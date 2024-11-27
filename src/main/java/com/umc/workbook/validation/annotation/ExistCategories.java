package com.umc.workbook.validation.annotation;

import com.umc.workbook.validation.validator.CategoriesExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션
@Constraint(validatedBy = CategoriesExistValidator.class) // CategoriesExistValidator라는 클래스를 통해 @ExistCategories가 붙은 대상을 검증
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER} ) // 어노테이션 적용 범위
@Retention(RetentionPolicy.RUNTIME) // 어노테이셔의 생명 주기 지정 (현재는 RUNTIME, 실행 중에만 유효)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class <?>[] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String fieldName() default "memberId"; // 잘못된 필드 이름 포함
}
