package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    List<RolePermission> getByRoleId(long roleId);
}
