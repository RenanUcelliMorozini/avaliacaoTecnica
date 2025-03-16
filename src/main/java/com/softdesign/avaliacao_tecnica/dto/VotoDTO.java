package com.softdesign.avaliacao_tecnica.dto;

import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    private Long id;
    private SessaoVotacao sessao;
    private Long associadoId;
    private Boolean voto;
}
