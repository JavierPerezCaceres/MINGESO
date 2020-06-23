package cl.tmingeso.back.repositories;

import cl.tmingeso.back.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {

    //Verify if a Student exists by a input Rut
    Boolean existsStudentByRut(int rut);
}
