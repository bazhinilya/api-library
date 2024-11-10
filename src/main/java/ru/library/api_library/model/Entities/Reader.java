package ru.library.api_library.model.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import ru.library.api_library.model.Enums.Gender;

@Data
@Entity
public class Reader {
    @Id
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String birthDate;
    
    @OneToMany(mappedBy = "reader")
    private List<Transaction> transactions;
}