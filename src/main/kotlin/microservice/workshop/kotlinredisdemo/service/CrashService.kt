package microservice.workshop.kotlinredisdemo.service

import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

@Service
class CrashService {
    private val executor = Executors.newScheduledThreadPool(1)

    // calls System.exit after a 2 second delay
    fun crashIt() {
        executor.schedule({ exitProcess(22) }, 2000, TimeUnit.MILLISECONDS)
    }
}
