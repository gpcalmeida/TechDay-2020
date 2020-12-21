package com.devcamp.domain

abstract class UseCase<out T> {
    abstract fun execute() : T
}
