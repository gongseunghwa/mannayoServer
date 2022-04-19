package hansung.mannayo.mannayoserverapplication.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//jwt가 유효한지 인증용 필터
public class JwtAuthenticationFilter extends GenericFilterBean {

    //jwt 토큰 생성/ 검증 모듈 클래스
    private JwtTokenProvider jwtTokenProvider;

    //jwt 토큰 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider  = jwtTokenProvider;
    }

    //Request로 들어오는 토큰의 유효성 검증
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        //Ruquest의 Header에서 token 파싱
        if(token != null && jwtTokenProvider.validateToken(token)){
            //토콘의 유효성/ 만료일자 확인
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            //토큰으로 인증정보 확인
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
