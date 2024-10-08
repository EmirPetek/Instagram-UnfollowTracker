package com.emirpetek.instagramunfollowtracker.data

data class DataItem(
    val title: String,
    val media_list_data: List<Any>,  // media_list_data şu an boş olduğundan Any türünde bırakıldı
    val string_list_data: List<StringListData>
)