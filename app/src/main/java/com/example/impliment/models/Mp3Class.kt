package com.example.impliment.models

class Mp3Class {
    var mp3_name:String?=null
    var name_se:String?=null
    var info:String?=null
    var img:ImgClass?=null
    constructor(mp3_name:String,name_se:String,info:String,img:ImgClass){
        this.mp3_name = mp3_name
        this.name_se = name_se
        this.info = info
        this.img= img
    }
}