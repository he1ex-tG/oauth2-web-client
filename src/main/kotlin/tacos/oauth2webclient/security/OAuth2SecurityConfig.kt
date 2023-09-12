package tacos.oauth2webclient.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
import org.springframework.security.oauth2.core.oidc.OidcScopes

@Configuration
class OAuth2SecurityConfig(
    private val authServProp: TacoAuthorizationServerProperties
) {

    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(tacoAdminClientRegistration())
    }

    private fun tacoAdminClientRegistration(): ClientRegistration {
        return ClientRegistration.withRegistrationId("taco-admin-client")
            .clientId(authServProp.clientId)
            .clientSecret(authServProp.clientSecret)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .redirectUri("http://localhost:9090/login/oauth2/code/taco-admin-client")
            .clientName("Taco authorization server")
            .scope("write:ingredients", "delete:ingredients", OidcScopes.OPENID)
            .authorizationUri("http://authserver:9000/oauth2/v1/authorize")
            .tokenUri("http://authserver:9000/oauth2/v1/token")
            .userInfoUri("http://authserver:9000/connect/v1/userinfo")
            .jwkSetUri("http://authserver:9000/oauth2/v1/jwks")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .build()
    }
}