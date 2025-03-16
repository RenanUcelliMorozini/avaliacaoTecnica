package com.softdesign.avaliacao_tecnica.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {

    @JsonProperty("id")
    private String idCampo;

    @JsonProperty("tipo")
    private String tipo;

    private PautaDTO pauta; 

    private List<VotoDTO> votos;

    private String titulo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String valorTexto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer valorNumerico;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate valorData;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime dataAbertura;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime dataFechamento;

}
