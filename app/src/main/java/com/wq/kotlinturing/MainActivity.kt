package com.wq.kotlinturing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wq.kotlinturing.Bean.UserBean
import com.wq.kotlinturing.constants.CCUrls
import com.wq.kotlinturing.util.CCRequest
import com.wq.kotlinturing.util.ResponseCallBack


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CCRequest
                .getInstance()
                .get(CCUrls.TEST_URL1)
                .params("")
                .execute(object : ResponseCallBack<UserBean>() {
                    override fun onResponseSuccess(response: UserBean) {

                    }
                })
    }
}
