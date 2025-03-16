package com.softdesign.avaliacao_tecnica.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.repository.PautaRepository;

@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(String tipo, String titulo) {
        final Pauta pauta = Pauta.builder().tipo(tipo).titulo(titulo).build();
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }
}