package com.bitcode.webservices1

import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread(var handler: Handler) : AsyncTask<Any, Any, UserModel>() {

    override fun doInBackground(vararg params: Any?): UserModel {
        //Util.makeSimpleWebRequest()
        return Util.getUserDetails(params[0].toString().toInt())
    }

    override fun onPostExecute(result: UserModel?) {
        super.onPostExecute(result)
        if(result != null) {
            var message = Message()
            message.obj = result
            handler.sendMessage(message)
        }

    }
}