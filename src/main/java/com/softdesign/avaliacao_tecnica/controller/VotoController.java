package com.softdesign.avaliacao_tecnica.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.softdesign.avaliacao_tecnica.dto.VotoDTO;
import com.softdesign.avaliacao_tecnica.exception.AssociadoRealizouVotoException;
import com.softdesign.avaliacao_tecnica.exception.SessaoVotacaoInvalidaException;
import com.softdesign.avaliacao_tecnica.model.Voto;
import com.softdesign.avaliacao_tecnica.service.VotoService;

@RestController
@RequestMapping("/votos")
public class VotoController {
    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @GetMapping
    public List<VotoDTO> listarVotos() {
        List<Voto> votos = votoService.listarVotos();
        List<VotoDTO> votoDTOs = votos.stream()
                .map(this::converterParaDTO) 
                .collect(Collectors.toList());
        return votoDTOs;
    }

    @PostMapping
    public ResponseEntity<?> registrarVoto(@RequestParam Long sessaoId, @RequestParam Long associadoId, @RequestParam(value = "voto")boolean voto) {
        try {
            VotoDTO novoVotoDTO = converterParaDTO(votoService.registrarVoto(sessaoId, associadoId, voto));
            return ResponseEntity.ok(novoVotoDTO);
        } catch (SessaoVotacaoInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (AssociadoRealizouVotoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao registrar o voto.");
        }
    }

    private VotoDTO converterParaDTO(Voto voto) {
        VotoDTO dto = new VotoDTO();
        BeanUtils.copyProperties(voto, dto);
        return dto;
    }
}