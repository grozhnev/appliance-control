package com.github.grozhnev.appliancecontrol.repository;

import com.github.grozhnev.appliancecontrol.entity.ApplianceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends JpaRepository<ApplianceEntity, Long> {
}
