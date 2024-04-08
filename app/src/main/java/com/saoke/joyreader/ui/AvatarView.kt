package com.saoke.joyreader.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import com.saoke.joyreader.R
import com.saoke.joyreader.logic.extension.dpToPx

class AvatarView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ShapeableImageView(context, attrs, defStyleAttr) {
    private var _size: Int = 64
    fun setSize(size: Int) {
        if (size <= 0) return
        _size = size
    }

    init {
        layoutParams.width = _size.dpToPx(context)
        layoutParams.height = _size.dpToPx(context)
        setImageResource(R.drawable.avatar)
        scaleType = ScaleType.CENTER_CROP
        shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCornerSizes(ShapeAppearanceModel.PILL)
            .build()
    }
}