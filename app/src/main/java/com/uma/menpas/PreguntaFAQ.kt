package com.uma.menpas

data class PreguntaFAQ(
    val text: String,
    val subtext: String,
    var isExpandable: Boolean = false
)