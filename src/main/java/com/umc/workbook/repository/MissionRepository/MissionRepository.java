package com.umc.workbook.repository.MissionRepository;

import com.umc.workbook.domain.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    Page<Mission> findAllMissionByStoreId(Long storeId, Pageable pageable);
}
