package hansung.mannayo.mannayoserverapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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