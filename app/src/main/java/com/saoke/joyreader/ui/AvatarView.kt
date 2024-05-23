package com.saoke.joyreader.ui

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import com.tencent.mmkv.MMKV

class AvatarView(context: Context, attrs: AttributeSet? = null) :
    ShapeableImageView(context, attrs) {
    init {
        val avatarUrl = MMKV.defaultMMKV().decodeString(
            "avatar_url",
            "http://west2-work4-pany0593.oss-cn-shenzhen.aliyuncs.com/avatar/default-avatar.png"
        )!!
        Glide.with(this).load(avatarUrl).into(this)
        scaleType = ScaleType.CENTER_CROP
        shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCornerSizes(ShapeAppearanceModel.PILL)
            .build()
    }
}