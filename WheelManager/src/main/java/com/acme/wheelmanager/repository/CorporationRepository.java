package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation,Long> {
}
