package com.umc.workbook.validation.validator;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.repository.MemberMissionRepository.MemberMissionRepository;
import com.umc.workbook.validation.annotation.ValidMissionStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class MissionStatusValidator implements ConstraintValidator<ValidMissionStatus, Map<String, String>> {

    private final MemberMissionRepository memberMissionRepository;

    private String missionIdField;
    private String memberIdField;

    @Override
    public void initialize(ValidMissionStatus annotation) {
        this.missionIdField = annotation.missionIdField();
        this.memberIdField = annotation.memberIdField();
    }

    @Override
    public boolean isValid(Map<String, String> params, ConstraintValidatorContext context) {
        // 필드 값 추출
        Long missionId = Long.parseLong(params.get(missionIdField));
        Long memberId = Long.parseLong(params.get(memberIdField));

        // 존재 여부 확인
        boolean exists = memberMissionRepository
                .findMemberMissionByMissionIdAndMemberId(missionId, memberId)
                .isPresent();

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_EXISTS.toString())
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
