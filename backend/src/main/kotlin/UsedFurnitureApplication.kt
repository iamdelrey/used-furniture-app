package rksp.furniture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class UsedFurnitureApplication

fun main(args: Array<String>) {
    runApplication<UsedFurnitureApplication>(*args)
}
