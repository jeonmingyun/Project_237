package com.coram.spy237.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlin.coroutines.coroutineContext

class Utils {
    companion object {
        fun onToast(context: Context, msg: Any) {
            Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show()
        }

        fun onLog(msg: Any) {
            Log.e("dddddddd", msg.toString())
        }
    }
}