package com.example.homecookshop

import java.io.Serializable

data class MyData(var word:String, var meaning:String, var isOpen:Boolean, var food:String):Serializable {
}