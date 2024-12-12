package com.umc.workbook.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResultDTO { // 미션 등록
        Long missionId; // 미션 아이디
        LocalDateTime createdAt; // 생성일자
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberMissionResultDTO { // 도전 중인 미션으로 변경
        String missionContent; // 미션 이름
        String missionStatus; // 미션 상태
        LocalDateTime expiredAt; // 만료 일자
    }

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static abstract class BaseMissionPreViewListDTO<T> { // 공통 미션 목록 응답
        List<T> missionList; // 미션 목록
        Integer listSize; // 현재 페이지의 리뷰 목록 개수
        Integer totalPage; // 총 페이지
        Long totalElements; // 총 리뷰 개수
        Boolean isFirst; // 페이지 처음 여부
        Boolean isLast; // 페이지 마지막 여부
    }

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionPreViewListDTO extends BaseMissionPreViewListDTO<StoreMissionPreviewDTO> { // 가게 미션 목록
        String storeName; // 가게 이름
    }

    @SuperBuilder
    @Getter
    public static class MemberMissionPreViewListDTO extends BaseMissionPreViewListDTO<MemberMissionPreviewDTO> { // 멤버 미션 목록
    }

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static abstract class BaseMissionPreviewDTO { // 공통 미션 조회
        String content; // 미션 내용
        Integer money; // 기준 금액
        Integer point; // 적립 포인트
        LocalDateTime createdAt; // 생성 일자
    }

    @SuperBuilder
    @Getter
    public static class StoreMissionPreviewDTO extends BaseMissionPreviewDTO { // 가게 미션
    }

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreviewDTO extends BaseMissionPreviewDTO { // 멤버 미션
        String missionStatus; // 미션 상태
        String storeName; // 가게 이름
    }
}
