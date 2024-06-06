package backend.backend.students;

import backend.backend.exception.StudentNotFoundException;
import backend.backend.students.Student;
import backend.backend.students.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = {
        "https://www.jamesresume.online",
        "http://localhost:3000",
        "https://james-resume-av9x.vercel.app/",
        "https://james-resume-backend-9a3094b7738e.herokuapp.com"
})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        Student student = studentService.getStudentById(id);
        student.setName(studentDetails.getName());
        student.setSex(studentDetails.getSex());
        student.setEmail(studentDetails.getEmail());
        Student updatedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}