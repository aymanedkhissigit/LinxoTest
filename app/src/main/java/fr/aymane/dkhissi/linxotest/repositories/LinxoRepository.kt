package fr.aymane.dkhissi.linxotest.repositories



import fr.aymane.dkhissi.linxotest.Objects.Albums
import fr.aymane.dkhissi.linxotest.Objects.Photos
import fr.aymane.dkhissi.linxotest.Objects.Users
import fr.aymane.dkhissi.linxotest.backend.LinxoWebservice
import retrofit2.Response
import javax.inject.Inject


class LinxoRepository @Inject constructor (val linxoWebservice: LinxoWebservice) {







    suspend fun getAlbums(): Response<Albums> {



        return linxoWebservice.getAlbums()

    }

    suspend fun getUsers(): Response<Users> {
        return linxoWebservice.getUsers()

    }

    suspend fun getPhotos(): Response<List<Photos>> {
        return linxoWebservice.getPhotos()

    }










}