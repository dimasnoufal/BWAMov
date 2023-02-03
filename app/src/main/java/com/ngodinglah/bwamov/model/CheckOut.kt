package com.ngodinglah.bwamov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckOut (
    var kursi: String ? = "",
    var harga: String ? = ""
) : Parcelable