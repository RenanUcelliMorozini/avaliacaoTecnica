package com.softdesign.avaliacao_tecnica.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.softdesign.avaliacao_tecnica.domain.DominioTipoCampo;
import com.softdesign.avaliacao_tecnica.exception.PautaNaoEncontradaException;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.repository.PautaRepository;
import com.softdesign.avaliacao_tecnica.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaRepository pautaRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public SessaoVotacao abrirSessao(Long pautaId, DominioTipoCampo tipo, Long duracaoMinutos, 
            String valorTexto, Integer valorNumerico, LocalDate valorData) {

        final Pauta pauta = pautaRepository.findById(pautaId)
            .orElseThrow(() -> new PautaNaoEncontradaException("Pauta não encontrada com ID: " + pautaId));

        SessaoVotacao sessao = SessaoVotacao.builder()
            .pauta(pauta)
            .tipo(tipo.name())
            .idCampo(tipo.getCodigo())
            .titulo(tipo.getDescricao())
            .dataAbertura(LocalDateTime.now())
            .dataFechamento(LocalDateTime.now().plusMinutes(duracaoMinutos))
            .valorTexto(valorTexto)
            .valorNumerico(valorNumerico)
            .valorData(valorData)
            .build();

        return sessaoVotacaoRepository.save(sessao);
    }

    public List<SessaoVotacao> listarVotacao() {
        return sessaoVotacaoRepository.findAll();
    }
}