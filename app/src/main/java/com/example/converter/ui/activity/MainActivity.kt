package com.example.converter.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.mvp.model.Image
import com.example.converter.mvp.model.MainConverter
import com.example.converter.mvp.presenter.MainPresenter
import com.example.converter.ui.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val REQUEST_ID = 1

    private var vb: ActivityMainBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter(MainConverter(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        vb?.button?.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            }
            startActivityForResult(Intent.createChooser(intent, "Выберите картинку"), REQUEST_ID)
        }
        vb?.progressBar?.visibility = ProgressBar.INVISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ID) {
            if (resultCode == Activity.RESULT_OK) {
                data?.data?.let { uri ->
                    val bytes = this.contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }
                    bytes?.let {
                        presenter.converter(Image(bytes))
                    }
                }
            }
        }
    }

    override fun showSuccess() {
        Toast.makeText(this, "Выполнено", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
    }

    override fun showInProgress() {
        vb?.progressBar?.visibility = ProgressBar.VISIBLE
    }

    override fun hideInProgress() {
        vb?.progressBar?.visibility = ProgressBar.INVISIBLE
    }
}