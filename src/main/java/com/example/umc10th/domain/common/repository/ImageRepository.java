package com.example.umc10th.domain.common.repository;

import com.example.umc10th.domain.common.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
