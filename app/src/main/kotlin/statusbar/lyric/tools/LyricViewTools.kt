package statusbar.lyric.tools

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import statusbar.lyric.view.LyricSwitchView

object LyricViewTools {
<<<<<<< HEAD:app/src/main/kotlin/statusbar/lyric/tools/LyricViewTools.kt
    private var animaList: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8)
    val randomAnima: Int get() = animaList.random()

=======
>>>>>>> main:app/src/main/java/statusbar/lyric/tools/LyricViewTools.kt
    fun getAlphaAnimation(into: Boolean, duration: Long = 250): AnimationSet {
        val alphaAnimation = (if (into) AlphaAnimation(0f, 1F) else AlphaAnimation(1F, 0f)).apply {
            this.duration = duration
        }
        return AnimationSet(true).apply {
            addAnimation(alphaAnimation)
        }
    }

    fun switchViewInAnima(int: Int?, interpolator: Int?, time: Int?): Animation? {
        val t = time?.toLong() ?: 500L
<<<<<<< HEAD:app/src/main/kotlin/statusbar/lyric/tools/LyricViewTools.kt
        val translateAnimation: Animation? = when (int) {
            1 -> TranslateAnimation(0f, 0f, 100f, 0f)
            2 -> TranslateAnimation(0f, 0f, -100f, 0f)
            3 -> TranslateAnimation(100f, 0f, 0f, 0f)
            4 -> TranslateAnimation(-100f, 0f, 0f, 0f)
            5 -> null
            6 -> ScaleAnimation(0f, 1f, 0f, 1f)
            7 -> ScaleAnimation(0f, 1f, 1f, 1f)
            8 -> ScaleAnimation(1f, 1f, 0f, 1f)
=======
        val translateAnimation: Animation? = when (str) {
            "Top" -> TranslateAnimation(0f, 0f, 100f, 0f)
            "Bottom" -> TranslateAnimation(0f, 0f, -100f, 0f)
            "Start" -> TranslateAnimation(100f, 0f, 0f, 0f)
            "End" -> TranslateAnimation(-100f, 0f, 0f, 0f)
            "Fade" -> null
            "ScaleXY" -> ScaleAnimation(0f, 1f, 0f, 1f)
            "ScaleX" -> ScaleAnimation(0f, 1f, 1f, 1f)
            "ScaleY" -> ScaleAnimation(1f, 1f, 0f, 1f)
>>>>>>> main:app/src/main/java/statusbar/lyric/tools/LyricViewTools.kt
            else -> return null
        }?.apply {
            duration = t
        }
        return getAlphaAnimation(true, t).apply {
            translateAnimation?.let { addAnimation(it) }
            switchInterpolator(interpolator)
        }
    }


    fun switchViewOutAnima(str: Int?, time: Int?): Animation? {
        val t = time?.toLong() ?: 500L
        val translateAnimation: Animation? = when (str) {
<<<<<<< HEAD:app/src/main/kotlin/statusbar/lyric/tools/LyricViewTools.kt
            1 -> TranslateAnimation(0f, 0f, 0f, -100f)
            2 -> TranslateAnimation(0f, 0f, 0f, +100f)
            3 -> TranslateAnimation(0f, -100f, 0f, 0f)
            4 -> TranslateAnimation(0f, 0f + 100f, 0f, 0f)
            5 -> null
            6 -> ScaleAnimation(1f, 0f, 1f, 0f)
            7 -> ScaleAnimation(1f, 0f, 1f, 1f)
            8 -> ScaleAnimation(1f, 1f, 1f, 0f)
=======
            "Top" -> TranslateAnimation(0f, 0f, 0f, -100f)
            "Bottom" -> TranslateAnimation(0f, 0f, 0f, +100f)
            "Start" -> TranslateAnimation(0f, -100f, 0f, 0f)
            "End" -> TranslateAnimation(0f, 0f + 100f, 0f, 0f)
            "Fade" -> null
            "ScaleXY" -> ScaleAnimation(1f, 0f, 1f, 0f)
            "ScaleX" -> ScaleAnimation(1f, 0f, 1f, 1f)
            "ScaleY" -> ScaleAnimation(1f, 1f, 1f, 0f)
>>>>>>> main:app/src/main/java/statusbar/lyric/tools/LyricViewTools.kt
            else -> return null
        }?.apply {
            duration = t
        }
        return getAlphaAnimation(false, t).apply {
            translateAnimation?.let { addAnimation(it) }
        }
    }

    private fun Animation.switchInterpolator(int: Int?) {
        interpolator = when (int) {
            1 -> AccelerateInterpolator()
            2 -> DecelerateInterpolator()
            3 -> AccelerateDecelerateInterpolator()
            4 -> OvershootInterpolator()
            5 -> BounceInterpolator()
            else -> LinearInterpolator()
        }
    }

    fun View.hideView(anim: Boolean = true) {
        if (visibility == View.GONE) return
        if (anim) {
            val alphaAnimation = getAlphaAnimation(false).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        visibility = View.GONE
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
            startAnimation(alphaAnimation)
        } else {
            visibility = View.GONE
        }
    }

    fun View.showView() {
        if (visibility == View.VISIBLE) return
        val alphaAnimation = getAlphaAnimation(true).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    visibility = View.VISIBLE
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }
        startAnimation(alphaAnimation)
    }

    private fun ObjectAnimator.colorAnimator() {
        this.apply {
            setEvaluator(ArgbEvaluator())
            duration = 200L
        }.start()
    }

    @SuppressLint("Recycle", "AnimatorKeep")
    fun LyricSwitchView.textColorAnima(color: Int) {
        this.applyToAllViews {
            ObjectAnimator.ofInt(it, "textColor", it.currentTextColor, color).colorAnimator()
        }
    }

    @SuppressLint("Recycle")
    fun ImageView.iconColorAnima(startColor: Int, endColor: Int) {
        ObjectAnimator.ofInt(this, "colorFilter", startColor, endColor).colorAnimator()
    }

}