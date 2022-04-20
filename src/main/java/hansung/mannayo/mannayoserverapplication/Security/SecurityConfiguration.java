package hansung.mannayo.mannayoserverapplication.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity
                .httpBasic().disable() // rest api 이므로 기본설정 x
                .csrf().disable() // rest api 이므로 필요 x
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// jwt토큰으로 생성하므로 session은 필요 x
                .and()
                    .authorizeRequests()
                        .antMatchers("/signin").permitAll()
                        .antMatchers("/signup").permitAll()
                        .antMatchers("/members/findMyPasswordByEmail").permitAll()
                        .antMatchers("/members/findMyAccountByNickname").permitAll()
                        .antMatchers("/members/findMyAccountByPhoneNumber").permitAll()
                        .anyRequest().hasRole("USER")
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // Jwt토큰 필터를 아이디 패스워드 인증 전에 넣는다.

    }

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger-ui/**");
    }
}
