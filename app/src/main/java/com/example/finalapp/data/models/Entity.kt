package com.example.finalapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Entity(
    val id: Int,
    @SerializedName("field") val field: String,
    @SerializedName("concept") val concept: String,
    @SerializedName("scientist") val scientist: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("year") val year: Int? = null,

) : Parcelable
