package com.karrierekind.karrierekindapp.repository;

import com.karrierekind.karrierekindapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}