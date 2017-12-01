package com.wq.kotlinturing.util

import android.text.TextUtils
import com.alibaba.fastjson.JSON

/**
 *
 * Created by vivianWQ on 2017/12/1
 * Mail: wangqi_vivian@sina.com
 * desc:
 * Version: 1.0
 *
 */

object JsonTools {


    /**
     * 把JSON数据转换成指定的java对象
     */
    fun <T> getBean(json: String, clazz: Class<T>): T? {
        if (TextUtils.isEmpty(json))
            return null
        try {
            return JSON.parseObject(json, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return null
        }
    }


    /**
     * 把java对象转换成JSON字符串
     */
    fun getJsonString(jsonObject: Object): String? {
        if (null == jsonObject)
            return ""
        try {
            return JSON.toJSONString(jsonObject, true)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return null
        }
    }


    /**
     * 解析一个json数组
     */
    fun <T> getBeanList(json: String, clazz: Class<T>): List<T>? {
        if (TextUtils.isEmpty(json))
            return null
        try {
            return JSON.parseArray(json, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return null
        }
    }
}