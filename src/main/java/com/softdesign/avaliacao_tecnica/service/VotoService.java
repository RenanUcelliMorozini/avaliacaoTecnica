package com.softdesign.avaliacao_tecnica.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.softdesign.avaliacao_tecnica.exception.AssociadoRealizouVotoException;
import com.softdesign.avaliacao_tecnica.exception.SessaoVotacaoInvalidaException;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.model.Voto;
import com.softdesign.avaliacao_tecnica.repository.SessaoVotacaoRepository;
import com.softdesign.avaliacao_tecnica.repository.VotoRepository;

@Service
public class VotoService {
    private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public VotoService(VotoRepository votoRepository, SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    public List<Voto> listarVotos () {
        return votoRepository.findAll();
    }

    public Voto registrarVoto(Long sessaoId, Long associadoId, Boolean voto) {
        final Optional<SessaoVotacao> sessao = sessaoVotacaoRepository.findById(sessaoId);
        if (sessao.isEmpty() || LocalDateTime.now().isAfter(sessao.get().getDataFechamento())) {
            throw new SessaoVotacaoInvalidaException("Sessão inválida ou encerrada");
        }

        if (votoRepository.existsByAssociadoIdAndSessaoId(associadoId, sessaoId)) {
            throw new AssociadoRealizouVotoException("O associado já votou nesta sessão.");
        }

        final Voto novoVoto = Voto.builder()
            .sessao(sessao.get())
            .associadoId(associadoId)
            .voto(voto)
            .build();
        
        return votoRepository.save(novoVoto);
    }
}