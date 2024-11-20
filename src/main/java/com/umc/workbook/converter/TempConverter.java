package com.umc.workbook.converter;

import com.umc.workbook.dto.TempResponse;

public class TempConverter {
    
    // dto로 변환
    public static TempResponse.TempTestDTO toTempTestDTO(){
        return TempResponse.TempTestDTO.builder()
                .testString("This is Test")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag){
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
