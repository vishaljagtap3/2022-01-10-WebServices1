package com.bitcode.webservices1

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bitcode.webservices1.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activity_main,
            null,
            false
        )

        setContentView(binding.root)

        binding.btnGetUserData.setOnClickListener {
            //code to make http request

            WebThread(UserHandler())
                .execute(binding.edtUserId.text.toString().toInt())
        }
    }

    inner class UserHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg != null) {
                this@MainActivity.userModel = msg.obj as UserModel
                binding.userModel = userModel


            }
        }
    }


}

@BindingAdapter("web_url")
fun setWebUrlToImageView(imageView : ImageView, url : String?) {
    Log.e("tag", "url: ${url}")
    if(url != null) {
        Picasso.get()
            .load(Uri.parse(url))
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }
}