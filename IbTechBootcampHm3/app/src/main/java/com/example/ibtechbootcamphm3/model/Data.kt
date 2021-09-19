package com.example.ibtechbootcamphm3.model

import java.io.Serializable

data class Data(
    val viewType: Int,              // MultipleView
    val text: String? = null,       // Text
    val avatar: Int? = null
): Serializable
