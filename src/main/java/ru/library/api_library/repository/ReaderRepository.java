package ru.library.api_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.library.api_library.model.Entities.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {
}