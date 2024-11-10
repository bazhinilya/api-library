package ru.library.api_library.model.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Book Model Information")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Book Id", example = "123")
    private Long id;

    @Schema(description = "The title of the book", example = "Something")
    private String title;

    @Schema(description = "The year of publication of the book", example = "1999")
    private int publishedYear;

    @ManyToOne
    private Author author;
}