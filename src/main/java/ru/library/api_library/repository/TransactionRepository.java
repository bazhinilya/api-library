package ru.library.api_library.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.library.api_library.model.Entities.Author;
import ru.library.api_library.model.Entities.Reader;
import ru.library.api_library.model.Entities.Transaction;
import ru.library.api_library.model.Enums.OperationType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t.book.author FROM Transaction t WHERE t.transactionDate BETWEEN ?1 AND ?2 GROUP BY t.book.author ORDER BY COUNT(t) DESC")
    List<Author> findMostPopularAuthors(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT t.reader FROM Transaction t GROUP BY t.reader ORDER BY COUNT(t) DESC")
    List<Reader> findMostActiveReaders();

    @Query("SELECT r FROM Reader r LEFT JOIN Transaction t ON r.phoneNumber = t.reader.phoneNumber GROUP BY r ORDER BY COUNT(t.id) DESC")
    List<Reader> findReadersWithUnreturnedBooks();

    @Query("SELECT t FROM Transaction t WHERE t.book.id = :bookId ORDER BY t.transactionDate DESC")
    List<Transaction> findByBookId(@Param("bookId") Long bookId);

    default boolean isBookBorrowed(Long bookId) {
        List<Transaction> transactions = findByBookId(bookId);
        return !transactions.isEmpty() && transactions.get(0).getOperationType().equals(OperationType.BORROW);
    }
}