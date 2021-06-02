package br.com.talthur.librarysystem.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.talthur.librarysystem.models.Role;
import br.com.talthur.librarysystem.security.repository.UserRepository;

public class SSUserDetailsService implements UserDetailsService {


	private UserRepository userRepository;
	
	
	public SSUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			br.com.talthur.librarysystem.models.User user = userRepository.findByUserName(username);
			if(user == null) {
				return null;
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
		}
		catch(Exception a){
			throw new UsernameNotFoundException("User not found");
		}
	}
	
private Set<GrantedAuthority> getAuthorities(br.com.talthur.librarysystem.models.User user){
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
			authorities.add(grantedAuthority);
		}
		
		return authorities;
		
	}
	
	

}
