package org.mycodeschool.myblog.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.mycodeschool.myblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);
    List<Article> findByContentContaining(String content);

    
}
