package org.mycodeschool.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.mycodeschool.myblog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}