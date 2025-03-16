package com.softdesign.avaliacao_tecnica.dto;

import java.time.LocalDateTime;
import com.softdesign.avaliacao_tecnica.model.Pauta;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {

    private Long id;
    private Pauta pauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String valorString;
    private Integer valorInteger;
    private LocalDateTime valorData;

    
}
