package com.umc.workbook.repository.MissionRepository;

import com.umc.workbook.domain.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    // 가게 아이디와 일치하는 모든 미션을 페이지로 반환
    Page<Mission> findAllMissionByStoreId(Long storeId, Pageable pageable);
}
