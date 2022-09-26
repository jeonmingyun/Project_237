package com.coram.spy237.ui.country_info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coram.spy237.databinding.ActivityConnectModalBinding
import com.coram.spy237.util.Utils

class ConnectModalActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityConnectModalBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityConnectModalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeBtn.setOnClickListener {
            finish()
        }
        binding.appCompatButton.setOnClickListener {
            if(isInputChecked()) {
                Utils.onToast(this, "전도 대상자 정보가 전달되었습니다")
                finish()
            } else {
                Utils.onToast(this, "대상자 이름을 입력해주세요")
            }
        }
        binding.imageUploadBtn.setOnClickListener {
            startDefaultGalleryApp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    private fun startDefaultGalleryApp() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

    // 갤러리 화면에서 이미지를 선택한 경우 현재 화면에 보여준다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK) {
            return
        }

        when (requestCode) {
            100 -> {
                data?:return
                val uri = data.data as Uri
                binding.imageUploadBtn.setImageURI(uri)
            }
            else -> {
                Utils.onToast(this, "사진을 가져오지 못했습니다.")
            }
        }
    }

    private fun isInputChecked(): Boolean {
        if(binding.etFirstName.text.isBlank() ||
            binding.etLastName.text.isBlank()) {
            return false
        }
        return true
    }
}