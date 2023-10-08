package dev.aloyisius.App_Auth_Server.Security.AppUserDetails;

import dev.aloyisius.App_Auth_Server.Models.ApplicationUsers;
import dev.aloyisius.App_Auth_Server.Repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository userRepository;

    public AppUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(AppUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(""));
    }

    protected static class AppUserDetails extends ApplicationUsers implements UserDetails{
        public AppUserDetails(ApplicationUsers users) {
            super(users);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(this::getAuthority);
        }

        @Override
        public String getUsername() {
            return this.getEmail();
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
    }
}
