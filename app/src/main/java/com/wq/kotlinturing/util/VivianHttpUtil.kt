package com.wq.kotlinturing.util

import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder




/**
 *
 * Created by vivianWQ on 2017/12/1
 * Mail: wangqi_vivian@sina.com
 * desc:
 * Version: 1.0
 *
 */

object VivianHttpUtil {

    private val GET: String = "GET"

    private val POST: String = "POST"

    private val connectTimeOut: Int = 6000

    private val readTimeOut: Int = 30000


    /**
     * GET方式发送数据
     *
     * @param http
     * @param data
     * @param charset
     * @return
     */
    fun sendGet(http: String, data: String, charset: String): String {
        return request(http, data, charset, GET)
    }

    fun sendGet(http: String, map: HashMap<String, String>, charset: String): String {
        return sendGet(http, map, charset, false)
    }

    fun sendGet(http: String, map: HashMap<String, String>, charset: String, encode: Boolean): String {
        return sendGet(http, parseMap(map, charset, encode), charset)
    }


    /**
     * POST方式发送数据
     *
     * @param http
     * @param data
     * @param charset
     * @return
     */
    fun sendPost(http: String, data: String, charset: String): String {
        return request(http, data, charset, POST)
    }

    fun sendPost(http: String, map: HashMap<String, String>, charset: String): String {
        return sendPost(http, map, charset, false)
    }

    fun sendPost(http: String, map: HashMap<String, String>, charset: String, encode: Boolean): String {
        return sendPost(http, parseMap(map, charset, encode), charset)
    }
    /**
     * 解析map
     */
    fun parseMap(map: HashMap<String, String>, charset: String, encode: Boolean): String {

        var sb: StringBuffer = StringBuffer()
        if (map != null && !map.isEmpty()) {
            try {

                var f: Boolean = true
                var v: String

                for (k in map.keys) {
                    if (k != null && "" != k) {
                        v = map[k]!!.trim()
                        if (!f)
                            sb.append("&")
                        if (encode)
                            v = URLEncoder.encode(v, charset)
                        sb.append(k).append("=").append(v)
                        f = false
                    }
                }

            } catch (e: Exception) {

            }
        }
        return sb.toString().trim()
    }


    fun request(http: String, data: String, charset: String, type: String): String {
        var urlString: String
        var builder = StringBuilder()
        var connection: HttpURLConnection? = null
        var outputStreamWriter: OutputStreamWriter? = null
        var bufferedWriter: BufferedWriter? = null
        var inputStreamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        if (GET.equals(type) && data != null && !"".equals(data)) {
            urlString = http + "?" + data
        } else {
            urlString = http
        }


        try {

            var url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = type
            connection.connectTimeout = connectTimeOut
            connection.readTimeout = readTimeOut
            if (POST.equals(type))
                connection.doOutput = true
            connection.doInput = true
            connection.connect()
            //传送数据
            if (POST.equals(type)) {
                if (data != null && !"".equals(data)) {
                    outputStreamWriter = OutputStreamWriter(connection.outputStream, charset)
                    bufferedWriter = BufferedWriter(outputStreamWriter)
                    bufferedWriter.write(data)
                    bufferedWriter.flush()
                }
            }
            //接收数据
            if (connection.responseCode == 200) {
                inputStreamReader = InputStreamReader(connection.inputStream, charset)
                bufferedReader = BufferedReader(inputStreamReader)
                var line: String?
                Log.i("connection","第1次")
                do {
                    line = bufferedReader.readLine()
                    builder.append(line + "\n")
                    Log.i("connection","循环一次"+line)

                } while (line != null)


//            while ((line = bufferedReader.readLine()) != null) {
//                builder.append(line + "\n")
//            }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {

                if (bufferedWriter != null)
                    bufferedWriter.close()
//
                if (outputStreamWriter != null)
                    outputStreamWriter.close()
                if (bufferedReader != null)
                    bufferedReader.close()
                if (inputStreamReader != null)
                    inputStreamReader.close()
                if (connection != null)
                    connection.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return builder.toString().trim()
        }

        return ""

    }


}