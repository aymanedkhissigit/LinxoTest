package fr.aymane.dkhissi.linxotest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.aymane.dkhissi.linxotest.adapters.AlbumAdapter
import fr.aymane.dkhissi.linxotest.adapters.GridAdapter
import fr.aymane.dkhissi.linxotest.databinding.FragmentImageGridBinding
import fr.aymane.dkhissi.linxotest.viewModels.LinxoViewModel
import kotlinx.android.synthetic.main.fragment_image_grid.view.*

@AndroidEntryPoint
class ImageGridFragment : Fragment() {

    private lateinit var binding: FragmentImageGridBinding

     var listUrls = ArrayList<String>()
    lateinit var gridAdapter: GridAdapter
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linxoViewModel : LinxoViewModel by viewModels ()

        val args: ImageGridFragmentArgs by navArgs()




        val gridAdapter =  GridAdapter(listUrls)
        binding.listPictures.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = gridAdapter

        }
        linxoViewModel.getPhotos().observe(viewLifecycleOwner, Observer {
            Log.d("photos",it.toString())
            for (i in 0 .. it.size-1){
                if (args.albumId==it[i].albumId){
                    listUrls.add(it[i].thumbnailUrl)
                }
            }

            Log.d("urls",listUrls.toString())
            Log.d("urls",listUrls.size.toString())
            //setupRecyclerView(binding.root)
            gridAdapter.submitList(listUrls)

        })


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageGridBinding.inflate(inflater)

        return binding.root
    }



}