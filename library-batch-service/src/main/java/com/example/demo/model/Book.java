package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private Long id;
    private String name;
    private String author;
    private Double price;

    private String genre;


    private Boolean available;

    private Boolean isProlongation;

    private String photoName;

    private LocalDate borrowDate;

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


