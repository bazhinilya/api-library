package ru.library.api_library.model.Entities;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Author Model Information")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Author Id", example = "123")
    private Long id;

    @Schema(description = "Author's first name", example = "Ivan")
    private String firstName;

    @Schema(description = "Author's last name", example = "Ivanov")
    private String lastName;

    @Schema(description = "Date of birth of the author", example = "08/02/2000")
    private String birthDate;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}