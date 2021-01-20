package studio.zewei.willy.scratchmaskimageviewsample

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import studio.zewei.willy.scratch.ScratchMaskImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val maskImage = findViewById<ScratchMaskImageView>(R.id.maskImage)
        val maskBtn = findViewById<TextView>(R.id.maskBtn)
        val clearBtn = findViewById<TextView>(R.id.clearBtn)

        maskBtn.setOnClickListener { maskImage.mask() }
        clearBtn.setOnClickListener { maskImage.clear(0) }

        maskImage.setEraseStrokeWidth(100f)
        maskImage.setRevealedObserver { percentage ->
            val isRevealed = percentage > 0.6
            if (isRevealed) {
                maskImage.clear()
                Toast.makeText(this, "Revealed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}