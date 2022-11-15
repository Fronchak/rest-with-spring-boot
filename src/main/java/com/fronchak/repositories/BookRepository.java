package com.fronchak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.models.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {}
