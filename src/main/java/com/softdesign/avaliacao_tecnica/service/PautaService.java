package com.softdesign.avaliacao_tecnica.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.softdesign.avaliacao_tecnica.model.Botao;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.repository.PautaRepository;

@Service
public class PautaService {
    private static final String URL_DESTINO = "softdesgin.com/ACAO";
    private static final String URL_RETORNO = "softdesgin.com";


    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(String tipo, String titulo) {
        final Pauta pauta = Pauta.builder()
            .tipo(tipo)
            .titulo(titulo)
            .botaoOk(Botao.builder().texto("Ação").url(URL_DESTINO).build())
            .botaoCancelar(Botao.builder().texto("Cancelar").url(URL_RETORNO).build())
            .build();
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }
}