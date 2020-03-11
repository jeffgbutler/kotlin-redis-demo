package microservice.workshop.kotlinredisdemo.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentServiceTests {
    @Test
    fun testNoInterest() {
        val payment = PaymentService().calculate(120000.0, 0.0, 10)
        assertThat(payment.toDouble()).isEqualTo(1000.00)
    }

    @Test
    fun testWithInterest() {
        val payment = PaymentService().calculate(120000.0, 3.5, 30)
        assertThat(payment.toDouble()).isEqualTo(538.85)
    }
}
