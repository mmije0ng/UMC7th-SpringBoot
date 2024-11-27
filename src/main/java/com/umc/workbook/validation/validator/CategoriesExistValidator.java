package com.umc.workbook.validation.validator;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.repository.FoodCategoryRepository.FoodCategoryRepository;
import com.umc.workbook.validation.annotation.ExistCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
// ExistCategories 어노테이션에 대한 로직을 담고
// 검증 대상은 List<Long>
// ConstraintValidator: isValid 메소드의 반환 값을 확인하여 검증이 되었는지 실패했는 지를 알려 줌
// isValid의 리턴 값이 false면 ConstraintViolationException 을 발생시킴
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {

        // 검증 대상인 List<Long> 의 값을 가진 카테고리가 모두 데이터베이스에 있는 지를 판단
        // 하나라도 없다면 false를 반환
        boolean isValid = values.stream()
                .allMatch( value -> foodCategoryRepository.existsById(value));

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
