package com.singfusion.singfusion.service;
import com.singfusion.singfusion.entity.Role;
import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    Role updateRole(Long id, Role role);
    Role findRoleById(Long id);

    Role findDifferentRoleById(Long id);

    List<Role> listRoles();
    void deleteRoleById(Long id);
}
