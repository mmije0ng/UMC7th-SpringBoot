package com.umc.workbook.repository.ReviewRepository;

import com.umc.workbook.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
