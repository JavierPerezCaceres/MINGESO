package cl.tmingeso.back.controllers;

import cl.tmingeso.back.models.Student;
import cl.tmingeso.back.repositories.CareerRepository;
import cl.tmingeso.back.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://161.35.60.79:8080")
@RestController
@RequestMapping("/students")
public class StudentController {

    // ***** START Repositories Declaration ******

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CareerRepository careerRepository;

    // ***** END Repositories Declaration ******

    // ***** START services *****

    // Get all Students.
    @GetMapping
    @ResponseBody
    public List<Student> getAllStudents() { return studentRepository.findAll(); }

    // Create new Student
    @PostMapping("/create")
    @ResponseBody
    public Student create(@RequestBody Map<String, Object> jsonData){

        Logger logger = Logger.getLogger(StudentController.class.getName());

        // Creates a new student and add it to data base.
        Student student = new Student(jsonData.get("rut").toString(), jsonData.get("name").toString(), jsonData.get("lastname").toString(), jsonData.get("birthDate").toString(), jsonData.get("career").toString());
        try {
            studentRepository.save(student);
            return student;
        }
        catch (NullPointerException e){
            logger.log(Level.WARNING, "Rut already exist!!",e);
        }
        return new Student();
    }

    // ***** END services *****
}



