package ru.library.api_library.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ru.library.api_library.model.Entities.Author;
import ru.library.api_library.model.Entities.Reader;
import ru.library.api_library.service.LibraryService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Library", description = "Library management APIs")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/transaction")
    @Operation(summary = "Perform transaction", description = "Executes the transaction. The user can take and return the book", tags = {
            "transaction", "post" })
    @Parameters({
            @Parameter(name = "phoneNumber", description = "The reader's phone number", required = true),
            @Parameter(name = "bookId", description = "The book id", required = true),
            @Parameter(name = "operationType", description = "The type of operation is 'BORROW' or 'RETURN'", required = true)
    })
    public void performTransaction(
            @RequestParam String phoneNumber,
            @RequestParam Long bookId,
            @RequestParam String operationType) {
        libraryService.performTransaction(phoneNumber, bookId, operationType);
    }

    @GetMapping("/popular-author")
    @Operation(summary = "Get most popular Author", description = "Returns the most popular author", tags = {
            "author", "get" })
    @Parameters({
            @Parameter(name = "startDate", description = "The starting date of the range", required = true),
            @Parameter(name = "endDate", description = "The ending date of the range", required = true)
    })
    public Author getMostPopularAuthor(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return libraryService.getMostPopularAuthor(startDate, endDate);
    }

    @GetMapping("/most-active-reader")
    @Operation(summary = "Get most active Reader", description = "Returns the most active reader", tags = { "reader",
            "get" })
    public Reader getMostActiveReader() {
        return libraryService.getMostActiveReader();
    }

    @GetMapping("/readers-unreturned-books")
    @Operation(summary = "Get Readers with unreturned books", description = "returns a list of readers who have not returned their books", tags = {
            "readers", "get" })
    public List<Reader> getReadersWithUnreturnedBooks() {
        return libraryService.getReadersWithUnreturnedBooks();
    }
}