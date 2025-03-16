package com.softdesign.avaliacao_tecnica.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "O tipo é obrigatório.")
    private String tipo; 

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;
  
    @JsonManagedReference
    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY)
    private List<SessaoVotacao> sessoes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "texto", column = @Column(name = "botao_ok_texto")),
        @AttributeOverride(name = "url", column = @Column(name = "botao_ok_url"))
    })
    private Botao botaoOk;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "texto", column = @Column(name = "botao_cancelar_texto")),
        @AttributeOverride(name = "url", column = @Column(name = "botao_cancelar_url"))
    })
    private Botao botaoCancelar;


}
