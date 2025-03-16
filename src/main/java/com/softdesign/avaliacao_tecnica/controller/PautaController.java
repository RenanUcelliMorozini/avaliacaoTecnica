package com.softdesign.avaliacao_tecnica.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.service.PautaService;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    private  PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    public List<Pauta> listarPautas() {
        return pautaService.listarPautas();
    }

    @PostMapping
    public Pauta criarPauta(@RequestParam String tipo, @RequestParam String titulo) {
        return pautaService.criarPauta(tipo, titulo);
    }
}