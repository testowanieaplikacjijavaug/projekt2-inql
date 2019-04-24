package org.inql.onlineshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "db_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @Column(unique = true)
    @NotNull
    @Size(min = 2, max = 30)
    private String surname;

    @Column(unique = true)
    @NotNull
    @Size(min = 2, max =30)
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}
