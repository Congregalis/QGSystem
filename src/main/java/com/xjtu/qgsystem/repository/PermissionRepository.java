package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> getPermissionsById(long id);
}
