package com.umc.workbook.validation.validator;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.service.MemberService.MemberQueryService;
import com.umc.workbook.validation.annotation.ExistMember;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// ExistMember 어노테이션에 대한 로직을 담고
// 검증 대상은 Long
@RequiredArgsConstructor
@Component
public class MemberExistValidator implements ConstraintValidator <ExistMember,Long> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 파라미터로 넘어온 지역 아이디가 존재하는 아이디인지 검증
        boolean isValid = memberQueryService.existsMemberById(value);

        // false이면 MEMBER_NOT_FOUND 에러 던지기
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
