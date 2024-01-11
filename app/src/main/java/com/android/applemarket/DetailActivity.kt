package com.android.applemarket

import android.graphics.drawable.BitmapDrawable
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity: AppCompatActivity() {
    private var productInfo : Product? = null
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productInfo = intent.getParcelableExtra<Product>("product")

        binding.tvTitle.text = productInfo?.pname
        binding.tvContent.text = productInfo?.pcontent
        binding.tvAddress.text = productInfo?.address

        val deciamlFormat = DecimalFormat("#,###")
        val changedPrice = deciamlFormat.format(productInfo?.price)
        binding.tvDetailPrice.text = changedPrice

        binding.ivMainImg.setImageResource(productInfo?.imgsrc!!)
        binding.tvSeller.text = productInfo?.seller

        binding.ivBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.ivLikeIcon.setOnClickListener {

            if(binding.ivLikeIcon.tag.toString()  == "heart" ){
                binding.ivLikeIcon.setImageResource(R.drawable.heartredfill)
                binding.ivLikeIcon.tag = "heartredfill"
                Snackbar.make(it,"관심목록에 추가됐습니다.",Snackbar.LENGTH_SHORT).show()
            } else {
                binding.ivLikeIcon.setImageResource(R.drawable.heart)
                binding.ivLikeIcon.tag = "heart"
            }

        }
    }




}