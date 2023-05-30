package orbag.server;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(info = @Info(title = "orbag API", version = "v0-alpha"))
@SecuritySchemes({
        @SecurityScheme(name = ApiInfo.JWT, description = "Authentication via X-Api-Token header", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
public class ApiInfo {

    public static final String JWT="JWT";
}
