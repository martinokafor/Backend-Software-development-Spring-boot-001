package customer.com.profile.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;

@OpenAPIDefinition(info = @Info(
        description = "Open API for Customer Vehicle application",
        title = "Open API Specification",
        version = "V1.0"
),
        servers = {
        @Server(
                description = "DEV",
                url = "http://localhost:8080"

        ),
        @Server(
                description = "PROD",
                url = "http://localhost:8082"

        )

        }

)
public class OpenAPIConfig {
}
