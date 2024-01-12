package com.android.applemarket

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.applemarket.ConstVar
import com.android.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.System.exit

class DetailActivity: AppCompatActivity() {
    private var productInfo : Product? = null
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private var isLike = false
    private val itemPosition by lazy{intent.getIntExtra(ConstVar.PRODUCT_INDEX,0)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ConstVar.PRODUCT_OBJECT, Product::class.java)
        } else {
            intent.getParcelableExtra<Product>(ConstVar.PRODUCT_OBJECT)
        }

        binding.tvTitle.text = productInfo?.pname
        binding.tvContent.text = productInfo?.pcontent
        binding.tvAddress.text = productInfo?.address

        val deciamlFormat = DecimalFormat("#,###")
        val changedPrice = deciamlFormat.format(productInfo?.price)
        binding.tvDetailPrice.text = "${changedPrice} 원"

        binding.ivMainImg.setImageResource(productInfo?.imgsrc!!)
        binding.tvSeller.text = productInfo?.seller

        binding.ivBack.setOnClickListener {
            exit()
        }

        binding.ivLikeIcon.setOnClickListener {
            if(!isLike){
                binding.ivLikeIcon.setImageResource(R.drawable.heartredfill)
                Snackbar.make(it,"관심목록에 추가됐습니다.",Snackbar.LENGTH_SHORT).show()
                isLike=true
            } else {
                binding.ivLikeIcon.setImageResource(R.drawable.heart)
                Snackbar.make(it,"관심목록에 삭제됐습니다.",Snackbar.LENGTH_SHORT).show()
                isLike=false
            }

        }

        isLike= productInfo?.isLike == true
        binding.ivLikeIcon.setImageResource(
            if(isLike){
                R.drawable.heartredfill
            }else{
                R.drawable.heart
            }
        )



    }

    fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(ConstVar.PRODUCT_INDEX, itemPosition)
            putExtra( ConstVar.IS_LIKE, isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exit()
    }
}