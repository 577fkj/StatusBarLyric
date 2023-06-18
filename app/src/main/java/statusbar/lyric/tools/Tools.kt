/*
 * StatusBarLyric
 * Copyright (C) 2021-2022 fkj@fkj233.cn
 * https://github.com/577fkj/StatusBarLyric
 *
 * This software is free opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by 577fkj.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 * <https://github.com/577fkj/StatusBarLyric/blob/main/LICENSE>.
 */

@file:Suppress("DEPRECATION")

package statusbar.lyric.tools

import android.annotation.SuppressLint
import android.content.*
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import de.robv.android.xposed.XSharedPreferences
import statusbar.lyric.BuildConfig
import java.io.DataOutputStream
import java.util.*


@SuppressLint("StaticFieldLeak")
object Tools {
    fun goMainThread(callback: (() -> Unit)? = null) {
        callback?.let {
            val handler = Handler(Looper.getMainLooper())
            handler.post(callback)
        }
    }
    fun String.filterClassName(): Boolean {
        val filterList = arrayListOf("controlcenter", "image", "keyguard")
        filterList.forEach {
            if (contains(it, true)) return false
        }
        return this != TextView::class.java.name
    }
    fun String.dispose()=this.replace(" ", "").replace("\n", "").replace("\t", "").replace("0", "")

    fun getPref(key: String): XSharedPreferences? {
        val pref = XSharedPreferences(BuildConfig.APPLICATION_ID, key)
        return if (pref.file.canRead()) pref else null
    }

    @SuppressLint("WorldReadableFiles")
    @Suppress("DEPRECATION")
    fun getSP(context: Context, key: String?): SharedPreferences? {
        return context.createDeviceProtectedStorageContext().getSharedPreferences(key, Context.MODE_WORLD_READABLE)
    }


    fun voidShell(command: String, isSu: Boolean) {
        try {
            if (isSu) {
                val p = Runtime.getRuntime().exec("su")
                val outputStream = p.outputStream
                val dataOutputStream = DataOutputStream(outputStream)
                dataOutputStream.writeBytes(command)
                dataOutputStream.flush()
                dataOutputStream.close()
                outputStream.close()
            } else {
                Runtime.getRuntime().exec(command)
            }
        } catch (ignored: Throwable) {
        }
    }


    inline fun <T> T?.isNotNull(callback: (T) -> Unit): Boolean {
        if (this != null) {
            callback(this)
            return true
        }
        return false
    }

    inline fun Boolean.isNot(callback: () -> Unit) {
        if (!this) {
            callback()
        }
    }

    inline fun Any?.isNull(callback: () -> Unit): Boolean {
        if (this == null) {
            callback()
            return true
        }
        return false
    }

    fun Any?.isNull() = this == null

    fun Any?.isNotNull() = this != null
}
