package com.softdesign.avaliacao_tecnica.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    private String idCampo;
    
    @NotNull(message = "O tipo de campo não pode ser nulo.") 
    private String tipo;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pautaId", nullable = false)
    private Pauta pauta;

    @JsonManagedReference
    @OneToMany(mappedBy = "sessao", fetch = FetchType.LAZY)
    private List<Voto> votos;

    private String titulo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String valorTexto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer valorNumerico;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate valorData;


    @JsonIgnore
    private LocalDateTime dataAbertura;

    @JsonIgnore
    private LocalDateTime dataFechamento;
    
}