package com.softdesign.avaliacao_tecnica.dto;

import java.util.List;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {
    
    private String tipo; 
    private String titulo;
    private List<SessaoVotacao> sessoes;
}