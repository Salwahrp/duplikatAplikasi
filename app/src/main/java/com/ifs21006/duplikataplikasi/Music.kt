package com.ifs21006.duplikataplikasi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.ProtocolFamily

@Parcelize
data class Music(
    var photo: Int,
    var name: String = "",
) : Parcelable