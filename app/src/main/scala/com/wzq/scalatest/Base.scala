package com.wzq.scalatest

/**
 * Created by wzq on 15/10/6.
 */
trait Base {

  //scala 闭包的返回值取决于函数之外声明的变量

  val o = { (x: String) =>
    println(x)
  }
}
