package fr.aymane.dkhissi.linxotest.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class ImageGridFragment : Fragment() {

    private lateinit var binding: FragmentImageGridBinding

     var listUrls = ArrayList<String>()



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
        if (checkForInternet(context!!)){
            linxoViewModel.getPhotos().observe(viewLifecycleOwner, Observer {

                listUrls.clear()
                for (i in 0 .. it.size-1){
                    if (args.albumId==it[i].albumId){
                        listUrls.add(it[i].thumbnailUrl)
                    }
                }
                gridAdapter.submitList(listUrls)
                binding.myRefresh.isRefreshing=false

            })
        }else{
            Toast.makeText(context,"Check your connection and Refresh", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing=false
        }
        binding.myRefresh.setOnRefreshListener {
            if (checkForInternet(context!!)){
                linxoViewModel.getPhotos().observe(viewLifecycleOwner, Observer {
                    listUrls.clear()
                    for (i in 0 .. it.size-1){
                        if (args.albumId==it[i].albumId){
                            listUrls.add(it[i].thumbnailUrl)
                        }
                    }
                    gridAdapter.submitList(listUrls)
                    binding.myRefresh.isRefreshing=false

                })
            }else{
                Toast.makeText(context,"Check your connection and Refresh", Toast.LENGTH_SHORT).show()
                binding.myRefresh.isRefreshing=false
            }
        }




    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageGridBinding.inflate(inflater)

        return binding.root
    }

    private fun checkForInternet(context: Context): Boolean {


        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            val network = connectivityManager.activeNetwork ?: return false


            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true


                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}