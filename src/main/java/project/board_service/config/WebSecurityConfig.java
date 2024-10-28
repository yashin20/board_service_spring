package project.board_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    /*PasswordEncoder Bean 등록 - password 암호화 (방식 - BCryptPasswordEncoder)*/
    @Bean
    public static PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    /*WebSecurityCustomizer Bean 등록 - 정적 resources 접근을 위함*/
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .requestMatchers("/img/**", "/css/**", "/js/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/posts/new").authenticated()
                                .requestMatchers("/", "/members/login", "/members/join", "/posts/{postId}").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) ->
                        form
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/members/login")
                                .loginProcessingUrl("/login")
                                .failureUrl("/members/login?error=true")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .userDetailsService(customUserDetailsService)
                .logout(logout ->
                        logout
                                .logoutUrl("/logout") //로그아웃 처리 URL
                                .logoutSuccessUrl("/") //로그아웃 성공 후 리다이렉트 할 URL
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                )
                .csrf(csrf ->
                        csrf
                                .ignoringRequestMatchers("/api/**") // /api/** 경로에 대한 CSRF 보호를 비활성화
                );

        return http.build();
    }

}
