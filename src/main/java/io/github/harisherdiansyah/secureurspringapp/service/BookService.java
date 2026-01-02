package io.github.harisherdiansyah.secureurspringapp.service;

import io.github.harisherdiansyah.secureurspringapp.dto.BookRequestDTO;
import io.github.harisherdiansyah.secureurspringapp.dto.BookResponseDTO;
import io.github.harisherdiansyah.secureurspringapp.exception.NotFoundException;
import io.github.harisherdiansyah.secureurspringapp.model.Book;
import io.github.harisherdiansyah.secureurspringapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDTO> getAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublishedDate(),
                        book.getCreatedAt(),
                        book.getUpdatedAt()
                )).toList();
    }

    public BookResponseDTO getById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data not found"));
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedDate(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        Book.BookBuilder bookBuilder = Book.builder()
                .title(bookRequestDTO.getTitle())
                .author(bookRequestDTO.getAuthor())
                .publishedDate(bookRequestDTO.getPublishedDate());

        Book newBook = bookRepository.save(bookBuilder.build());

        return new BookResponseDTO(
                newBook.getId(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getPublishedDate(),
                newBook.getCreatedAt(),
                newBook.getUpdatedAt()
        );
    }

    public BookResponseDTO updateBook(UUID id, BookRequestDTO bookRequestDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data not found"));

        Book.BookBuilder bookBuilder = Book.builder()
                .id(id)
                .title(bookRequestDTO.getTitle())
                .author(bookRequestDTO.getAuthor())
                .publishedDate(bookRequestDTO.getPublishedDate())
                .createdAt(existingBook.getCreatedAt())
                .updatedAt(existingBook.getUpdatedAt());

        Book updatedBook = bookRepository.save(bookBuilder.build());

        return new BookResponseDTO(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getPublishedDate(),
                updatedBook.getCreatedAt(),
                updatedBook.getUpdatedAt()
        );
    }

    public BookResponseDTO deleteBook(UUID id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data not found"));

        bookRepository.deleteById(id);

        return new BookResponseDTO(
                existingBook.getId(),
                existingBook.getTitle(),
                existingBook.getAuthor(),
                existingBook.getPublishedDate(),
                existingBook.getCreatedAt(),
                existingBook.getUpdatedAt()
        );
    }
}
