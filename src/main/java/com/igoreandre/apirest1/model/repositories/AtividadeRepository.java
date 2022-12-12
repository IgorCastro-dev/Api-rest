package com.igoreandre.apirest1.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igoreandre.apirest1.model.entity.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

}
