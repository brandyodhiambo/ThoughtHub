package com.brandyodhiambo.ThoughtHub.repository;

import com.brandyodhiambo.ThoughtHub.model.Role;
import com.brandyodhiambo.ThoughtHub.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName name);
}
