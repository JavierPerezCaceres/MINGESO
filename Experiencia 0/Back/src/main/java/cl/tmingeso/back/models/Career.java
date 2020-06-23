package cl.tmingeso.back.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="career")
public class Career implements  Serializable{

    // ***** START Columns. *****

    // ID Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    // Name Column.
    @Column(nullable = false, name = "`name`")
    private String name;

    // ***** END Columns. *****

    // ***** START Constructors *****

    // Empty Constructor.
    public Career(){}

    // Constructor.
    public Career(String name){
        this.name = name;
    }

    // ***** END Constructors *****

    // ***** START Getters & Setters *****

    // Get Name.
    public String getName(){
        return name;
    }

    // Set Name.
    public void setName(String name){
        this.name = name;
    }

    // ***** START Getters & Setters *****
}
