package net.tsukajizo.stampapp.task

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class Task<in Param, out Result> {
    abstract fun run(param: Param): Result

    fun execute(onResult: (Result) -> Unit, onFailure: (Throwable) -> Unit,
                params: Param) {
        launch(UI) {
            try {
                onResult.invoke(async(CommonPool) { run(params) }.await())
            } catch (e: Throwable) {
                onFailure.invoke(e)
            }
        }
    }
}