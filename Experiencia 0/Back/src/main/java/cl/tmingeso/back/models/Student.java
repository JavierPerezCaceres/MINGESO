package cl.tmingeso.back.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="students")
public class Student implements Serializable{

    // ***** START Columns. *****

    // ID Column.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    // RUT Column.
    @Column(nullable = false, name = "`rut`", unique = true)
    private String rut;

    // Name Column.
    @Column(nullable = false, name = "`name`")
    private String name;

    // Lastname Column.
    @Column(nullable = false, name = "`lastname`")
    private String lastname;

    // BirthDate Column.
    @Column(nullable = false, name = "`birth_Date`")
    private LocalDate birthDate;

    @Column(nullable = false,name="career")
    private String career;

    // ***** END Columns *****

    // ***** START Constructors *****

    // Empty Constructor.
    public Student(){}

    // Constructor.
    public Student(String rut, String name, String lastname,String date,String career){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.rut = rut;
        this.name = name;
        this.lastname = lastname;
        this.career = career;
        this.birthDate = LocalDate.parse(date, formatter);
    }

    // ***** END Constructors *****

    // ***** START Getters & Setters *****

    // Get ID.
    public Long getId(){
        return id;
    }

    // Set ID.
    public void setID(Long id){
        this.id = id;
    }

    // Get Rut.
    public String getRut(){
        return rut;
    }

    // Set Rut.
    public void setRut(String rut){
        this.rut = rut;
    }

    // Get Name.
    public String getName(){
        return name;
    }

    // Set Name.
    public void setName(String name){
        this.name = name;
    }

    // Get Lastname.
    public String getLastname(){
        return lastname;
    }

    // Set Lastname.
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    // Get BirthDate.
    public LocalDate getBirthDate(){
        return birthDate;
    }

    // Set BirthDate.
    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }

    // Get Career.
    public String getCareer(){ return career; }

    // Set Career.
    public void setCareer(String career){ this.career = career;}

    // ***** END Getters & Setters *****
}
