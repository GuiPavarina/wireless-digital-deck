package com.puc.wireless.digital.desk.hash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puc.wireless.digital.desk.hash.domain.Hash;

@Repository
public interface HashRepository extends JpaRepository<Hash, Long> {

    public Optional<Hash> findByUser(String User);

    public Optional<Hash> findByHash(String hash);

}
