package com.clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookBean {

    private Long id;
    private String name;
    private String author;
    private BigDecimal price;

    private Boolean available;

    //Genre du livre
    private String genre;

    //Nombre d'exemplaires
    private Long quantity;

    private Boolean isProlongation;

    private LocalDateTime borrowDate;
}
