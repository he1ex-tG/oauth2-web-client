package tacos.oauth2webclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Oauth2WebClientApplication

fun main(args: Array<String>) {
	runApplication<Oauth2WebClientApplication>(*args)
}
