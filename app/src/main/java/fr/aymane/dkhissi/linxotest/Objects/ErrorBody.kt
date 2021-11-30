package fr.aymane.dkhissi.linxotest.Objects

import com.google.gson.annotations.SerializedName


data class ErrorBody(
    @SerializedName("body")
    val body: Body,
    @SerializedName("message")
    val message: String
)