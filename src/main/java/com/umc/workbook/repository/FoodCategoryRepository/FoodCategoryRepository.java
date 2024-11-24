package com.umc.workbook.repository.FoodCategoryRepository;

import com.umc.workbook.domain.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
