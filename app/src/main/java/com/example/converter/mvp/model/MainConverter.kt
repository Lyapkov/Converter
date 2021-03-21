package com.example.converter.mvp.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class MainConverter(val context: Context?) : Converter {
    override fun converter(image: Image) = Completable.fromAction {
        Thread.sleep(3000)
        val bmp: Bitmap = BitmapFactory.decodeByteArray(image.data, 0, image.data.size)
        val file = File(context?.getExternalFilesDir(null), "convert.png")
        val stream = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
    }.subscribeOn(Schedulers.io())
}