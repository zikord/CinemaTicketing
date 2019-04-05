package com.kordulup.ticketing.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kordulup.ticketing.entities.Role;
import com.kordulup.ticketing.entities.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	 Optional<Role> findByName(RoleName roleName);

}
