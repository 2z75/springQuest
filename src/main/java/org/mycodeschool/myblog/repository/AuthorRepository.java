package org.mycodeschool.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.mycodeschool.myblog.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}