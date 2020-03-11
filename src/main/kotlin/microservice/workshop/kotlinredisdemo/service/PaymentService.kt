package microservice.workshop.kotlinredisdemo.service

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

@Service
class PaymentService {
    fun calculate(amount: Double, rate: Double, years: Int) =
        if (rate == 0.0) {
            calculateWithoutInterest(amount, years)
        } else {
            calculateWithInterest(amount, rate, years)
        }

    private fun calculateWithInterest(amount: Double, rate: Double, years: Int): BigDecimal {
        val monthlyRate = rate / 100.0 / 12.0
        val numberOfPayments = years * 12
        val payment = monthlyRate * amount / (1.0 - (1.0 + monthlyRate).pow((-numberOfPayments).toDouble()))
        return toMoney(payment)
    }

    private fun calculateWithoutInterest(amount: Double, years: Int): BigDecimal {
        val numberOfPayments = years * 12
        return toMoney(amount / numberOfPayments)
    }

    private fun toMoney(d: Double) =
        BigDecimal(d).setScale(2, RoundingMode.HALF_UP)
}
