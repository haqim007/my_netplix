package dev.haqim.netplix.core.domain.model

import android.net.Uri

data class MovieTrailer(
    val site: String,
    val key: String
){
    val url: Uri = if(site.lowercase() == "vimeo"){
        Uri.parse("https://vimeo.com/$key")
    } else {
        Uri.parse("https://www.youtube.com/watch?v=$key")
    }
    val isYoutube: Boolean = site.lowercase() == "youtube"
}
