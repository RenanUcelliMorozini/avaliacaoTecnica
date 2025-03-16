package com.softdesign.avaliacao_tecnica.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.softdesign.avaliacao_tecnica.domain.DominioTipoCampo;
import com.softdesign.avaliacao_tecnica.dto.SessaoVotacaoDTO;
import com.softdesign.avaliacao_tecnica.exception.PautaNaoEncontradaException;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.service.SessaoVotacaoService;

@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @GetMapping
    public List<SessaoVotacaoDTO> listarVotacao() {
        List<SessaoVotacao> sessaoVotacaos = sessaoVotacaoService.listarVotacao();
        List<SessaoVotacaoDTO> sessaoVotacaoDTOs = sessaoVotacaos.stream()
                .map(this::converterParaDTO) 
                .collect(Collectors.toList());
                
        return sessaoVotacaoDTOs;
    }

    @PostMapping("/abrir")
    public ResponseEntity<?> abrirSessao(
            @RequestParam Long pautaId, 
            @RequestParam DominioTipoCampo tipo,
            @RequestParam(required = false, defaultValue = "1") Long duracaoMinutos,
            @RequestParam(required = false) String valorTexto,
            @RequestParam(required = false) Integer valorNumerico,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate valorData
            ) {
        try {
            SessaoVotacaoDTO sessaoVotacaoDTO = converterParaDTO(sessaoVotacaoService.abrirSessao(pautaId, tipo, duracaoMinutos, valorTexto, valorNumerico, valorData));
            return ResponseEntity.status(HttpStatus.CREATED).body(sessaoVotacaoDTO); 
        } catch (PautaNaoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());  
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: ".concat(ex.getMessage())); 
        }
    }

    private SessaoVotacaoDTO converterParaDTO(SessaoVotacao sessaoVotacao) {
        SessaoVotacaoDTO dto = new SessaoVotacaoDTO();
        BeanUtils.copyProperties(sessaoVotacao, dto);
        return dto;
    }
}
