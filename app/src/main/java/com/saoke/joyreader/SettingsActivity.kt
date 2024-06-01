package com.saoke.joyreader

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.databinding.ActivitySettingsBinding
import com.saoke.joyreader.logic.model.Model
import com.saoke.joyreader.ui.auth.AuthActivity
import com.tencent.mmkv.MMKV
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.username.text = MMKV.defaultMMKV().decodeString("username", "无名氏")

        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        binding.avatar.setOnClickListener { applyPermission() }
        binding.avatarArrow.setOnClickListener { applyPermission() }

        binding.username.setOnClickListener { showUpdateDialog(EditorInfo.TYPE_CLASS_TEXT) }
        binding.usernameArrow.setOnClickListener { showUpdateDialog(EditorInfo.TYPE_CLASS_TEXT) }

        binding.password.setOnClickListener { showUpdateDialog(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD) }
        binding.passwordArrow.setOnClickListener { showUpdateDialog(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD) }
    }

    private fun applyPermission() {
        // 若 SDK < 33
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            // 若无权限
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    0
                )
            } else {
                openGallery()
            }
        }
        // 若 SDK >= 33
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // 若无权限
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    0
                )
            } else {
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivity.launch(intent)
    }

    private val startActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                try {
                    it.data?.data?.let { it1 ->
                        val inputStream = contentResolver.openInputStream(it1)
                        val file = File(cacheDir, "new_avatar.jpg") // 创建一个临时文件用于存储图片
                        val outputStream = FileOutputStream(file)
                        inputStream?.copyTo(outputStream)
                        inputStream?.close()
                        outputStream.close()

                        val requestFile =
                            file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                        val filePart =
                            MultipartBody.Part.createFormData("file", file.name, requestFile)
                        Retrofit.api.updateAvatar(filePart)
                            .enqueue(object : Callback<Model<String>> {
                                override fun onResponse(
                                    call: Call<Model<String>>,
                                    response: Response<Model<String>>
                                ) {
                                    if (response.isSuccessful) {
                                        Retrofit.getUser()
                                        Toast.makeText(
                                            this@SettingsActivity,
                                            response.body()!!.base.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Log.i("JoyReader", "updateAvatar：${response.code()}")
                                    }
                                }

                                override fun onFailure(call: Call<Model<String>>, t: Throwable) {
                                    Log.i("JoyReader", "请求失败: ${t.message}")
                                }
                            })
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            }
        }
    }

    private fun showUpdateDialog(inputType: Int) {
        AlertDialog.Builder(this).apply {
            setTitle(if (inputType == EditorInfo.TYPE_CLASS_TEXT) "修改用户名" else "修改密码")
            val editText = EditText(context)
            setView(editText)
            setPositiveButton("确定") { _, _ ->
                if (inputType == EditorInfo.TYPE_CLASS_TEXT) {
                    updateUsername(editText.text.toString())
                } else {
                    updatePassword(editText.text.toString())
                }
            }
            show()
        }
    }

    private fun updateUsername(newUsername: String) {
        Retrofit.api.updateUsername(newUsername).enqueue(object : Callback<Model<Void>> {
            override fun onResponse(call: Call<Model<Void>>, response: Response<Model<Void>>) {
                if (response.isSuccessful) {
                    binding.username.text = newUsername
                    Retrofit.getUser()
                }
                Toast.makeText(
                    this@SettingsActivity,
                    response.body()!!.base.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Model<Void>>, t: Throwable) {
                Toast.makeText(this@SettingsActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updatePassword(newPassword: String) {
        Retrofit.api.updatePassword(newPassword).enqueue(object : Callback<Model<Void>> {
            override fun onResponse(call: Call<Model<Void>>, response: Response<Model<Void>>) {
                if (response.isSuccessful) {
                    Retrofit.getUser()
                }
                Toast.makeText(
                    this@SettingsActivity,
                    response.body()!!.base.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Model<Void>>, t: Throwable) {
                Toast.makeText(this@SettingsActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}