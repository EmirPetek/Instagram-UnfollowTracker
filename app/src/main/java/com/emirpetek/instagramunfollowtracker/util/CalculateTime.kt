package com.emirpetek.instagramunfollowtracker.util

import android.content.Context
import com.emirpetek.instagramunfollowtracker.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CalculateTime    (val mContext: Context
) {
    fun unixtsToDate(timestamp: String): String {
        // Unix zaman damgası saniye cinsinden geliyor, bu yüzden milisaniyeye çevirmiyoruz
        val unixTimestamp = timestamp.toLong() // Unix zaman damgası saniye cinsinde
        val formattedDateTime = getLocalizedDateTime(unixTimestamp * 1000) // Tarih ve saat için milisaniyeye çevriliyor
        val postTime = formattedDateTime.substring(11, 16)
        val yyyy = formattedDateTime.substring(0, 4)
        val mm = formattedDateTime.substring(5, 7)
        val dd = formattedDateTime.substring(8, 10)
        val postDate = "$dd/$mm/$yyyy"

        val nowTimeStamp = System.currentTimeMillis() / 1000 // Şu anki zaman saniye cinsine çevrildi

        val timeDifference = nowTimeStamp - unixTimestamp // Saniye farkı

        val min = timeDifference / 60 // kaç dakika geçmiş
        val hour = timeDifference / 3600 // kaç saat geçmiş

        var text = ""
        if (min in 0..59) {
            text = "$min ${mContext.getString(R.string.minutes_ago)}"
        } else if (hour in 1..23) {
            text = "$hour ${mContext.getString(R.string.hours_ago)}"
        } else {
            text = "$postTime - $postDate"
        }

        return text
    }

    private fun getLocalizedDateTime(unixTime: Long): String {
        // Unix zamanını milisaniye cinsine çevir
        val date = Date(unixTime)

        // Cihazın mevcut dil ve bölge ayarlarını al
        val locale = Locale.getDefault()

        // Tarih ve saat formatını belirle
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)

        // Cihazın zaman dilimini al
        val timeZone = TimeZone.getDefault()
        dateFormat.timeZone = timeZone

        // Tarih ve saati formatla ve döndür
        return dateFormat.format(date)
    }

}