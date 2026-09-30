package com.example.medalert

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicine(
    val name: String,
    val dosage: String,
    val type: String,
    val frequency: String,
    val reminderRequired: Boolean
) : Parcelable
