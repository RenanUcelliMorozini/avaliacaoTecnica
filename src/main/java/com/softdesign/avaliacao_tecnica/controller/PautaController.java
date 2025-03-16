package com.softdesign.avaliacao_tecnica.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.softdesign.avaliacao_tecnica.dto.PautaDTO;
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
    public List<PautaDTO> listarPautas() {
        List<Pauta> pautas = pautaService.listarPautas();
        List<PautaDTO> pautaDTOs = pautas.stream()
                .map(this::converterParaDTO) 
                .collect(Collectors.toList());
        return pautaDTOs;
    }

    @PostMapping
    public PautaDTO criarPauta(@RequestParam String tipo, @RequestParam String titulo) {
        Pauta pautaSalva = pautaService.criarPauta(tipo, titulo);
        return converterParaDTO(pautaSalva);
    }

    private PautaDTO converterParaDTO(Pauta pauta) {
        PautaDTO dto = new PautaDTO();
        BeanUtils.copyProperties(pauta, dto);
        return dto;
    }
}