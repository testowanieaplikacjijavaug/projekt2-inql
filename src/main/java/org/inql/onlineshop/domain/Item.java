package org.inql.onlineshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "db_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null.")
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[A-Z][a-z]+",message = "Invalid name input.")
    private String name;

    @NotNull(message = "Value cannot be null.")
    @Positive(message = "Value must be higher than zero.")
    @Digits(integer = 10, fraction = 2, message = "Invalid value input.")
    private Double value;

    public Item() {
    }

    public Item(@NotNull(message = "Name cannot be null.") @NotBlank(message = "Name cannot be blank.") @Size(min = 2, max = 30) @Pattern(regexp = "[A-Z][a-z]+", message = "Invalid name input.") String name, @NotNull @Positive(message = "Value must be higher than zero.") @Digits(integer = 10, fraction = 2, message = "Invalid value input.") Double value) {
        this.name = name;
        this.value = value;
    }
}
