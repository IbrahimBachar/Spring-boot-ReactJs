package com.clinic_system.clinic_alshifa.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long>{

    Optional<MyAppUser> findByUsername(String username);

    Optional<MyAppUser> findByEmail(String email);

    Page<MyAppUser> findAll(Pageable pageable);
}



