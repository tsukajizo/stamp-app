package net.tsukajizo.stampapp.util

import android.net.Uri
import net.tsukajizo.stampapp.error.ParseCodeException
import java.lang.Integer.parseInt

class AppScheme {
    companion object {
        const val STAMP_ID = "id"
        const val UNKNOWN_STAMP_ID = -1
    }
}

fun parseIdFromCode(code: String): Int {
    val uri: Uri = Uri.parse(code)
    val stampId = uri.getQueryParameter(AppScheme.STAMP_ID)
    try {
        return parseInt(stampId)
    } catch (e: Exception) {
        throw ParseCodeException("fail parse code", e)
    }

}