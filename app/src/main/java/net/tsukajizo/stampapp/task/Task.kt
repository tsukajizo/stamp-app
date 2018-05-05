package net.tsukajizo.stampapp.task

interface TaskListener<in T> {
    fun onSuccess(result: T) {}
}