package microservice.workshop.kotlinredisdemo.http

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PaymentControllerTest {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private fun mockMvc() = webAppContextSetup(webApplicationContext).build()

    @Test
    fun testWithInterest() {
        with(mockMvc()) {
            perform(get("/resetCount"))
                .andExpect(status().`is`(HttpStatus.OK.value()))

            perform(get("/payment?amount=100000&rate=3.5&years=30"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.payment", `is`(449.04)))
                .andExpect(jsonPath("$.count", `is`(1)))
        }
    }

    @Test
    fun testZeroInterest() {
        with(mockMvc()) {
            perform(get("/resetCount"))
                .andExpect(status().`is`(HttpStatus.OK.value()))

            perform(get("/payment?amount=100000&rate=0&years=30"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.payment", `is`(277.78)))
                .andExpect(jsonPath("$.count", `is`(1)))
        }
    }

    @Test
    fun testThatHitCounterIncrements() {
        with(mockMvc()) {
            perform(get("/resetCount"))
                .andExpect(status().`is`(HttpStatus.OK.value()))

            perform(get("/payment?amount=100000&rate=3.5&years=30"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.payment", `is`(449.04)))
                .andExpect(jsonPath("$.count", `is`(1)))

            perform(get("/payment?amount=100000&rate=0&years=30"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.payment", `is`(277.78)))
                .andExpect(jsonPath("$.count", `is`(2)))
        }
    }
}
