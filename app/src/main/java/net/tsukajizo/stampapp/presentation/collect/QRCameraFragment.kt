package net.tsukajizo.stampapp.presentation.collect

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kotlinx.android.synthetic.main.fragment_qr_camera.view.*
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.error.ParseCodeException
import net.tsukajizo.stampapp.util.*


class QRCameraFragment : Fragment() {
    var barcodeView: DecoratedBarcodeView? = null

    companion object {
        const val REQ_PERMISSION_CAMERA = 0
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
        setCapture()
        checkPermission(activity, REQ_PERMISSION_CAMERA, object : PermissionCallback {
            override fun onGranted() {
                barcodeView?.resume()
            }
        })
        return view
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQ_PERMISSION_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    barcodeView?.resume()
                } else {
                    activity.finish()
                }
            }
            else -> {
                //何もしない
            }
        }
    }

    private fun setCapture() {
        barcodeView?.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(barcodeResult: BarcodeResult) {
                parseCode(barcodeResult.text)
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
            barcodeView?.resume()
        } else {
            val data = Intent()
            val bundle = Bundle()
            bundle.putInt(Constant.BUNDLE_KEY_STAMP_ID, id)
            data.putExtras(bundle)
            activity.setResult(Activity.RESULT_OK, data)
            activity.finish()
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