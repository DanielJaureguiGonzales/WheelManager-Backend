package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.Publicitie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PublicitieRepository extends JpaRepository<Publicitie,Long> {
    Page<Publicitie> findByCorporationId(Long corporationId, Pageable pageable);
    Optional<Publicitie> findByIdAndCorporationId(Long id, Long corporationId);
}
