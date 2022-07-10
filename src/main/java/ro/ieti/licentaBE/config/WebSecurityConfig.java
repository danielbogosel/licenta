package ro.ieti.licentaBE.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ro.ieti.licentaBE.security.oauth2.CustomOAuth2UserService;
import ro.ieti.licentaBE.security.oauth2.Oauth2LoginSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomOAuth2UserService oAuth2UserService;
    @Autowired
    private Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return null;
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
