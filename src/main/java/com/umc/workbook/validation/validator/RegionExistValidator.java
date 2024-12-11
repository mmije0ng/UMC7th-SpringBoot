package com.umc.workbook.validation.validator;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.service.RegionService.RegionQueryService;
import com.umc.workbook.validation.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// ExistRegion 어노테이션에 대한 로직을 담고
// 검증 대상은 Long
@RequiredArgsConstructor
@Component
public class RegionExistValidator implements ConstraintValidator <ExistRegion, Long> {
    
    private final RegionQueryService regionQueryService;
    private String fieldName; // 잘못된 필드명

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName(); // 어노테이션의 필드 이름 설정
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 파라미터로 넘어온 지역 아이디가 존재하는 아이디인지 검증
        boolean isValid = regionQueryService.existsRegionById(value);

        // false이면 REGION_NOT_FOUND 에러 던지기
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
