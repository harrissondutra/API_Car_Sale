package com.carsale.cars.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
@Table(name = "users", schema = "carsale")
@Entity(name = "User")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 598109323580992603L;

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean active;
}
