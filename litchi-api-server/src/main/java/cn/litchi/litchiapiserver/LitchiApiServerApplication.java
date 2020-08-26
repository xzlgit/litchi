package cn.litchi.litchiapiserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication()
@EnableFeignClients("cn.litchi.rpc")
@EnableZuulProxy
@RestController
@MapperScan("cn.litchi.model.mapper")
public class LitchiApiServerApplication {

    @RequestMapping("/health")
    public String health(){
        return "health";
    }
    public static void main(String[] args) {
        SpringApplication.run(LitchiApiServerApplication.class, args);
    }

}
