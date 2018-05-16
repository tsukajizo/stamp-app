package net.tsukajizo.stampapp.task

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class Task<in Param, out R, out F> {
    abstract fun run(param: Param): Result<R, F>

    fun execute(onResult: (R) -> Unit, onFailure: (F) -> Unit, params: Param) {
        val job = async(CommonPool) { run(params) }
        launch(UI) {
            val result = job.await()
            when (result) {
                is Result.Success -> onResult.invoke(result.result)
                is Result.Failure -> onFailure.invoke(result.failure)
            }

        }
    }
}