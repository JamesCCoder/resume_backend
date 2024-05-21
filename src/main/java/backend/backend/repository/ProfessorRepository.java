package backend.backend.repository;

import backend.backend.model.Professor;
import backend.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
