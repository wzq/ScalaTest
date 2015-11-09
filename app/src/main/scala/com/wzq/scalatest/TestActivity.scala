package com.wzq.scalatest

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.{Button, EditText}

/**
 * Created by wzq on 15/8/18.
 */
class TestActivity extends AppCompatActivity {

  var e1, e2, e3, e4 : EditText = null
  var b1 : Button = null
  var v1 : View = null;

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)
    setTitle("CMYK to RGB")

    v1 = findViewById(R.id.content_view)
    e1 = findViewById(R.id.editText1).asInstanceOf[EditText]
    e2 = findViewById(R.id.editText2).asInstanceOf[EditText]
    e3 = findViewById(R.id.editText3).asInstanceOf[EditText]
    e4 = findViewById(R.id.editText4).asInstanceOf[EditText]
    b1 = findViewById(R.id.editText5).asInstanceOf[Button]
  }

  def getRGB(view : View){
    try {
      val c = e1.getText.toString.toInt
      val m = Integer.valueOf(e2.getText.toString)
      val y = Integer.valueOf(e3.getText.toString)
      val k = Integer.valueOf(e4.getText.toString)

      val R = 255*(100-c)*(100-k)/10000
      val G = 255*(100-m)*(100-k)/10000
      val B = 255*(100-y)*(100-k)/10000
      val color = Color.rgb(R, G, B)
      val temp = "#"+ Integer.toHexString(R)+Integer.toHexString(G)+Integer.toHexString(B)
      b1.setText(temp)
      v1.setBackgroundColor(color)
    } catch {
      case e:Exception => println(e)
    }
  }
}
