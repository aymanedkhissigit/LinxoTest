package fr.aymane.dkhissi.linxotest.backend


import fr.aymane.dkhissi.linxotest.Objects.Albums
import fr.aymane.dkhissi.linxotest.Objects.Photos
import fr.aymane.dkhissi.linxotest.Objects.Users
import retrofit2.Response
import retrofit2.http.GET




interface LinxoWebservice {

    @GET("albums")
   suspend fun getAlbums () : Response<Albums>

    @GET("users")
    suspend fun getUsers (): Response<Users>

    @GET("photos")
    suspend fun getPhotos (): Response<List<Photos>>
}
