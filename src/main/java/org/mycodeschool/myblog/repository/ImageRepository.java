package org.mycodeschool.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.mycodeschool.myblog.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}