package com.umc.workbook.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.umc.workbook.config.AmazonConfig;
import com.umc.workbook.domain.Uuid;
import com.umc.workbook.repository.UuidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig; // S3를 사용시 필요한 인증에 대한 과정 등이 포함

    private final UuidRepository uuidRepository;

    // s3버킷에 파일 업로드
    public String uploadFile(String keyName, MultipartFile file){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType()); // 파일의 Content-Type 자동 설정
        metadata.setContentLength(file.getSize()); // 파일 크기 설정

        try {
            // PutObjectRequest 를 파라미터로 받아 S3 버킷에 저장
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e){
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        // getUrl 매서드를 이용해서 버킷에 저장 된 파일의 url을 받아서 리턴
        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    // 리뷰 이미지 업로드 시 KeyName을 만들도록 리턴
    public String generateReviewKeyName(Uuid uuid) {
        return amazonConfig.getReviewPath() + '/' + uuid.getUuid();
    }
}