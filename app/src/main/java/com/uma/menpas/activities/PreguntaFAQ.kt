package com.uma.menpas.activities

data class PreguntaFAQ(
    val text: String,
    val subtext: String,
    var isExpandable: Boolean = false
)