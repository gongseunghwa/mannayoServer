package hansung.mannayo.mannayoserverapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MannayoServerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MannayoServerApplication.class);
        app.run(args);
    }

}
