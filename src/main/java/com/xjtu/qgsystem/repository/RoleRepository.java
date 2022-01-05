package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getById(long id);
}
