package studio.zewei.willy.scratch

import android.graphics.Bitmap
import java.nio.ByteBuffer

/**
 * Created by Willy on 2021/01/18.
 */
fun Bitmap.getTransparentPercent(): Float {

    val buffer = ByteBuffer.allocate(height * rowBytes)
    copyPixelsToBuffer(buffer)
    val pixels = buffer.array()

    var emptyPixelCnt = 0
    pixels.forEach { if (it.toDouble() == 0.0) emptyPixelCnt++ }

    return if (emptyPixelCnt == 0) 0F else emptyPixelCnt / pixels.size.toFloat()
}