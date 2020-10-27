package co.micode.brewdogbeerchallenge.api

import co.micode.brewdogbeerchallenge.api.model.PunkApiResponse
import co.micode.brewdogbeerchallenge.api.model.PunkApiResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApiService {

    @GET("beers")
    suspend fun fetchBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): PunkApiResponse

    @GET("beers/{id}")
    suspend fun fetchSingleBeer(
        @Path("id") id: Int,
    ): PunkApiResponse
}

