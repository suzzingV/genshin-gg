package com.suzzingv.genshingg.security.User;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }


    @Override //사용자가 가지고 있는 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
        //SimpleGrantedAuthority : "user"라는 간단한 권한 나타냄
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override //반드시 고유해야 한다
    public String getUsername() {
        return email;
    }

    @Override //계정이 만료되었는지 확인, 만료되지 않았으면 true
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //계정 잠금되었는지 확인, 잠금 안되었으면 true
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //패스워드 만료 여부 확인, 만료 안되었으면 true
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정 사용 가능한지 확인, 사용가능하면 true
    public boolean isEnabled() {
        return true;
    }
}
