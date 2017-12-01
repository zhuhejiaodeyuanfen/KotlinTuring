package com.wq.kotlinturing.util

import android.text.TextUtils
import java.lang.reflect.ParameterizedType

/**
 *
 * Created by vivianWQ on 2017/12/1
 * Mail: wangqi_vivian@sina.com
 * desc:
 * Version: 1.0
 *
 */

abstract class ResponseCallBack<T> {

    private var rawType: Class<T>

    fun getData(cla: Class<T>, data: String): T? {
        if (data != null && !TextUtils.isEmpty(data))
            return JsonTools.getBean(data, cla)
        return null
    }

    init {
        //主构造函数的代码初始化相关操作
        //以下代码是通过泛型解析实际参数
        val genType = javaClass.genericSuperclass
        rawType = (genType as ParameterizedType).actualTypeArguments[0] as Class<T>

    }


    fun onSuccess(s: String) {
        getData(rawType, s)?.let { onResponseSuccess(it) }
    }


    fun onError(error: String) {

    }

    abstract fun onResponseSuccess(response: T)


}