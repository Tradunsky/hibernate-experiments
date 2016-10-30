package ua.tvv.hibernate.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Hunter {
    @Id
    @GeneratedValue
    private Integer inn;
    private String firstName;
    private String lastName;
    @OneToMany
    @JoinColumn(name = "hunter_inn")
    private List<Weapon> weapons = new LinkedList<>();
}
