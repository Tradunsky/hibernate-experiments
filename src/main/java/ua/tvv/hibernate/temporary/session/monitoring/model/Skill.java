package ua.tvv.hibernate.temporary.session.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Skill {
    @Id
    private String surrogateId;

    @Column
    private String name;

    @ManyToOne
    private Employee employee;

    public Skill(String name, Employee employee) {
        this.name = name;
        this.employee = employee;
        generateId();
    }

    @PrePersist
    private void generateId() {
        surrogateId = employee.getId() + "-" + name;
    }
}
