package net.tsukajizo.stampapp.task

interface TaskSuccessListener<in T> {
    fun onSuccess(result: T) {}
}