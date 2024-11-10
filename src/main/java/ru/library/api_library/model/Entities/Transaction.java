package ru.library.api_library.model.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.library.api_library.model.Enums.OperationType;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OperationType operationType;
    private LocalDateTime transactionDate;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;    
}