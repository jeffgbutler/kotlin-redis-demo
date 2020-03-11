package microservice.workshop.kotlinredisdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinRedisDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinRedisDemoApplication>(*args)
}
