package cl.tmingeso.back.controllers;

import cl.tmingeso.back.models.Career;
import cl.tmingeso.back.models.Student;
import cl.tmingeso.back.repositories.CareerRepository;
import cl.tmingeso.back.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(StudentController.class)
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private CareerRepository careerRepository;

    @Autowired
    private ObjectMapper mapper;

    private static final String NAME = "Javier";
    private static final String LASTNAME = "Pérez";
    private static final String RUT  = "19490189-5";
    private static final String URL = "/students";
    private static final String BIRTHDATE = "17-03-1997";
    private static final String CAREER = "Ingeniería Civil en Informática";
    private static final long ID = 1;

    @Test
    void getAllStudents() throws ParseException {

        // Declarations.
        Student student = new Student();
        Career career = new Career();
        List<Student> studentList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Set to a new Career

        // Set to new student.
        student.setID(ID);
        student.setRut(RUT);
        student.setName(NAME);
        student.setLastname(LASTNAME);
        student.setBirthDate(LocalDate.parse(BIRTHDATE, formatter));
        student.setCareer(CAREER);

        // New student is added.
        studentList.add(student);

        // Test
        given(studentRepository.findAll()).willReturn(studentList);
        try{
            System.out.println(mvc.perform(get(URL)
                    .contentType(APPLICATION_JSON)));
            mvc.perform(get(URL)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rut", is(RUT)));
        }
        catch (Exception ignored){
        }
    }

    @Test
    void create() throws ParseException {

        // Declarations.
        Student student = new Student(RUT,NAME,LASTNAME,BIRTHDATE,CAREER);
        Career career = new Career();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Set to a new Career
        career.setName(CAREER);

        // Set to new student.
        student.setID(ID);
        student.setRut(RUT);
        student.setName(NAME);
        student.setLastname(LASTNAME);
        student.setBirthDate(LocalDate.parse(BIRTHDATE, formatter));
        student.setCareer(CAREER);

        // Test
        given(studentRepository.save(student)).willReturn(student);
        try{
            mvc.perform(post(URL + "/" + "create")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(student.getId())))
                .andExpect(jsonPath("$.rut",is(student.getRut())))
                .andExpect(jsonPath("$.name",is(student.getName())))
                .andExpect(jsonPath("$.lastname",is(student.getLastname())))
                .andExpect(jsonPath("$.birthDate",is(student.getBirthDate())))
                .andExpect(jsonPath("$.career",is(student.getCareer())));
        }
        catch (Exception ignored){ }

    }
}