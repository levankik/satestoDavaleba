package softlab.satestodavalebaback;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@OpenAPIDefinition(
		info = @Info(
				title = "SatestoDavalebaBack",
				version = "v1",
				description = "SatestoDavalebaBack API"
		)
)
@SpringBootApplication
public class SatestoDavalebaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatestoDavalebaBackApplication.class, args);
	}

}
