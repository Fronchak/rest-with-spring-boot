package com.fronchak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.models.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
