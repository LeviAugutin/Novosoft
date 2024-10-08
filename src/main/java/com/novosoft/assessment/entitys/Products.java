package com.novosoft.assessment.entitys;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigInteger;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    @NotNull
    @Positive
    private BigInteger price;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;

}
