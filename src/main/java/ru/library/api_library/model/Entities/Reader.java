package ru.library.api_library.model.Entities;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.library.api_library.model.Enums.Gender;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Reader Model Information")
public class Reader {
    @Id
    @Schema(description = "The reader's phone number", example = "79545564454")
    private String phoneNumber;

    @Schema(description = "Reader's first name", example = "Ivan")
    private String firstName;

    @Schema(description = "Reader's last name", example = "Ivanov")
    private String lastName;

    @Schema(description = "Reader's gender 'FEMALE' or 'MALE'", example = "MALE")
    private Gender gender;

    @Schema(description = "Date of birth of the reader", example = "08/02/2000")
    private String birthDate;

    @OneToMany(mappedBy = "reader")
    private List<Transaction> transactions;
}