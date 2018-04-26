package net.tsukajizo.stampapp.data

data class Stamp(val id: Int, val label: String, val desc: String, var gatherDate: Long = NOT_GATHER_DATE) {
    companion object {
        const val NOT_GATHER_DATE: Long = 0L
    }

    val isGathered: Boolean
        get() = gatherDate != NOT_GATHER_DATE

    fun getStamp() {
        if (!isGathered) {
            gatherDate = System.currentTimeMillis()
        }
    }
}