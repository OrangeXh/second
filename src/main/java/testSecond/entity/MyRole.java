package testSecond.entity;

import com.sun.javafx.geom.transform.Identity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="role")
public class MyRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    private String name;

    public MyRole(){}

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }



}
