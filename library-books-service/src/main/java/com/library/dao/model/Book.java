package com.library.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private Double price;

    //Genre du livre
    private String genre;
    //Nombre d'exemplaires
    private Long quantity;

    @Column(columnDefinition = "boolean default true")
    private Boolean available;

    @Nullable
    private Boolean isProlongation;

    /*@Nullable
    @Temporal(TemporalType.DATE)
    private Date borrowDate;*/

    @Nullable
    private LocalDateTime borrowDate;

    @Nullable
    private Long borrowerId;

}


