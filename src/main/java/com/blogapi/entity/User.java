package com.blogapi.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name= "users")
@NoArgsConstructor @Getter
@Setter
public class User implements UserDetails{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	@NotBlank
	@Size(min = 2, max = 200, message = "  name should be between min 2 char and maximum 200 char")
	@Column(name="user_id",nullable=false,length=100)
	private String name;
	@Column(unique=true)
	@Email
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String password;
	@NotNull
	@NotBlank
	private String about;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinTable(name = "user_role")
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getRoleName())).toList();
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", posts=" + posts + ", roles=" + roles + "]";
	}
	
}
