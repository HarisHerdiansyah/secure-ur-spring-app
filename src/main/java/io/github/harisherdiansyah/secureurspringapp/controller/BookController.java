package io.github.harisherdiansyah.secureurspringapp.controller;

import io.github.harisherdiansyah.secureurspringapp.dto.BookRequestDTO;
import io.github.harisherdiansyah.secureurspringapp.dto.BookResponseDTO;
import io.github.harisherdiansyah.secureurspringapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getById(@PathVariable UUID id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookResponseDTO addBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.addBook(bookRequestDTO);
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable UUID id,
                                      @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.updateBook(id, bookRequestDTO);
    }

    @DeleteMapping("/{id}")
    public BookResponseDTO deleteBook(@PathVariable UUID id) {
        return bookService.deleteBook(id);
    }
}
