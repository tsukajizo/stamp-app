package net.tsukajizo.stampapp.task

sealed class Result<out S, out F> {
    data class Success<out S>(val result: S) : Result<S, Nothing>()
    data class Failure<out F>(val failure: F) : Result<Nothing, F>()
}