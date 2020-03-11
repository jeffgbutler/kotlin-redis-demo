package microservice.workshop.kotlinredisdemo.http

import microservice.workshop.kotlinredisdemo.service.HitCounterService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/resetCount")
class ResetHitCounterController(private val hitCounterService: HitCounterService) {
    @GetMapping
    fun reset() = hitCounterService.resetCount()
}

