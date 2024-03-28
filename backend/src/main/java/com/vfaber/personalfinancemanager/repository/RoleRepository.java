package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
