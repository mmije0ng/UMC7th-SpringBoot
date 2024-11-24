package com.umc.workbook.validation.annotation;

import com.umc.workbook.validation.validator.RegionExistValidator;
import com.umc.workbook.validation.validator.StoreExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistValidator.class) // 검증 로직을 구현한 클래스 지정
@Target({ElementType.PARAMETER, ElementType.FIELD}) // 어노테이션 적용 범위: 메서드 파라미터
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지
public @interface ExistStore {
    String message() default "해당하는 가게가 존재하지 않습니다.";

    Class<?>[] groups() default {}; // 유효성 검사 그룹

    Class<? extends Payload>[] payload() default {}; // 메타데이터 전달용
}
