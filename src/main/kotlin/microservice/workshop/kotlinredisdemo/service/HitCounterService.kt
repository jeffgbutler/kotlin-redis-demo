package microservice.workshop.kotlinredisdemo.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

interface HitCounterService {
    fun incrementCounter(): Long
    fun resetCount()
}

@Service
@Profile("!cloud")
class MemoryHitCounterService: HitCounterService {
    private var hitCount: Long = 0

    override fun incrementCounter() = ++hitCount

    override fun resetCount() {
        hitCount = 0
    }
}
