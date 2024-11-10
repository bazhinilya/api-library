package ru.library.api_library.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ru.library.api_library.model.Entities.Author;
import ru.library.api_library.model.Entities.Reader;
import ru.library.api_library.service.LibraryService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/transaction")
    public void performTransaction(
            @RequestParam String phoneNumber,
            @RequestParam Long bookId,
            @RequestParam String operationType) {
        libraryService.performTransaction(phoneNumber, bookId, operationType);
    }

    @GetMapping("/popular-author")
    public Author getMostPopularAuthor(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return libraryService.getMostPopularAuthor(startDate, endDate);
    }

    @GetMapping("/most-active-reader")
    public Reader getMostActiveReader() {
        return libraryService.getMostActiveReader();
    }

    @GetMapping("/readers-unreturned-books")
    public List<Reader> getReadersWithUnreturnedBooks() {
        return libraryService.getReadersWithUnreturnedBooks();
    }
}