package microservice.workshop.kotlinredisdemo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer

@Configuration
@Profile("cloud")
class CloudConfiguration(private val redisFactory: RedisConnectionFactory) {

    @Bean
    fun redisTemplate() =
        RedisTemplate<String, Int>().apply {
            setConnectionFactory(redisFactory)
            valueSerializer = GenericToStringSerializer(Int::class.java)
        }
}
