package ir.taghizadeh.deezer.view.trackList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.taghizadeh.deezer.R
import ir.taghizadeh.deezer.data.network.config.ApiClient
import ir.taghizadeh.deezer.data.network.services.ChartTracksService
import ir.taghizadeh.deezer.utils.NoConnectivityException
import ir.taghizadeh.deezer.view.MainActivity
import kotlinx.android.synthetic.main.fragment_track_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class TrackListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleNavigation()
        val apiService = ApiClient.buildService(activity!!, ChartTracksService::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                apiService.getChartTracks().await()
            } catch (e: NoConnectivityException) {

            }
        }
    }

    private fun handleNavigation() {
        btn_track_details.setOnClickListener {
            val action =
                TrackListFragmentDirections.trackListFragmentToTrackDetailsFragment(text_track_title.text.toString())
            findNavController().navigate(action)
        }
    }

}
