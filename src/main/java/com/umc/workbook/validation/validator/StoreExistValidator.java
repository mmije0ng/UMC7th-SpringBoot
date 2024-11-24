package com.umc.workbook.validation.validator;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import com.umc.workbook.validation.annotation.ExistStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class StoreExistValidator implements ConstraintValidator  <ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 파라미터로 넘어온 가게 아이디가 존재하는 아이디인지 검증
        boolean isValid = storeRepository.existsById(value);

        // false이면 STORE_NOT_FOUND 에러 던지기
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
