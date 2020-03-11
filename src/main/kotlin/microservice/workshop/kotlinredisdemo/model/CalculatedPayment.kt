package microservice.workshop.kotlinredisdemo.model

import java.math.BigDecimal

data class CalculatedPayment(
    val amount: Double,
    val rate: Double,
    val years: Int,
    val payment: BigDecimal,
    val instance: String,
    val count: Long
)
