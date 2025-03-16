package com.softdesign.avaliacao_tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softdesign.avaliacao_tecnica.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    
}
