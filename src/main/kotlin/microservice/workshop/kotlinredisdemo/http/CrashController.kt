package microservice.workshop.kotlinredisdemo.http

import io.swagger.annotations.ApiOperation
import microservice.workshop.kotlinredisdemo.service.CrashService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/crash")
class CrashController(private val crashService: CrashService) {

    @ApiOperation("Warning! The application will crash 2 seconds after this method is called")
    @GetMapping
    fun crashIt(): String {
        crashService.crashIt()
        return "OK"
    }
}

