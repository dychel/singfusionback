package com.singfusion.singfusion.service;
import com.singfusion.singfusion.entity.Role;
import com.singfusion.singfusion.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {

        role.setRoleName(role.getRoleName().toUpperCase());
        role.setDateCreate(new Date());
        role.setDateUpdate(new Date());

        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role) {

        Role roleToUpdate = roleRepository.findByIdRole(id);

        if (roleToUpdate == null)
            return null;

        roleToUpdate.setRoleName(role.getRoleName().toUpperCase());
        roleToUpdate.setDateUpdate(new Date());

        return roleRepository.save(roleToUpdate);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findByIdRole(id);
    }

    @Override
    public Role findDifferentRoleById(Long id) {
        return roleRepository.findByIdDifferentRole(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
