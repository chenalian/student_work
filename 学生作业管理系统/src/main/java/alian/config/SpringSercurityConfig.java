package alian.config;

import alian.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringSercurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDatalis myUserDatalis;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
             http.authorizeRequests()
                     // 权限访问
                     .antMatchers("/teacher/**","/student/slefinfo")
                     .hasAuthority ("teacher")
                    .antMatchers("/student/**")
                    .hasAnyAuthority ("student")
                    .and()
                     .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                     .formLogin()
                     // 登录反文革
                    .loginPage("/login")// 登录路径
                     .loginProcessingUrl("/login/form")
                     .successHandler(myAuthenticationSuccessHandler)
                     .failureHandler(myAuthenticationFailHandler)
                    .and()
                     .sessionManagement().invalidSessionUrl("/login")
                     .and()
                     // 退出访问
                     .logout()
                     .logoutUrl("/logout")
//                     .logoutSuccessHandler(userLogoutSuccessHandler)
                     .deleteCookies("JSESSIONID")
                     .logoutSuccessUrl("/login")
                     .permitAll()
                     .and()
                    .authorizeRequests()
                     // 静态资源访问 基本页面访问
                    .antMatchers("/login","/login/indentycode").permitAll()
                    .antMatchers("/images/**","/css/**","/js/**","/font/**","/my_js/**","/webjars/**","/lib/**","/layui/**").permitAll()
                    .anyRequest()
                     .authenticated()
          .and()
                .csrf().disable();

        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDatalis).passwordEncoder(new BCryptPasswordEncoder());
    }
}
