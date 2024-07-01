package org.wildcodeschool.myblog.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcodeschool.myblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);
    List<Article> findByContentContaining(String content);
    List<Article> findByCreatedAtAfter(LocalDateTime date);
    List<Article> findTop5ByOrderByCreatedAtDesc();
    
}

// A l'aide de la documentation, ajoute dans l'interface ArticleRepository des méthodes de requête personnalisées supplémentaires :

// qui retourne une liste d'articles dont le contenu contient une chaine de caractère fournie en paramètre,
// qui retourne une liste d'articles créée après une date fournie en paramètre,
// qui retourne les 5 derniers articles créés et classés du plus récent au plus ancien.
// Crée ensuite les endpoints qui te permettront d'utiliser ces méthodes dans ta classe ArticleController.

// Versionne ton code sur Github et met à disposition le lien vers ton repository.