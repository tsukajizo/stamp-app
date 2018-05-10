package net.tsukajizo.stampapp.data.asset

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StampJsonParserTest {
    @Test
    fun fromAssetFile() {
        val context = InstrumentationRegistry.getContext()
        val filename = "stamp_list_for_test.json"
        val stamps = StampJsonParser(context).fromAssetFile(filename)
        val stamp1 = stamps[0]
        assertEquals(1, stamp1.id)
        assertEquals("test1", stamp1.label)
        assertEquals("hogehoge", stamp1.desc)
        assertEquals(11.111111, stamp1.latitude, 0.0)
        assertEquals(11.111111, stamp1.longitude, 0.0)

        val stamp2 = stamps[1]
        assertEquals(2, stamp2.id)
        assertEquals("test2", stamp2.label)
        assertEquals("fugafuga", stamp2.desc)
        assertEquals(22.2222222, stamp2.latitude, 0.0)
        assertEquals(22.2222222, stamp2.longitude, 0.0)
    }
}