package com.xjtu.qgsystem.component;

import cn.dev33.satoken.stp.StpInterface;
import com.xjtu.qgsystem.entity.Permission;
import com.xjtu.qgsystem.entity.RolePermission;
import com.xjtu.qgsystem.entity.UserRole;
import com.xjtu.qgsystem.repository.PermissionRepository;
import com.xjtu.qgsystem.repository.RolePermissionRepository;
import com.xjtu.qgsystem.repository.RoleRepository;
import com.xjtu.qgsystem.repository.UserRoleRepository;
import com.xjtu.qgsystem.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        long userId = TokenUtil.getInstance().getUserIdFromToken((String) loginId);

        // 获取所有该用户的角色
        List<UserRole> userRoles = userRoleRepository.getByUserId(userId);
        for (UserRole userRole : userRoles) {
            // 获取所有该角色下的权限
            List<RolePermission> rolePermissions = rolePermissionRepository.getByRoleId(userRole.getRoleId());

            for (RolePermission rolePermission : rolePermissions) {
                List<Permission> permissions = permissionRepository.getPermissionsById(rolePermission.getPermissionId());

                // 将权限名添加到返回结果中
                for (Permission permission : permissions) {
                    permissionList.add(permission.getPermissionName());
                }
            }
        }

        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleList = new ArrayList<>();
        long userId = TokenUtil.getInstance().getUserIdFromToken((String) loginId);

        // 获取所有该用户的角色
        List<UserRole> userRoles = userRoleRepository.getByUserId(userId);
        for (UserRole userRole : userRoles) {
            roleList.add(roleRepository.findById(userRole.getRoleId()).get().getRoleName());
        }

        return roleList;
    }
}
