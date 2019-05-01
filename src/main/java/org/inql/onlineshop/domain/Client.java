package org.inql.onlineshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "db_client")
public class Client {

    public Client() {
    }

    public Client(@NotNull @Size(min = 2, max = 30) String name, @NotNull @Size(min = 2, max = 30) String surname, @NotNull @Size(min = 2, max = 30) String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 4, max = 30)
    @Pattern(regexp =
            "[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*",    message = "Invalid name provided")
    private String name;

    @Column(unique = true)
    @NotNull(message = "Surname cannot be null")
    @Size(min = 4, max = 30)
    @Pattern(regexp =
            "[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*",    message = "Invalid surname provided")
    private String surname;

    @Column(unique = true)
    @NotNull(message = "Email cannot be null")
    @Size(min = 4, max =30)
    @Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]$",message = "Invalid email provided")
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}
