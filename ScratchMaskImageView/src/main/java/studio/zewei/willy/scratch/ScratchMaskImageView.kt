package studio.zewei.willy.scratch

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView


/**
 * Created by Willy on 2021/01/18.
 */
class ScratchMaskImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var revealedObserver: (percentage: Float) -> Unit = {}

    private val erasePath by lazy { Path() }
    private val erasePaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isDither = true
            strokeWidth = 80F
            color = Color.TRANSPARENT
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }
    }

    init {
        // Set null to paint of layer, make the layer is transparent.
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply { drawPath(erasePath, erasePaint) }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    erasePath.moveTo(x, y)
                }
                MotionEvent.ACTION_MOVE -> {
                    erasePath.lineTo(x, y)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> checkRevealPercentage()
            }

            invalidate()
        }
        return true
    }

    private fun checkRevealPercentage() {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        revealedObserver(bitmap.getTransparentPercent())
    }

    /**
     * Clear all area of masking with the Fade Animation.
     */
    fun clear(fadeAnimMs: Long = 500) {
        if (visibility == View.GONE) return

        ObjectAnimator.ofFloat(this, "alpha", 1F, 0F).apply {
            duration = fadeAnimMs
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    visibility = View.GONE
                    revealedObserver(1f)
                }
            })
            start()
        }
    }

    /**
     * Revert scratches and mask view again.
     */
    fun mask() {
        visibility = View.VISIBLE
        alpha = 1f

        erasePath.reset()
        invalidate()

        revealedObserver(0f)
    }

    /**
     * Set the stroke width for paint of eraser
     */
    fun setEraseStrokeWidth(width: Float) {
        erasePaint.strokeWidth = width
    }

    /**
     * Set a observer to watch the percentage of revealed
     */
    fun setRevealedObserver(observer: (percentage: Float) -> Unit) {
        revealedObserver = observer
    }
}