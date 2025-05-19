package com.singfusion.singfusion.repository;

import com.singfusion.singfusion.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRoleName(String roleName);

	@Query("select role from Role role where role.id = :id")
	Role findByIdRole(@Param("id") Long id);

	@Query("select role from Role role where role.id != :id")
	Role findByIdDifferentRole(@Param("id") Long id);
}
