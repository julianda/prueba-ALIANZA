package co.com.alianza.fiduciaria;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SpringBootApplication
@Configuration
@EntityScan(basePackageClasses = { AlianzaApplication.class, Jsr310JpaConverters.class })
public class AlianzaApplication {

	public static void main(String[] args) { SpringApplication.run(AlianzaApplication.class, args);}

		@Bean
		public OpenAPI customOpenAPI() {
			return new OpenAPI().info(new Info().title("Alianza API")
							.description("Alianza API")
							.version("1.0"));
		}

	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}

	}
