package yw.assignment.iodigital.ioplatform;

import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "yw.assignment.iodigital.ioplatform")
@EntityScan("yw.assignment.iodigital.ioplatform.model")
@EnableSwagger2
@EnableCaching
public class TedTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TedTalkApplication.class, args);
	}
	
	@Bean
	public Docket api() {                
	    Docket d=new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("yw.assignment.iodigital.ioplatform"))
	      .paths(PathSelectors.any())
	      .build();
	    d.apiInfo(apiInfo());
	    d.tags(new Tag("TedTalk Platform", ""));
	    d.produces(new HashSet<String>() {
	    	{
	    		add("application/json");
	    	}
	    });
	    
	    return d;
	}

	public static ApiInfo apiInfo() {	    
	    return new ApiInfoBuilder().title("TedTalk Platform").description("TedTalk Plaform by Yao Wang for IODigital")
	    		.version("1.0").build();
	}

}
