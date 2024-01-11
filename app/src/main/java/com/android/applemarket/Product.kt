package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    var pnum :Int , //번호
    var imgsrc : Int , //이미지 파일명
    var pname : String , //상품명
    var pcontent : String ,  //상품 소개
    var seller : String , //판매자
    var price : Int , //가격
    var address : String, //주소
    var like : Int, //좋아요
    var chat : Int //채팅
) : Parcelable