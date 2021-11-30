package fr.aymane.dkhissi.linxotest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.aymane.dkhissi.linxotest.Objects.Albums
import fr.aymane.dkhissi.linxotest.Objects.GlobalObject
import fr.aymane.dkhissi.linxotest.Objects.Users
import fr.aymane.dkhissi.linxotest.adapters.AlbumAdapter
import fr.aymane.dkhissi.linxotest.databinding.FragmentMainBinding
import fr.aymane.dkhissi.linxotest.viewModels.LinxoViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.progressBar.visibility=View.VISIBLE
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linxoViewModel : LinxoViewModel by viewModels ()



        val albumAdapter =  AlbumAdapter(onItemClick = {
            goToImageGridFragment(it)
        })
        binding.myRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }


        linxoViewModel.getAlbumsAndUsers().observe(viewLifecycleOwner, Observer {
            albumAdapter.submitList(getListGlobal(it.albums!!,it.users!!))
            binding.progressBar.visibility=View.GONE
        })


    }

    private fun goToImageGridFragment(it: Int) {

        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
             findNavController().navigate(action)
    }



    private fun getListGlobal(albums: Albums, users: Users): List<GlobalObject> {
        var listGlobal: MutableList<GlobalObject> = arrayListOf()

        for (i in 0..albums.size - 1) {

            for (j in 0..users.size - 1) {

                if (albums[i].userId == users[j].id) {
                    var globalObject =
                        GlobalObject(users[j].id, albums[i].id, albums[i].title, users[j].name)
                    listGlobal.add(globalObject)

                }


            }
        }
        return listGlobal.sortedBy { it.nameAlbum }
    }


}