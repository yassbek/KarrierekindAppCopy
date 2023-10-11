package com.karrierekind.karrierekindapp.repository;

import com.karrierekind.karrierekindapp.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {
}
