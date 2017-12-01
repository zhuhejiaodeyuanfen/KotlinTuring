#KotlinTuring
####本项目尽量使用kotilin进行开发
1.使用安卓原生的网络请求,Rxjava处理线程间的数据返回
`````
   CCRequest
                .getInstance()
                .get(CCUrls.TEST_URL1)
                .params("")
                .execute(object : ResponseCallBack<UserBean>() {
                    override fun onResponseSuccess(response: UserBean) {

                    }
                })
`````

2.尝试使用图灵机器人的开放接口获取数据并进行界面展示
