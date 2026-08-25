package com.flahlks.cursos.database.repository;

import com.flahlks.cursos.database.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {

    boolean existsByMateria(String materia);
}
