package com.gustavo.docanalyzer.infrastructure.db.repository;

import com.gustavo.docanalyzer.infrastructure.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u " +
            "LEFT JOIN DocumentEntity d ON d.user.id = u.id AND d.uploadDate BETWEEN :startDate AND :endDate " +
            "WHERE u.registrationDate < :endDate " +
            "AND d.id IS NULL")
    List<UserEntity> findUsersByNoUploadWithinPeriod(LocalDateTime startDate, LocalDateTime endDate);
}
