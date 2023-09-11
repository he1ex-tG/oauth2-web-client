package tacos.oauth2webclient.feign

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken

class IngredientsFeignClientConfiguration(
    private val oAuth2AuthorizedClientService: OAuth2AuthorizedClientService
) {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->
            val accessToken = getAccessToken()
            template.header("Authorization", "Bearer $accessToken")
        }
    }

    private fun getAccessToken(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        var token = ""
        if (authentication is OAuth2AuthenticationToken) {
            val clientRegistrationId = authentication.authorizedClientRegistrationId
            if (clientRegistrationId == "taco-admin-client") {
                val oAuth2AuthorizedClient =
                    oAuth2AuthorizedClientService.loadAuthorizedClient<OAuth2AuthorizedClient>(
                        clientRegistrationId,
                        authentication.name
                    )
                token = oAuth2AuthorizedClient.accessToken.tokenValue
            }
        }
        return token
    }
}