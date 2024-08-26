package org.mycodeschool.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.mycodeschool.myblog.model.ArticleAuthor;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {
}