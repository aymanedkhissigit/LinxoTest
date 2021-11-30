package fr.aymane.dkhissi.linxotest.viewModels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.aymane.dkhissi.linxotest.Objects.Albums
import fr.aymane.dkhissi.linxotest.Objects.AlbumsUsers
import fr.aymane.dkhissi.linxotest.Objects.Photos
import fr.aymane.dkhissi.linxotest.Objects.Users
import fr.aymane.dkhissi.linxotest.repositories.LinxoRepository

import kotlinx.coroutines.*
import javax.inject.Inject




@HiltViewModel
class LinxoViewModel @Inject constructor (val linxoRepository: LinxoRepository) : ViewModel() {


    var albums : Albums? = Albums()
    var users : Users? = Users()
    var albumsUsers = MutableLiveData<AlbumsUsers>()
    var photos = MutableLiveData<List<Photos>>()



    fun getAlbumsAndUsers() : LiveData<AlbumsUsers>{

        viewModelScope.launch(Dispatchers.IO) {

            albums = linxoRepository.getAlbums().body()
            users = linxoRepository.getUsers().body()
            albumsUsers.postValue(AlbumsUsers(albums,users))
        }
        return albumsUsers

    }
    fun getPhotos() : LiveData<List<Photos>>{

        viewModelScope.launch(Dispatchers.IO) {
            photos.postValue(linxoRepository.getPhotos().body())
        }
        return photos

    }


}


