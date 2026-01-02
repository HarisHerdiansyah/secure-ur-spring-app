package io.github.harisherdiansyah.secureurspringapp.repository;

import io.github.harisherdiansyah.secureurspringapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}
