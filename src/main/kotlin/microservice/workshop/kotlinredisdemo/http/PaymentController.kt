package microservice.workshop.kotlinredisdemo.http

import microservice.workshop.kotlinredisdemo.model.CalculatedPayment
import microservice.workshop.kotlinredisdemo.service.HitCounterService
import microservice.workshop.kotlinredisdemo.service.PaymentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/payment")
class PaymentController(private val hitCounterService: HitCounterService, private val paymentService: PaymentService) {
    @Value("\${cloud.application.instance_index:local}")
    private lateinit var instance: String

    @GetMapping
    fun calculatePayment(@RequestParam("amount") amount: Double, @RequestParam("rate") rate: Double,
                         @RequestParam("years") years: Int): CalculatedPayment {

        val payment = paymentService.calculate(amount, rate, years)

        logger.debug("Calculated payment of {} for input amount: {}, rate: {}, years: {}",
            payment, amount, rate, years)

        return CalculatedPayment(amount, rate, years, payment, instance, hitCounterService.incrementCounter())
    }

    companion object {
        val logger = LoggerFactory.getLogger(PaymentController::class.java)
    }
}
