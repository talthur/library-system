package br.com.talthur.librarysystem.security.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.talthur.librarysystem.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserName(String username); 
}
