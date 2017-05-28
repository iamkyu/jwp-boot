package io.iamkyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan({"io.iamkyu", "support"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class JwpSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwpSpringBootApplication.class, args);
    }
}
