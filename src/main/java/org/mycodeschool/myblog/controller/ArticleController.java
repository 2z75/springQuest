package org.mycodeschool.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mycodeschool.myblog.model.Article;
import org.mycodeschool.myblog.model.Category;
import org.mycodeschool.myblog.repository.ArticleRepository;
import org.mycodeschool.myblog.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Article>> getArticlesByTitle(@PathVariable String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("content/{content}")
    public ResponseEntity<List<Article>> getArticlesByContent(@PathVariable String content) {
        List<Article> articles = articleRepository.findByContentContaining(content);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }


    // CREATION
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        if (article.getCategory() != null) {
            Category category = categoryRepository.findById(article.getCategory().getId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().body(null);
            }
            article.setCategory(category);
        }
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    // MODIFICATION
    @PutMapping("/{id}") //Méthode de mise à jour
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        // Mise à jour de la catégorie
        if (articleDetails.getCategory() != null) {
            Category category = categoryRepository.findById(articleDetails.getCategory().getId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().body(null); 
            }
            article.setCategory(category);
        }

        Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(updatedArticle);
    }

    // SUPPRESSION
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        articleRepository.delete(article);
        return ResponseEntity.noContent().build();
    }

    
}
