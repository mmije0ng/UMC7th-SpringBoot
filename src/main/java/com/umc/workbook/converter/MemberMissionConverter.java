package com.umc.workbook.converter;

import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(){
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
