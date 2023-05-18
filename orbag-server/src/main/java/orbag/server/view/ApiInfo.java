package orbag.server.view;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(info = @Info(title = "orbag API"))
/*
@SecuritySchemes({
        @SecurityScheme(name = ApiInfo.API_TOKEN_SCHEMA_NAME, description = "Authentication via X-Api-Token header", type = SecuritySchemeType.APIKEY,in = SecuritySchemeIn.HEADER,paramName = "X-Api-Token")
})
 */
public class ApiInfo {

    public static final String API_TOKEN_SCHEMA_NAME="X-Api-Token";
}
