package com.example.twodatabase.db2.repository;

import com.example.twodatabase.db2.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
public interface BookRepository extends JpaRepository<BookEntity, Serializable> {
}
