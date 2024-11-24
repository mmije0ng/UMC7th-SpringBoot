package com.umc.workbook.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    // List<String> -> JSON 문자열로 변환
    public static String convertImageListToJson(List<String> imageList) {
        try {
            return mapper.writeValueAsString(imageList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("이미지 리스트 JSON 변환 중 오류 발생", e);
        }
    }

    // String -> JSON 변환
    public static String convertStringToJson(String value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("문자열 JSON 변환 중 오류 발생", e);
        }
    }

    // JSON 문자열 -> List<String> 변환
    public static List<String> convertJsonToImageList(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON을 이미지 리스트로 변환 중 오류 발생", e);
        }
    }

    // JSON -> String 변환
    public static String convertJsonToString(String json) {
        try {
            return mapper.readValue(json, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 문자열 변환 중 오류 발생", e);
        }
    }
}