package br.com.talthur.librarysystem.security.repository;

import javax.management.relation.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByRole(String role);
}
