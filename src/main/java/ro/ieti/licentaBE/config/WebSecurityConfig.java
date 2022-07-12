package ro.ieti.licentaBE.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ro.ieti.licentaBE.entity.CustomerUser;
import ro.ieti.licentaBE.security.oauth2.CustomOAuth2UserService;
import ro.ieti.licentaBE.security.oauth2.Oauth2LoginSuccessHandler;
import ro.ieti.licentaBE.service.CustomUserService;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomOAuth2UserService oAuth2UserService;
    @Autowired
    private Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    private final CustomUserService userService;

    @Autowired
    public WebSecurityConfig(CustomUserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) email -> {
            Optional<CustomerUser> user = userService.findUserByEmail(email);
            if (!user.isPresent()) {
                throw new UsernameNotFoundException("No user found with email: " + email);
            }
            return (UserDetails) user.get();
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/customer/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint().userService(oAuth2UserService)
                .and()
                .successHandler(oauth2LoginSuccessHandler)
                .and()
                .logout().permitAll()
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository());
    }

    private PersistentTokenRepository persistentTokenRepository() {
        return null;
    }


}
