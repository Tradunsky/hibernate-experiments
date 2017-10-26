package ua.tvv.hibernate.temporary.session.monitoring.model;


import org.hibernate.annotations.CollectionType;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Employee {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Column
    @CollectionType(type = "ua.tvv.hibernate.temporary.session.monitoring.collection.type.MonitorableBagType")
    private List<Skill> skills = new LinkedList<>();

    public Employee(Integer id) {
        this.id = id;
    }
}
