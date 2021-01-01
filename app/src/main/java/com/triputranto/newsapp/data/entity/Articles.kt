package com.triputranto.newsapp.data.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.triputranto.newsapp.utils.Const.COLUMN_AUTHOR
import com.triputranto.newsapp.utils.Const.COLUMN_CONTENT
import com.triputranto.newsapp.utils.Const.COLUMN_DESCRIPTION
import com.triputranto.newsapp.utils.Const.COLUMN_ID_ARTICLE
import com.triputranto.newsapp.utils.Const.COLUMN_PUBLISHED_AT
import com.triputranto.newsapp.utils.Const.COLUMN_TITLE
import com.triputranto.newsapp.utils.Const.COLUMN_URL
import com.triputranto.newsapp.utils.Const.COLUMN_URL_TO_IMAGE
import com.triputranto.newsapp.utils.Const.TABLE_NAME
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
@Entity(tableName = TABLE_NAME)
@Parcelize
data class Articles(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID_ARTICLE)
    var idArticles: Int? = null,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String? = null,
    @Embedded
    val source: Source? = null,
    @ColumnInfo(name = COLUMN_AUTHOR)
    val author: String? = null,
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String? = null,
    @ColumnInfo(name = COLUMN_URL)
    val url: String? = null,
    @ColumnInfo(name = COLUMN_URL_TO_IMAGE)
    val urlToImage: String? = null,
    @ColumnInfo(name = COLUMN_PUBLISHED_AT)
    val publishedAt: String? = null,
    @ColumnInfo(name = COLUMN_CONTENT)
    val content: String? = null,
    val query: String? = null,
    val country: String? = null
) : Parcelable {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    fun getPublished(publishedAt: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("dd MMMM yyyy")
            val date: Date = inputFormat.parse(publishedAt)
            outputFormat.format(date)
        } catch (e: Exception) {
            "date cant be convert"
        }

    }
}