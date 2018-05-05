package net.tsukajizo.stampapp.task

abstract class Task<T> {
    abstract fun execute(): T


}