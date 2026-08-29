package com.flahlks.cursos.database.repository;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
}
