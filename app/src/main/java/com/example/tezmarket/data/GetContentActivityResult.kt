package com.example.tezmarket.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import java.io.File
import java.io.IOException
import kotlin.random.Random


class GetContentActivityResult(
    private val launcher: ManagedActivityResultLauncher<String, Uri?>,
    val uri: Uri?
) {
    fun launch(mimeType: String) {
        launcher.launch(mimeType)
    }
}

@Composable
fun rememberGetContentActivityResult(): GetContentActivityResult {
    var uri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = {
                uri = it
            })
    return remember(launcher, uri) {
        GetContentActivityResult(launcher, uri)
    }
}

fun decodeUriToBitmap(context: Context, uri: Uri?): Bitmap {
//    val openDocumentIntent = Intent(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
    return uri?.let { uriPath ->
        Log.d("debug", "decodeUriToBitmap: $uriPath")
//        val resolver = context.contentResolver.takePersistableUriPermission(uriPath, openDocumentIntent.flags)
        context.contentResolver.openInputStream(uriPath).use { stream ->
            BitmapFactory.decodeStream(stream)
        }
    } ?: throw IOException("Image not found.")
}

private fun File.writeBitmap(bitmap: Bitmap) {
    outputStream().use { out ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
        out.flush()
    }
}

fun getFileFromPath(uri: Uri?, context: Context): File {
    val bitmap = decodeUriToBitmap(context, uri)
    val file = File("${context.cacheDir}", "image${Random(5)}.jpeg")
    file.writeBitmap(bitmap)
    return file
}





