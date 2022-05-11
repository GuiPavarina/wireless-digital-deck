package com.puc.wireless.digital.desk.shortcut.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.puc.wireless.digital.desk.shortcut.domain.Shortcut;

@Repository
public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {

    @Query(value = "select * from shortcuts s where s.shortcut_user = :user", nativeQuery = true)
    public Optional<List<Shortcut>> findByUser(@Param("user") String user);

    @Modifying
    @Transactional
    @Query(value = "delete from shortcuts s where s.shortcut_user = :user and s.shortcut_order = :order", nativeQuery = true)
    public void deleteByUserAndOrder(@Param("user") String user, @Param("order") Long order);

    @Modifying
    @Transactional
    @Query(value = "delete from shortcuts s where s.shortcut_user = :user", nativeQuery = true)
    public void deleteByUser(@Param("user") String user);

}
