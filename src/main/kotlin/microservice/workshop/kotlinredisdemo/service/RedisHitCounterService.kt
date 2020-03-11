package microservice.workshop.kotlinredisdemo.service

import org.springframework.context.annotation.Profile
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
@Profile("cloud")
class RedisHitCounterService(private val redisTemplate: RedisTemplate<String, Int>) : HitCounterService {

    override fun incrementCounter() =
        with(redisTemplate) {
            opsForValue().setIfAbsent(REDIS_KEY, DEFAULT_VALUE)
            opsForValue().increment(REDIS_KEY)!!
        }

    override fun resetCount() =
        redisTemplate.opsForValue().set(REDIS_KEY, DEFAULT_VALUE)

    companion object {
        const val REDIS_KEY = "payment-calculator"
        const val DEFAULT_VALUE = 5000
    }
}
