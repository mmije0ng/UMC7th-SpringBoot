package com.umc.workbook.validation.annotation;

import com.umc.workbook.validation.validator.MissionStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionStatusValidator.class)
@Target(ElementType.PARAMETER) // 메서드 레벨에 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMissionStatus {
    String message() default "미션 상태가 유효하지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String missionIdField() default "missionId";

    String memberIdField() default "memberId";

}

