package com.ethbek.mezion.inventory.service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static com.ethbek.mezion.inventory.service.models.dto.Constants.*;
import static springfox.documentation.builders.PathSelectors.regex;

@Profile({"development", "staging"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String DESCRIPTION = "This is an Inventory System used for managing inventory in the Fuel Sector";

    private ApiKey apiKey() {
        return new ApiKey(BEARER, AUTHORIZATION_HEADER, HEADER);
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths( PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope( "global", "accessEverything" );
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList( new SecurityReference( BEARER, authorizationScopes ) );
    }

    @Bean
    public Docket userEndpoint(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ethbek.mezion.inventory.service"))
                .paths(regex("/.*"))
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        return new ApiInfoBuilder()
                .title("Meizon Inventory Service")
                .description( DESCRIPTION)
                .version("1.0.1")
                .licenseUrl("https://opensource.org/licenses/Apache-2.0")
                .license("Apache Licence Version 2.0")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openApi = new OpenAPI()
                .info(getInfo());
            addSecurity(openApi);
        return openApi;
    }

    private Info getInfo() {
        return new Info()
                .title("Meizon Inventory Service")
                .description(DESCRIPTION)
                .version("1.0.1");
    }
    private void addSecurity(OpenAPI openApi) {
        Components components = createComponents();
        SecurityRequirement securityItem = new SecurityRequirement().addList(SCHEME_NAME);

        openApi
                .components(components)
                .addSecurityItem(securityItem);
    }

    private Components createComponents() {
        Components components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());

        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .in(SecurityScheme.In.HEADER)
                .bearerFormat(AUTHORIZATION_FLOW)
                .description(AUTHORIZATION_DESCRIPTION)
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }


}
