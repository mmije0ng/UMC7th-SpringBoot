package com.umc.workbook.repository.FoodCategoryRepository;

import com.umc.workbook.domain.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
