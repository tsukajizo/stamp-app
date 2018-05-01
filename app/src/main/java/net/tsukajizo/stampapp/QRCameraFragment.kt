package net.tsukajizo.stampapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kotlinx.android.synthetic.main.fragment_qr_camera.view.*
import net.tsukajizo.stampapp.error.ParseCodeException
import net.tsukajizo.stampapp.util.AppScheme
import net.tsukajizo.stampapp.util.parseIdFromCode


class QRCameraFragment : Fragment() {
    var barcodeView: DecoratedBarcodeView? = null

    companion object {
        fun newInstance(): QRCameraFragment {
            val fragment = QRCameraFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_qr_camera, container, false)
        barcodeView = view.decorated_barcode_view
        startCapture()
        return view
    }

    private fun startCapture() {
        barcodeView?.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(barcodeResult: BarcodeResult) {
                parseCode(barcodeResult.text)
                Toast.makeText(activity, barcodeResult.text, Toast.LENGTH_LONG).show()
            }

            override fun possibleResultPoints(list: List<ResultPoint>) {}
        })
    }

    private fun parseCode(string: String) {
        val id = try {
            parseIdFromCode(string)
        } catch (e: ParseCodeException) {
            AppScheme.UNKNOWN_STAMP_ID
        }
        if (id == AppScheme.UNKNOWN_STAMP_ID) {
            Toast.makeText(activity, "Code not found!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, "Code found!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView?.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView?.pause()
    }
}