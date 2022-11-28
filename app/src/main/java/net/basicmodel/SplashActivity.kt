package net.basicmodel

import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.xxxxxxh.update.BaseActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.xutils.http.RequestParams
import org.xutils.x

class SplashActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun startMainActivity() {
//       startActivity(Intent(this,MainActivity::class.java))
        /* val retrofit = RetrofitUtils().retrofit()
         val service = retrofit.create(RequestService::class.java)
         service.uploadFbData(url,mutableMapOf("content" to v)).enqueue(object :
             Callback<ResponseBody>{
             override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                 Log.i("xxxxxxH", response.body()!!.string())
             }

             override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                 Log.i("xxxxxxH", "onFailure")
             }

         })*/
        val body: RequestBody = Gson().toJson(mutableMapOf("content" to v))
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        OkGo.post<String>(url).upRequestBody(body).execute(object : StringCallback() {
            override fun onSuccess(response: com.lzy.okgo.model.Response<String>?) {
                Log.i("xxxxxxH", response!!.body().toString())
            }

        })
        /*val params = RequestParams(url)
        params.addParameter("content", v)
        x.http().post(params, object : org.xutils.common.Callback.CommonCallback<String> {
            override fun onSuccess(result: String?) {
                Log.i("xxxxxxH", "$result")
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {

            }

            override fun onCancelled(cex: org.xutils.common.Callback.CancelledException?) {

            }

            override fun onFinished() {

            }

        })*/
    }


}