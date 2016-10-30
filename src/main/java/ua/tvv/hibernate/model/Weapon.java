package ua.tvv.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tradunsky V.V.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Weapon {
    @Id
    private String name;
//    private Integer hunterInn;
}
