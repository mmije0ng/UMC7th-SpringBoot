package com.umc.workbook.service.FoodCategoryService;

import com.umc.workbook.repository.FoodCategoryRepository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryQueryServiceImpl implements FoodCategoryQueryService{
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Boolean existsFoodCategoryById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
