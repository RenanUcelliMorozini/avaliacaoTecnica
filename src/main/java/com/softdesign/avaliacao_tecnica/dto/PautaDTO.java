package com.softdesign.avaliacao_tecnica.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {
    
    @JsonIgnore
    private Long id;

    @NotBlank(message = "O tipo é obrigatório.")
    private String tipo;  

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    private List<SessaoVotacao> sessoes;
}