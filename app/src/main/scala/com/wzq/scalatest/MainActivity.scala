package com.wzq.scalatest

import android.animation.ValueAnimator.AnimatorUpdateListener
import android.animation.{Animator, AnimatorSet, ObjectAnimator, ValueAnimator}
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.{BounceInterpolator, LinearInterpolator}
import android.widget.{Button, ImageView}

/**
 * Created by wzq on 15/7/23.
 */
class MainActivity extends AppCompatActivity with View.OnClickListener{

  var index : Int = 0
  var pic : ImageView = null

  var btn : Button = null

  var area : View = null

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    pic = findViewById(R.id.main_img).asInstanceOf[ImageView]
    btn = findViewById(R.id.main_btn).asInstanceOf[Button]
    area = findViewById(R.id.main_area)

    val valueAnimator : ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
    valueAnimator.addUpdateListener(new AnimatorUpdateListener {
      override def onAnimationUpdate(valueAnimator: ValueAnimator): Unit = {
        pic.setAlpha(valueAnimator.getAnimatedValue.asInstanceOf[Float])
      }
    })

    valueAnimator.setInterpolator(new LinearInterpolator())

    val mAnimator : ValueAnimator = ValueAnimator.ofFloat(-300f, 0f);
    mAnimator.addUpdateListener(new AnimatorUpdateListener {
      override def onAnimationUpdate(valueAnimator: ValueAnimator): Unit = {
        pic.setY(valueAnimator.getAnimatedValue.asInstanceOf[Float])
      }
    })

    val animSet : AnimatorSet = new AnimatorSet;
    animSet.play(valueAnimator).`with`(mAnimator);
    animSet.setDuration(1000);
    //animSet.start()

    val objectAnimator : ObjectAnimator =  ObjectAnimator.ofFloat(pic, "translationX", 0, 100).setDuration(1000)
    objectAnimator.setInterpolator(new BounceInterpolator())
    //objectAnimator.start()

    pic.setScaleX(0)
    pic.setScaleY(0)
    val mAnimator1 : ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
    mAnimator1.addUpdateListener(new AnimatorUpdateListener {
      override def onAnimationUpdate(valueAnimator: ValueAnimator): Unit = {
        val value : Float = valueAnimator.getAnimatedValue.asInstanceOf[Float]
        pic.setScaleX(value)
        pic.setScaleY(value)
      }
    })
    mAnimator1.setInterpolator(new BounceInterpolator())
    mAnimator1.setDuration(3000)
    mAnimator1.start()

    btn.setOnClickListener(this)

  }

  override def onClick(view: View): Unit = {
    val mAnimator3 : ValueAnimator = ValueAnimator.ofFloat(area.getY, btn.getY)
    mAnimator3.addUpdateListener(new AnimatorUpdateListener {
      override def onAnimationUpdate(valueAnimator: ValueAnimator): Unit = {
        area.setY(valueAnimator.getAnimatedValue.asInstanceOf[Float])
      }
    })
    val mAnimator2 : ValueAnimator = ValueAnimator.ofFloat(btn.getElevation, 50f)
    mAnimator2.addUpdateListener(new AnimatorUpdateListener {
      override def onAnimationUpdate(valueAnimator: ValueAnimator): Unit = {
        btn.setElevation(valueAnimator.getAnimatedValue.asInstanceOf[Float])
      }
    })
    val animSet : AnimatorSet = new AnimatorSet
    animSet.play(mAnimator2).`with`(mAnimator3)
    animSet.setDuration(500)
    animSet.addListener(new Animator.AnimatorListener{
      override def onAnimationEnd(animator: Animator): Unit = {
        btn.setVisibility(View.VISIBLE)
      }

      override def onAnimationRepeat(animator: Animator): Unit = {

      }

      override def onAnimationStart(animator: Animator): Unit = {

      }

      override def onAnimationCancel(animator: Animator): Unit = {

      }
    })
    animSet.start()
  }

}
