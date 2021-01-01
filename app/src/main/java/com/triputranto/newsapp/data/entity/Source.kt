package com.triputranto.newsapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
@Parcelize
data class Source(
    @ColumnInfo(name = "idSource")
    val id: String? = "",
    @ColumnInfo(name = "nameSource")
    val name: String? = ""
) : Parcelable