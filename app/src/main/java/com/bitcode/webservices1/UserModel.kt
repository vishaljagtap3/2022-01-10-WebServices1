package com.bitcode.webservices1

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

data class UserModel(
    var id : Int,
    var email : String,
    var firstName : String,
    var lastName : String,
    var avatar : String
)

