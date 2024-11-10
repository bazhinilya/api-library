package ru.library.api_library.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.library.api_library.model.Entities.Author;
import ru.library.api_library.model.Entities.Book;
import ru.library.api_library.model.Entities.Reader;
import ru.library.api_library.model.Entities.Transaction;
import ru.library.api_library.model.Enums.OperationType;
import ru.library.api_library.repository.BookRepository;
import ru.library.api_library.repository.ReaderRepository;
import ru.library.api_library.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final TransactionRepository transactionRepository;

    public void performTransaction(String phoneNumber, Long bookId, String operationType) {
        var checkedOperationType = OperationType.fromString(operationType);

        Reader reader = readerRepository.findById(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Reader not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (transactionRepository.isBookBorrowed(bookId))
            throw new IllegalStateException("Book is not available for borrowing.");

        Transaction transaction = new Transaction();
        transaction.setOperationType(checkedOperationType);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setReader(reader);
        transaction.setBook(book);

        transactionRepository.save(transaction);
    }

    public Author getMostPopularAuthor(LocalDateTime startDate, LocalDateTime endDate) {
        var mostPopularAuthors = transactionRepository.findMostPopularAuthors(startDate, endDate);
        return Optional.ofNullable(mostPopularAuthors.get(0))
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
    }

    public Reader getMostActiveReader() {
        var mostActiveReaders = transactionRepository.findMostActiveReaders();
        return Optional.ofNullable(mostActiveReaders.get(0))
                .orElseThrow(() -> new IllegalArgumentException("Reader not found"));
    }

    public List<Reader> getReadersWithUnreturnedBooks() {
        return transactionRepository.findReadersWithUnreturnedBooks();
    }
}