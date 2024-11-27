package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 지역
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false, length = 20)
    private String regionName; // 지역 이름

    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // REMOVE 제외
    private List<Store> storeList = new ArrayList<>(); // 가게와 일대다 양방향
}
