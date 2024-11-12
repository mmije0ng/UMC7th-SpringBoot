package com.umc.workbook.repository;

import com.umc.workbook.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findByExpiredAtIsNotNull();

}
