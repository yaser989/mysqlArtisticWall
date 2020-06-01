package org.openup.repo;

import java.util.List;
import java.util.Optional;

import org.openup.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	
	List<Role> findRoleById(Long id);
}
