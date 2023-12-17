package com.suzzingv.genshingg.security.config;

import com.suzzingv.genshingg.security.User.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailService userDetailService;

    @Bean //스프링 시큐리티 기능 비활성화
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring() //requestMathers에 대해 인증, 인가 사용 안함
                .requestMatchers(toH2Console()) //콘솔에 접근할 때마다 인증해야되면 번거롭다. toH2Console()을 사용하면 H2 Console의 URL을 application.yml을 통해 디폴트가 아닌 다른 값으로 변경하더라도 해당 클래스를 고칠 필요가 없다.
                .requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/login", "/signup", "/user").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .csrf(AbstractHttpConfigurer::disable); //csrf.disable() : csrf 설정 비활성화. 원래는 csrf 공격 방지하기 위해 활성화하는 게 좋음
        return http.build();
    }

    @Bean //인증 관리자 관련 설정. 사용자 정보 가져올 서비스 재정의, 인증 방법 설정
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService); //사용자 정보 가져올 서비스 정의. 이때 설정하는 서비스 클래스는 반드시 UserDetailsService 상속받은 클래스여야 함
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder()); //비밀번호 암호화 위한 인코더 설정
        return daoAuthenticationProvider;
    }

    @Bean //패스워드 인코더로 사용할 빈 등록
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
