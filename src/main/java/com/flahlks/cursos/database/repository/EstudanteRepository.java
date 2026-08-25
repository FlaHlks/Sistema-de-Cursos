package com.flahlks.cursos.database.repository;

import com.flahlks.cursos.database.model.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {

    boolean existsByEmail(String email);
}
