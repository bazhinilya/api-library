package ru.library.api_library.model.Entities;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.library.api_library.model.Enums.OperationType;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Transaction Model Information")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Transaction Id", example = "123")
    private Long id;

    @Schema(description = "The type of transaction is 'BORROW' or 'RETURN'", example = "RETURN")
    private OperationType operationType;

    @Schema(description = "Date and time of the transaction", example = "22/12/2022 13:56:54")
    private LocalDateTime transactionDate;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;
}