package tacos.oauth2webclient.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "taco-authorization-server")
data class TacoAuthorizationServerProperties(
    var clientId: String = "",
    var clientSecret: String = ""
)
