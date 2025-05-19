package rksp.furniture

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UsedFurnitureApplication

fun main(args: Array<String>) {
    SpringApplication.run(UsedFurnitureApplication::class.java, *args)
}