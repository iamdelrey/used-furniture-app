package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080

    embeddedServer(Netty, port) {
        routing {
            get("/") {
                call.respondText("Hello from Spring on Render!", ContentType.Text.Plain)
            }
        }
    }.start(wait = true)
}
