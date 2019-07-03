package testSecond.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import testSecond.doservice.AuthenService;
import testSecond.mapper.UserRepository;


@EnableWebSecurity
public class SecurityConfigAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定义授权规则  spring.io
        http
                //发出一个请求拦截
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/failPage"
                )
                .permitAll()
                .antMatchers(
                        "/successForAll"
                )
                .hasAnyRole(
                        "ROLE_ADMIN",
                        "ROLE_USER")
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .successForwardUrl("/successForAll")
                .failureForwardUrl("/failPage")
                .permitAll()
                .and()
                .userDetailsService(authenService())
                .logout()
                .permitAll()
                .and().csrf().disable();
    }



    @Bean
    public AuthenService authenService() {
        AuthenService authenService = new AuthenService();
        authenService.setUserRepository(userRepository);
        return authenService;
    }

    /**
     * 密码加密(可自定义加密方式)
     */
    @Bean
    public  PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
