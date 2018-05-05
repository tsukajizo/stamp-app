package net.tsukajizo.stampapp.presentation.location

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.task.ReadStampTask
import net.tsukajizo.stampapp.task.TaskListener
import javax.inject.Inject


class StampLocationFragment : Fragment(), OnMapReadyCallback {

    @Inject
    lateinit var readStampTask: ReadStampTask

    companion object {
        val SHIBUYA: LatLng = LatLng(35.6590746, 139.6984292)
        fun newInstance(): StampLocationFragment {
            val fragment = StampLocationFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.app()!!.appComponent()!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stamp_location, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        // カメラを移動
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(SHIBUYA, 5f))
        readStampTask.setListener(object : TaskListener<List<Stamp>?> {
            override fun onSuccess(result: List<Stamp>?) {
                super.onSuccess(result)
                if (result != null) {
                    result.forEach { stamp -> addMarker(map, stamp) }
                }
            }
        })
        readStampTask.execute()


    }

    private fun addMarker(map: GoogleMap?, stamp: Stamp) {
        map?.addMarker(MarkerOptions()
                .position(LatLng(stamp.latitude, stamp.longitude))
                .title("Marker"))
    }
}