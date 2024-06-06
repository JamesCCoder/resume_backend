package backend.backend.professors;

import backend.backend.exception.ProfessorNotFoundException;
import backend.backend.professors.Professor;
import backend.backend.professors.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = {
        "https://www.jamesresume.online",
        "http://localhost:3000",
        "https://james-resume-backend-9a3094b7738e.herokuapp.com"
})
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        Professor professor = professorService.getProfessorById(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public Professor createProfessor(@Valid @RequestBody Professor professor) {
        return professorService.saveProfessor(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @Valid @RequestBody Professor professorDetails) {
        Professor professor = professorService.getProfessorById(id);
        professor.setName(professorDetails.getName());
        professor.setSex(professorDetails.getSex());
        professor.setEmail(professorDetails.getEmail());
        Professor updatedProfessor = professorService.saveProfessor(professor);
        return ResponseEntity.ok(updatedProfessor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProfessorNotFoundException.class)
    public ResponseEntity<String> handleProfessorNotFoundException(ProfessorNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
