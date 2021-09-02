package com.example.kotlinbasic


fun IntArray.myReverse(k:Int = 0):IntArray
{ var k = k

    try {
        if(k<0)k=0
        var last = this.size - 1
        var temp: Int
        while (last >k) {
            temp = this[k]
            this[k] = this[last]
            this[last] = temp
            k++
            last--
        }
        /*for (i in this.indices) {
            println(this[i])
        }*/
    } catch (e: IllegalArgumentException) {
        if (k<0)
        println("k>0 olmalı")
    }
    return this

}