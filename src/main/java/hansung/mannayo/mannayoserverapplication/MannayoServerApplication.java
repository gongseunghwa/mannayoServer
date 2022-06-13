package hansung.mannayo.mannayoserverapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaAuditing // JPA Auditing 어노테이션을 모두 활성화 시킬 수 있도록 Application 클래스에 활성화 어노테이션 추가
@EnableSwagger2
@SpringBootApplication
public class MannayoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannayoServerApplication.class, args);
    }

    //패스워드 인코딩을 위한 PasswordEncoder 빈으로 등록
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}


//TODO
// Security, cascade
// DB에 있는 내용을 삭제하지 않되, Member의 AccountType과 게시물의 is_deleted를 이용하여 구분한다.
// Contains Tyme mismatch 해결해야함.