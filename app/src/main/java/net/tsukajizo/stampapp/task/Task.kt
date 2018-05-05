package net.tsukajizo.stampapp.task

interface TaskSuccessListener<in T> {
    fun onSuccess(t: T) {}
}