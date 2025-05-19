package rksp.furniture.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RedirectController {

    @GetMapping("/")
    fun redirectToHome(): String {
        return "redirect:/home"
    }
}
