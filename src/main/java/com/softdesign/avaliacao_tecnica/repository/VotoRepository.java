package com.softdesign.avaliacao_tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softdesign.avaliacao_tecnica.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByAssociadoIdAndSessaoId(Long associadoId, Long sessaoId);
    
}