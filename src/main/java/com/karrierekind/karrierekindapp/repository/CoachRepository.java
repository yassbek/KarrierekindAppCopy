package com.karrierekind.karrierekindapp.repository;

import com.karrierekind.karrierekindapp.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}