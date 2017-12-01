package com.wq.kotlinturing.util

import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *
 * Created by vivianWQ on 2017/12/1
 * Mail: wangqi_vivian@sina.com
 * desc: 网络请求 单例
 *
 * 使用伴生对象 生成单例
 * Version: 1.0
 *
 */

class CCRequest private constructor() {


    companion object {
        fun getInstance(): CCRequest {
            return Instance.ccRequest
        }
    }


    private object Instance {
        val ccRequest = CCRequest()

    }



    operator fun get(url: String): CCRequest {
        this.url = url
        return this
    }

    fun params(params: String): CCRequest {
        this.params = params
        return this
    }

    private var url: String? = null
    private var params: String? = null

    fun getParams(): String? {
        return params
    }

    fun getUrl(): String? {
        return url

    }

    fun execute(responseCallBack: ResponseCallBack<*>) {
        getUrl()?.let { getParams()?.let { it1 -> doGetUrl(it, it1, responseCallBack) } }
    }

    fun <T> doGetUrl(url: String, params: String, responseCallBack: ResponseCallBack<T>) {

        Observable.create(Observable.OnSubscribe<String> { subscriber ->
            val result = VivianHttpUtil.sendGet(url, params, "utf-8")
            subscriber.onNext(result)
        }).observeOn(AndroidSchedulers.mainThread())//指定subscriber的回调发生在UI线程
                .subscribeOn(Schedulers.newThread())
                .subscribe(object : Subscriber<String>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(s: String) {

                        responseCallBack.onSuccess(s)
                        onCompleted()

                    }
                })

    }


}