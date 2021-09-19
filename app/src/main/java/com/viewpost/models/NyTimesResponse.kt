package com.mostpopular


import com.google.gson.annotations.SerializedName
/*
* response for the most popular api
* */
data class NyTimesResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("uri")
        val uri: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("asset_id")
        val assetId: Long,
        @SerializedName("source")
        val source: String,
        @SerializedName("published_date")
        val publishedDate: String,
        @SerializedName("updated")
        val updated: String,
        @SerializedName("section")
        val section: String,
        @SerializedName("subsection")
        val subsection: String,
        @SerializedName("nytdsection")
        val nytdsection: String,
        @SerializedName("adx_keywords")
        val adxKeywords: String,
        @SerializedName("column")
        val column: Any?,
        @SerializedName("byline")
        val byline: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("abstract")
        val `abstract`: String,
        @SerializedName("des_facet")
        val desFacet: List<String>,
        @SerializedName("org_facet")
        val orgFacet: List<String>,
        @SerializedName("per_facet")
        val perFacet: List<String>,
        @SerializedName("geo_facet")
        val geoFacet: List<String>,
        @SerializedName("media")
        val media: List<Media>,
        @SerializedName("eta_id")
        val etaId: Int
    ) {
        data class Media(
            @SerializedName("type")
            val type: String,
            @SerializedName("subtype")
            val subtype: String,
            @SerializedName("caption")
            val caption: String,
            @SerializedName("copyright")
            val copyright: String,
            @SerializedName("approved_for_syndication")
            val approvedForSyndication: Int,
            @SerializedName("media-metadata")
            val mediaMetadata: List<MediaMetadata>
        ) {
            data class MediaMetadata(
                @SerializedName("url")
                val url: String,
                @SerializedName("format")
                val format: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("width")
                val width: Int
            )
        }
    }
}