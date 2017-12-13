package app.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/manufacturers").permitAll()
                .antMatchers("/manufacturer/{id:[\\d+]}").permitAll()
                .antMatchers("/manufacturer/{id:[\\d+]}/products").permitAll()
                .antMatchers("/manufacturer/create").hasAuthority("USER")
                .antMatchers("/manufacturer/{id:[\\d+]}/*").hasAuthority("ADMIN")
                .antMatchers("/manufacturer/{id:[\\d+]}/*").hasAuthority("ADMIN")

                .antMatchers("/products").permitAll()
                .antMatchers("/product/{id:[\\d+]}").permitAll()
                .antMatchers("/product/create").hasAuthority("USER")
                .antMatchers("/product/{id:[\\d+]}/*").hasAuthority("ADMIN")
                .antMatchers("/product/{id:[\\d+]}/*").hasAuthority("ADMIN")

                .antMatchers("/users").permitAll()

                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()

                .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successForwardUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**");
    }
}
