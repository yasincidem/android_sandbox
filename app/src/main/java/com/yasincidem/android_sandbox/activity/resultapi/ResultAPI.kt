package com.yasincidem.android_sandbox.activity.resultapi

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile
import androidx.core.net.toUri
import com.yasincidem.android_sandbox.databinding.ActivityResultApiBinding
import com.yasincidem.android_sandbox.ext.toast
import java.io.File
import java.lang.ref.WeakReference


//https://developer.android.com/training/basics/intents/result
//https://www.jianshu.com/p/a367e26a8b8c

class ResultAPI : AppCompatActivity() {

    private val contracts = CONTRACTS.values().toList()

    private var _binding: ActivityResultApiBinding? = null
    private val binding get() = _binding!!

    private val openNextActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                toast("Result OK from NextActivity")
            }
        }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions : Map<String, Boolean> ->
            // Do something if some permissions granted or denied
            permissions.entries.forEach {
                if (it.value) toast("${it.key} is granted")
                else toast("${it.key} is denied")
            }
        }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // Do something if permission granted
            if (isGranted) toast("Permission is granted")
            else toast("Permission is denied")
        }

    private val takePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        toast("${bitmap.width}/${bitmap.height}")
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isGranted ->
        if (isGranted) toast("takePicture true")
        else toast("takePicture false")
    }

    private val takeVideo = registerForActivityResult(ActivityResultContracts.TakeVideo()) { bitmap ->
        toast(bitmap.config.toString())
    }

    private val pickContract = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
        toast(uri.path.toString())
    }

    private val createDocument = registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
        toast(uri.path.toString())
    }

    private val openDocumentTree = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        toast(uri.path.toString())
    }

    private val openMultipleDocument = registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
        uris.forEach { uri ->
            toast(uri.path.toString())
        }
    }

    private val openDocument = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        toast(uri.path.toString())
    }

    private val getMultipleContents = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        uris.forEach { uri ->
            toast(uri.path.toString())
        }
    }

    private val observer by lazy {
        MyLifecycleObserver(activityResultRegistry, WeakReference(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(observer)

        for (item in contracts.sortedBy {
            it.name
        }) {
            binding.buttonContainer.addView(
                Button(this).apply {
                    text = item.name
                    isAllCaps = false
                    setOnClickListener {
                        when(item) {
                            CONTRACTS.StartActivityForResult -> {
                                openNextActivity.launch(Intent(this@ResultAPI, this::class.java))
                            }

                            CONTRACTS.RequestMultiplePermissions -> {
                                requestMultiplePermissions.launch(
                                    arrayOf(
                                        Manifest.permission.BLUETOOTH,
                                        Manifest.permission.NFC,
                                        Manifest.permission.ACCESS_FINE_LOCATION
                                    )
                                )
                            }

                            CONTRACTS.RequestPermission -> {
                                requestPermission.launch(Manifest.permission.INTERNET)
                            }

                            CONTRACTS.TakePicturePreview -> {
                                takePicturePreview.launch()
                            }

                            CONTRACTS.TakePicture -> {
                                //https://stackoverflow.com/a/42516202/6382158
                                takePicture.launch(
                                    getUriForFile(
                                        this@ResultAPI,
                                        "$packageName.fileprovider",
                                        File("image1.jpg")
                                    )
                                )
                            }

                            CONTRACTS.TakeVideo -> {
                                takeVideo.launch(
                                    getUriForFile(
                                        this@ResultAPI,
                                        "$packageName.fileprovider",
                                        File("video1.mp4")
                                    )
                                )
                            }

                            CONTRACTS.PickContact -> {
                                pickContract.launch()
                            }

                            CONTRACTS.CreateDocument -> {
                                createDocument.launch("doc")
                            }

                            CONTRACTS.OpenDocumentTree -> {
                                openDocumentTree.launch(
                                    getUriForFile(
                                        this@ResultAPI,
                                        "$packageName.fileprovider",
                                        File("/")
                                    )
                                )
                            }

                            CONTRACTS.OpenMultipleDocuments -> {
                                //openMultipleDocument.launch()
                            }

                            CONTRACTS.OpenDocument -> {
                                //openDocument.launch()
                            }

                            CONTRACTS.GetMultipleContents -> {
                                //getMultipleContents.launch()
                            }

                            CONTRACTS.GetContent -> {
                                observer.selectImage()
                            }
                        }
                    }
                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    enum class CONTRACTS {
        StartActivityForResult,
        RequestMultiplePermissions,
        RequestPermission,
        TakePicturePreview,
        TakePicture,
        TakeVideo,
        PickContact,
        CreateDocument,
        OpenDocumentTree,
        OpenMultipleDocuments,
        OpenDocument,
        GetMultipleContents,
        GetContent
    }

}