package com.tammam.secure_notes.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tammam.secure_notes.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private Long id;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private boolean is2faEnabled;
	private Collection<?extends GrantedAuthority> authorities;
	public UserDetailsImpl(Long id, String username, String email, String password, boolean is2faEnabled,
			Collection<? extends GrantedAuthority> authorities) {
		//super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.is2faEnabled = is2faEnabled;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl buid(User user)
	{
	GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName().name());
	return new UserDetailsImpl (user.getUserId(),user.getUserName(),user.getEmail(),user.getPassword(),user.isIstwoFactorEnabled(),
			List.of(authority));
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated mserethod stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return  username;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean is2faEnabled() {
        return is2faEnabled;
    }

    @Override
    public boolean equals(Object o) {
    
    		
    if(o==null || o.getClass()!=getClass()) return false;
    if(this ==o) return true;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(user.id,id);
    		
    		
    }
}
	


