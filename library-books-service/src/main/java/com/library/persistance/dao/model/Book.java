package com.library.persistance.dao.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


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


    @Column(columnDefinition = "boolean default true")
    private Boolean available;

    @Nullable
    private Boolean isProlongation;

    @Nullable
    private String photoName;

    /*@Nullable
    @Temporal(TemporalType.DATE)
    private Date borrowDate;*/

    @Nullable
    private LocalDate borrowDate;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    @Nullable
    private AppUser borrower;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", available=" + available +
                ", isProlongation=" + isProlongation +
                ", photoName='" + photoName + '\'' +
                ", borrowDate=" + borrowDate +
                ", borrower=" + borrower +
                '}';
    }
}


