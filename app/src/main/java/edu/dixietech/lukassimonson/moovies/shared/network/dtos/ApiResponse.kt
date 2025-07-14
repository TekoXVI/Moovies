package edu.dixietech.lukassimonson.moovies.shared.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<R> (
    val page: Int,
    val results: List<R>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)