package com.fangye.serialization.utils

import android.provider.ContactsContract
import com.fangye.serialization.DataJsonActivity
import com.fangye.serialization.entity.JsonItemEntity

/**
 * 数据准备工具类
 * @Package: com.fangye.serialization.utils
 * @Author: fanyeren51@gmail.com
 * @UpdateDate: 2020/05/05 06:40
 */
class DataUtils {
    companion object{
        private const val NULL_NAME = "某些字段 服务端返回null的情况 解析"
        private const val XML_NAME = "直接传入xml格式的数据，传入非json数据，程序崩溃"
        private const val TEXT_NAME = "直接传入一个字符串，传入非json数据，程序崩溃"
        private const val TEXT_ILLEGAL_JSON_NAME = "传入一个非json数据，程序崩溃"

        private const val TEXT_NULL_JSON = "{\"key\":\"15563021738ans_104_985806_1950\",\"dataReq\":null,\"dataReqs\":null,\"value\":{\"ans\":[null,\"uck\"],\"ans2\":{\"aaa\":null,\"bbb\":\"sssss\"},\"dataReq\":null,\"dataReqs\":null,\"itemIdMap\":{\"1\":\"1050236\"},\"time\":28},\"persistent\":true}"
        private const val TEXT_ILLEGAL_JSON = "{\"id\":\"1111\"\"name\":\"我不是一个json\"}"
        private const val TEXT = "sfdsfsfs"
        private const val XML = ("<!DOCTYPE html>\n"
                + "    <html lang=\"en\">\n"
                + "    <head>\n"
                + "    \t<meta charset=\"UTF-8\">\n"
                + "    \t<meta name=\"viewport\" content=\"initial-scale=1,maximum-scale=1, minimum-scale=1\">\n"
                + "    \t<title>404</title>\n"
                + "        <script type=\"text/javascript\" src=\"//res.ekwing.com/js/mb/bridge.js?v=1\"></script>\n"
                + "    <style type=\"text/css\">\n"
                + "    \thtml {\n"
                + "        \tfont-family: Tahoma,Arial,Roboto,\"Droid Sans\",\"Helvetica Neue\",\"Droid Sans Fallback\",\"Heiti SC\",\"Microsoft "
                + "Yahei\",sans-serif\n"
                + "    \t}\n"
                + "    \n"
                + "    \thtml,body {\n"
                + "    \t    width: 100%;\n"
                + "    \t    height: 100%;\n"
                + "    \t    overflow: hidden;\n"
                + "    \t    font-size: 40px;\n"
                + "    \t    margin:0;\n"
                + "    \t    padding: 0;\n"
                + "    \t    -webkit-touch-callout: none;\n"
                + "    \t    -webkit-user-select: none;\n"
                + "    \t    user-select:none;\n"
                + "    \t}\n"
                + "    \tp{\n"
                + "    \t\tmargin:0;\n"
                + "    \t\tpadding: 0;\n"
                + "    \t}\n"
                + "     \n"
                + "    \t@font-face {\n"
                + "    \t\tfont-family: 'iconfont';  project id 164748\n"
                + "    \t  \tsrc: url('//at.alicdn.com/t/font_164748_n33hn9vh8d.eot');\n"
                + "    \t  \tsrc: url('//at.alicdn.com/t/font_164748_n33hn9vh8d.eot?#iefix') format('embedded-opentype'),\n"
                + "    \t  \turl('//at.alicdn.com/t/font_164748_n33hn9vh8d.woff') format('woff'),\n"
                + "    \t  \turl('//at.alicdn.com/t/font_164748_n33hn9vh8d.ttf') format('truetype'),\n"
                + "    \t  \turl('//at.alicdn.com/t/font_164748_n33hn9vh8d.svg#iconfont') format('svg');\n"
                + "    \t}\n"
                + "    \t.iconfont{\n"
                + "    \t\tfont-family: 'iconfont';\n"
                + "    \t\t\n"
                + "    \t}\n"
                + "    \t.top{\n"
                + "    \t\twidth: 100%;\n"
                + "    \t\theight: 1.2rem;\n"
                + "    \t\t/*background-color: red;*/\n"
                + "    \t\tline-height: 1.2rem;\n"
                + "    \t}\n"
                + "    \t.top-icon{\n"
                + "    \t\t/*display: inline;*/\n"
                + "    \t\twidth:0.533rem;\n"
                + "    \t\tpadding-top: 0;\n"
                + "    \t\tmargin:0;\n"
                + "    \t\tfont-size: 0.533rem;\n"
                + "    \t\tpadding-left: 0.267rem;\n"
                + "    \t\tcolor: #333333;\n"
                + "    \t\tline-height: 1.2rem;\n"
                + "    \t} \n"
                + "    \t.content{\n"
                + "    \t\twidth: 100%;\n"
                + "    \t\ttext-align: center;\n"
                + "    \t\t\n"
                + "    \t}\n"
                + "    \t.content img{\n"
                + "    \t\twidth: 8.533rem;\n"
                + "    \t\theight: auto;\n"
                + "    \t}\n"
                + "    \t.content p{\n"
                + "    \n"
                + "    \t\tfont-size: 0.373rem;\n"
                + "    \t\tcolor: #666666;\n"
                + "    \t\tline-height: 0.56rem;\n"
                + "    \t}\n"
                + "    \t.foot{\n"
                + "    \t\tposition: fixed;\n"
                + "    \t\tleft: 0;\n"
                + "    \t\tright: 0;\n"
                + "    \t\tbottom: 0;\n"
                + "    \t\t/*width: 10.0rem;*/\n"
                + "    \t\tpadding-bottom: 0.8rem;\n"
                + "    \t\ttext-align: center;\n"
                + "    \t}\n"
                + "    \t.foot p{\n"
                + "    \t\tfont-size: 0.32rem;\n"
                + "    \t\tcolor: #b0b0b0;\n"
                + "    \t\tline-height: 0.48rem;\n"
                + "    \t}\n"
                + "    \t\n"
                + "    </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "    \t <div class=\"top\">\n"
                + "    \t\t<div class=\"iconfont top-icon\" id=\"goback\">&#xe6e5;</div>\n"
                + "    \t</div> \n"
                + "    \t<div class=\"content\">\n"
                + "    \t\t<img src=\"/resource/static/images/mapi404.png\">\n"
                + "    \t\t<p>这个页面失联了，攻城狮正在紧急搜索中...<br>不用担心，一切尽在掌握！</p>\n"
                + "    \t</div>\n"
                + "    \t<div class=\"foot\">\n"
                + "    \t\t<p>\n"
                + "    \t\t\t造成的原因可能如下：<br>可能网速过慢，或网络中断...<br>服务器过热导致网站可能出现故障...<br>这个页面太受欢迎，太多人在访问...\n"
                + "    \t\t</p>\n"
                + "    \t</div>\n"
                + "    \t<script type=\"text/javascript\">\n"
                + "    \t\tdocument.getElementById('goback').onclick=function(){\n"
                + "    \t\t\tconsole.log('goback');\n"
                + "                 bridgeClass.toLocalEvent(\"goback\");\n"
                + "    \t\t\t//window.history.back();\n"
                + "    \t\t}\n"
                + "    \t</script>\n"
                + "    </body>\n"
                + "    </html>")


        fun getList(type:Int):MutableList<JsonItemEntity>?{
            when (type) {
                DataJsonActivity.TYPE_BOOLEAN -> return getBooleanList()
                DataJsonActivity.TYPE_BYTE-> return getByteList()
                DataJsonActivity.TYPE_SHORT -> return getShortList()
                DataJsonActivity.TYPE_INT -> return getIntList()
                DataJsonActivity.TYPE_LONG -> return getLongList()
                DataJsonActivity.TYPE_FLOAT -> return getFloatList()
                DataJsonActivity.TYPE_DOUBLE -> return getDoubleList()
                DataJsonActivity.TYPE_STRING -> return getStringList()
                DataJsonActivity.TYPE_OBJECT -> return getObjectList()
                DataJsonActivity.TYPE_ARRAY -> return getArrayList()
                DataJsonActivity.TYPE_MAP -> return getMapList()
                DataJsonActivity.TYPE_NULL -> return getNullList()
                DataJsonActivity.TYPE_NO_JSON -> return getIllegalList()
            }
           return null
        }

        private fun getNullList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataNullName = "要byte/short/int/long/boolean/float/double/map/object/array,服务端给null"
            val dataNullJson = "{\"key\":\"15563021738ans_104_985806_1950\",\"testInt\":null,\"testShort\":null,\"testByte\":null,\"testLong\":null,\"testFloat\":null,\"testDouble\":null,\"testString\":null,\"testBoolean\":null,\"maps\":null,\"dataReq\":null,\"dataReqs\":null,\"dataReqRight\":{\"key\":\"sfdsfsfsfds\",\"callBack\":\"rwrwerwrwrtfdg3453\",\"testNull\":null},\"value\":{\"ans\":[null,\"uck\"],\"ans2\":{\"aaa\":null,\"bbb\":\"sssss\"},\"dataReq\":null,\"dataReqs\":null,\"itemIdMap\":{\"1\":\"1050236\"},\"time\":28},\"persistent\":true}"

            val dataNullName1 = "要object,给的null"
            val dataNullJson1 = "{\"key\":\"15563021738ans_104_985806_1950\",\"dataReq\":null,\"dataReqRight\":{\"key\":\"sfdsfsfsfds\",\"callBack\":\"rwrwerwrwrtfdg3453\",\"testNull\":null},\"value\":{\"ans\":[null,\"uck\"],\"ans2\":{\"aaa\":null,\"bbb\":\"sssss\"},\"dataReq\":null,\"dataReqs\":null,\"itemIdMap\":{\"1\":\"1050236\"},\"time\":28},\"persistent\":true}"

            val dataNullName2 = "要byte/short/int/long/boolean/float/double/map/object/array,服务端给字符串null"
            val dataNullJson2 = "{\"key\":\"15563021738ans_104_985806_1950\",\"testInt\":\"null\",\"testShort\":\"null\",\"testByte\":\"null\",\"testLong\":\"null\",\"testFloat\":\"null\",\"testDouble\":\"null\",\"testString\":\"null\",\"testBoolean\":\"null\",\"maps\":\"null\",\"dataReq\":\"null\",\"dataReqs\":\"null\",\"dataReqRight\":{\"key\":\"sfdsfsfsfds\",\"callBack\":\"rwrwerwrwrtfdg3453\",\"testNull\":\"null\"},\"value\":{\"ans\":[\"null\",\"uck\"],\"ans2\":{\"aaa\":\"null\",\"bbb\":\"sssss\"},\"dataReq\":\"null\",\"dataReqs\":\"null\",\"itemIdMap\":{\"1\":\"1050236\"},\"time\":28},\"persistent\":true}"

            list.add(JsonItemEntity(dataNullName, dataNullJson))
            list.add(JsonItemEntity(dataNullName1, dataNullJson1))
            list.add(JsonItemEntity(dataNullName2, dataNullJson2))
            return list
        }

        private fun getBooleanList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataBooleanName1 = "1、要boolean ，给的为boolean ,数据正常，解析正常"
            val dataBooleanJson1 = "{\"id\":100,\"name\":\"sssss\",\"status\":true}"

            val dataBooleanName2 = "2、要boolean ，给的float或者double ,解析返回"
            val dataBooleanJson2 = "{\"id\":100,\"name\":\"sssss\",\"status\":1.6}"

            val dataBooleanName3 = "3、要boolean ，给的byte/short/int/long，解析返回"
            val dataBooleanJson3 = "{\"id\":100,\"name\":\"sssss\",\"status\":14}"

            val dataBooleanName4 = "4、要boolean ，给的空字符串 ，解析返回"
            val dataBooleanJson4 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"\"}"

            val dataBooleanName5 = "5、要boolean ，给的字符串 ，解析返回"
            val dataBooleanJson5 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"sss\"}"

            val dataBooleanName6 = "6、要boolean ，给的字符串boolean，解析正常返回"
            val dataBooleanJson6 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"true\"}"

            val dataBooleanName7 = "7、要boolean 给的字符串int/short/byte/long ，解析返回"
            val dataBooleanJson7 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"111\"}"

            val dataBooleanName8 = "8、要boolean 给的字符串float/double ,解析返false"
            val dataBooleanJson8 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"111.3\"}"

            val dataBooleanName9 = "9、要boolean 给的object，解析返回"
            val dataBooleanJson9 = "{\"id\":100,\"name\":\"sssss\",\"status\":{\"id\":\"888\"}}"

            val dataBooleanName10 = "10、要boolean 给的array，解析返回"
            val dataBooleanJson10 = "{\"id\":100,\"name\":\"sssss\",\"status\":[{\"id\":\"888\"}]}"

            val dataBooleanName11 = "11、要boolean 给的转义的object，解析返回false"
            val dataBooleanJson11 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"{\\\"id\\\":\\\"888\\\"}\"}"

            val dataBooleanName12 = "12要boolean 给的转义的array，解析返回false"
            val dataBooleanJson12 = "{\"id\":100,\"name\":\"sssss\",\"status\":\"[{\\\"id\\\":\\\"888\\\"}]\"}"


            list.add(JsonItemEntity(dataBooleanName1, dataBooleanJson1))
            list.add(JsonItemEntity(dataBooleanName2, dataBooleanJson2))
            list.add(JsonItemEntity(dataBooleanName3, dataBooleanJson3))
            list.add(JsonItemEntity(dataBooleanName4, dataBooleanJson4))
            list.add(JsonItemEntity(dataBooleanName5, dataBooleanJson5))
            list.add(JsonItemEntity(dataBooleanName6, dataBooleanJson6))
            list.add(JsonItemEntity(dataBooleanName7, dataBooleanJson7))
            list.add(JsonItemEntity(dataBooleanName8, dataBooleanJson8))
            list.add(JsonItemEntity(dataBooleanName9, dataBooleanJson9))
            list.add(JsonItemEntity(dataBooleanName10, dataBooleanJson10))
            list.add(JsonItemEntity(dataBooleanName11, dataBooleanJson11))
            list.add(JsonItemEntity(dataBooleanName12, dataBooleanJson12))

            return  addIllegalLists(list)
        }

        private fun addIllegalLists(list: MutableList<JsonItemEntity>):MutableList<JsonItemEntity>{
            if(list!=null){
                val size = list.size
                val dataName1 = "${size+1}、$XML_NAME"
                val dataJson1 = XML

                val dataName2 = "${size+2}、$TEXT_NAME"
                val dataJson2 = TEXT

                val dataName3 = "${size+3}、$TEXT_ILLEGAL_JSON_NAME"
                val dataJson3 = TEXT_ILLEGAL_JSON

                list.add(JsonItemEntity(dataName1,dataJson1))
                list.add(JsonItemEntity(dataName2,dataJson2))
                list.add(JsonItemEntity(dataName3,dataJson3))
                return list
            }
            return mutableListOf<JsonItemEntity>()
        }


        private fun getIllegalList(): MutableList<JsonItemEntity> {
            var list = mutableListOf<JsonItemEntity>()
            return addIllegalLists(list)
        }


        /**
         *
         * 正常返回数据
         * {"id":"111","title":"培养一种习惯","describe":"","imageUrl":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg","data1":{"id":"234","name":""},"data2":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data3":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data4":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data5":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data6":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data7":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data8":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data9":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data10":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}}}
         *
         * 服务器返回异常数据
         * {"id":"111","title":"培养一种习惯","describe":"","imageUrl":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg","data1":2222,"data2":{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}},"data3":"{\"url\":\"https://www.baidu.com/event/emlesson\",\"intentData\":{\"className_ios\":\"WebViewBaseVC\",\"className_android\":\"com.fanye.serialization.MainActivity\"}}","data4":true,"data5":"true","data6":"222","data7":"","data8":"","data9":[{"url":"https://www.baidu.com/event/emlesson","intentData":{"className_ios":"WebViewBaseVC","className_android":"com.fanye.serialization.MainActivity"}}],"data10":"[{\"url\":\"https://www.baidu.com/event/emlesson\",\"intentData\":{\"className_ios\":\"WebViewBaseVC\",\"className_android\":\"com.fanye.serialization.MainActivity\"}}]"}
         *
         */
        private fun getObjectList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataObjectName1 = "1、要object ,给的float/double/short/int/long,解析返回null"
            val dataObjectJson1 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data1\":2222}"

            val dataObjectName2 = "2、要object,给的object ，可正常解析返回"
            val dataObjectJson2 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data2\":{\"url\":\"https://www.baidu.com/event/emlesson\",\"intentData\":{\"className_ios\":\"WebViewBaseVC\",\"className_android\":\"com.fanye.serialization.MainActivity\"}}}"

            val dataObjectName3 = "3、要object,给的字符串object，被转义了 ,可正常返回"
            val dataObjectJson3 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data3\":\"{\\\"url\\\":\\\"https://www.baidu.com/event/emlesson\\\",\\\"intentData\\\":{\\\"className_ios\\\":\\\"WebViewBaseVC\\\",\\\"className_android\\\":\\\"com.fanye.serialization.MainActivity\\\"}}\"}"

            val dataObjectName4 = "4、要object,给的boolean,解析返回null"
            val dataObjectJson4 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data4\":true}"

            val dataObjectName5 = "5、要object,给的字符串boolean，解析返回null"
            val dataObjectJson5 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data5\":\"true\"}"

            val dataObjectName6 = "6、要object,给的字符串short/int/long/float/double，解析返回null"
            val dataObjectJson6 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data6\":\"222\"}"

            val dataObjectName7 = "7、要object,给的空字符串，解析返回null"
            val dataObjectJson7 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data7\":\"\"}"

            val dataObjectName8 = "8、要object,给的字符串，解析返回null"
            val dataObjectJson8 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data8\":\"sdfsfds\"}"

            val dataObjectName9 = "9、要object,给的array，解析返回null"
            val dataObjectJson9 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data9\":[{\"url\":\"https://www.baidu.com/event/emlesson\",\"intentData\":{\"className_ios\":\"WebViewBaseVC\",\"className_android\":\"com.fanye.serialization.MainActivity\"}}]}"

            val dataObjectName10 = "10、要object,给的字符串array，解析返回null"
            val dataObjectJson10 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data10\":\"[{\\\"url\\\":\\\"https://www.baidu.com/event/emlesson\\\",\\\"intentData\\\":{\\\"className_ios\\\":\\\"WebViewBaseVC\\\",\\\"className_android\\\":\\\"com.fanye.serialization.MainActivity\\\"}}]\"}"

            list.add(JsonItemEntity(dataObjectName1, dataObjectJson1))
            list.add(JsonItemEntity(dataObjectName2, dataObjectJson2))
            list.add(JsonItemEntity(dataObjectName3, dataObjectJson3))
            list.add(JsonItemEntity(dataObjectName4, dataObjectJson4))
            list.add(JsonItemEntity(dataObjectName5, dataObjectJson5))
            list.add(JsonItemEntity(dataObjectName6, dataObjectJson6))
            list.add(JsonItemEntity(dataObjectName7, dataObjectJson7))
            list.add(JsonItemEntity(dataObjectName8, dataObjectJson8))
            list.add(JsonItemEntity(dataObjectName9, dataObjectJson9))
            list.add(JsonItemEntity(dataObjectName10, dataObjectJson10))

            return  addIllegalLists(list)
        }

        /**
         * 实际正确返回
         *[{"id":"111","name":"ssss","list1":[{"cc":"1111"},{"cc":"2222"}],"list2":[{"cc":"1111"},{"cc":"2222"}],"list3":[{"cc":"1111"},{"cc":"2222"}],"list4":[["11","bb1"],["22","bb2"]],"list5":[["11","bb1"],["22","bb2"]],"list6":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list7":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list8":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list9":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list10":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list11":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list12":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list13":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list14":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]]}]
         *
         * 服务端返回
         * [{"id":"111","name":"ssss","list1":333,"list2":[{"cc":"1111"},{"cc":"2222"}],"list3":"[{\"cc\":\"1111\"},{\"cc\":\"2222\"}]","list4":[["11","bb1"],["22","bb2"]],"list5":[["11","bb1"],["22","bb2"]],"list6":"[[{\"id\":\"11\",\"tab\":\"bb1\"}],[{\"id\":\"22\",\"tab\":\"bb2\"}]]","list7":[[{"id":"11","tab":"bb1"}],[{"id":"22","tab":"bb2"}]],"list8":true,"list9":"true","list10":"111","list11":"","list12":"sdfsfs","list13":{"android":"aaaa","ios":"bbbb"},"list14":"{\"android\":\"aaaa\",\"ios\":\"bbbb\"}"}]
         *
         */
        private fun getArrayList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataArrayName1 = "1、要array,给的float/double/short/int/long,解析返回null"
            val dataArrayJson1 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list1\":333}]"

            val dataArrayName2 = "2、要array，给的array，可正常返回"
            val dataArrayJson2 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list2\":[{\"cc\":\"1111\"},{\"cc\":\"2222\"}]}]"

            val dataArrayName3 = "3、要array，给的字符串json,即字段对应的json被转义了，可正常返回"
            val dataArrayJson3 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list3\":\"[{\\\"cc\\\":\\\"1111\\\"},{\\\"cc\\\":\\\"2222\\\"}]\"}]"

            val dataArrayName4 = "4、要array,给的字符串json，json里是array嵌套array(List<String>),数据正常返回"
            val dataArrayJson4 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list4\":\"[[\\\"11\\\",\\\"bb1\\\"],[\\\"22\\\",\\\"bb2\\\"]]\"}]"

            val dataArrayName5 = "5、要array,给的array嵌套array(List<String>),可正常解析"
            val dataArrayJson5 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list5\":[[\"11\",\"bb1\"],[\"22\",\"bb2\"]]}]"

            val dataArrayName6 = "6、要array,给的字符串json ,array嵌套array(List<Object)),可正常返回"
            val dataArrayJson6 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list6\":\"[[{\\\"id\\\":\\\"11\\\",\\\"tab\\\":\\\"bb1\\\"}],[{\\\"id\\\":\\\"22\\\",\\\"tab\\\":\\\"bb2\\\"}]]\"}]"

            val dataArrayName7 = "7、要array,给的array嵌套array(List<Object>),可正常解析"
            val dataArrayJson7 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list7\":[[{\"id\":\"11\",\"tab\":\"bb1\"}],[{\"id\":\"22\",\"tab\":\"bb2\"}]]}]"

            val dataArrayName8 = "8、要array,给的boolean,解析返回null"
            val dataArrayJson8 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list8\":true}]"

            val dataArrayName9 = "9、要array,给的字符串boolean，解析返回null"
            val dataArrayJson9 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list9\":\"true\"}]"

            val dataArrayName10 = "10、要array,给的字符串short/int/long/float/double,解析返回null"
            val dataArrayJson10 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list10\":\"111\"}]"

            val dataArrayName11 = "11、要array,给的空字符串，解析返回null"
            val dataArrayJson11 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list11\":\"\"}]"

            val dataArrayName12 = "12、要array,给的字符串，非json，解析返回null "
            val dataArrayJson12 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list12\":\"sdfsfs\"}]"

            val dataArrayName13 = "13、要array,给的object，解析返回null"
            val dataArrayJson13 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list13\":{\"android\":\"aaaa\",\"ios\":\"bbbb\"}}]"

            val dataArrayName14 = "14、要array,给的字符串object，解析返回null"
            val dataArrayJson14 = "[{\"id\":\"111\",\"name\":\"ssss\",\"list14\":\"{\\\"android\\\":\\\"aaaa\\\",\\\"ios\\\":\\\"bbbb\\\"}\"}]"

            list.add(JsonItemEntity(dataArrayName1, dataArrayJson1))
            list.add(JsonItemEntity(dataArrayName2, dataArrayJson2))
            list.add(JsonItemEntity(dataArrayName3, dataArrayJson3))
            list.add(JsonItemEntity(dataArrayName4, dataArrayJson4))
            list.add(JsonItemEntity(dataArrayName5, dataArrayJson5))
            list.add(JsonItemEntity(dataArrayName6, dataArrayJson6))
            list.add(JsonItemEntity(dataArrayName7, dataArrayJson7))
            list.add(JsonItemEntity(dataArrayName8, dataArrayJson8))
            list.add(JsonItemEntity(dataArrayName9, dataArrayJson9))
            list.add(JsonItemEntity(dataArrayName10, dataArrayJson10))
            list.add(JsonItemEntity(dataArrayName11, dataArrayJson11))
            list.add(JsonItemEntity(dataArrayName12, dataArrayJson12))
            list.add(JsonItemEntity(dataArrayName13, dataArrayJson13))
            list.add(JsonItemEntity(dataArrayName14, dataArrayJson14))

            return addIllegalLists(list)
        }

        private fun getMapList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataMapName1 = "1、要Map ,给的float/double/short/int/long,解析返回null"
            val dataMapJson1 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data1\":2222}"

            val dataMapName2 = "2、要Map,给的object ，可正常解析返回"
            val dataMapJson2 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data2\":{\"url\":\"https://www.baidu.com/event/emlesson\",\"intentData\":{\"className_ios\":\"WebViewBaseVC\",\"className_android\":\"com.fanye.serialization.MainActivity\"}}}"

            //todo 确定下数据是否正常
            val dataMapName3 = "3、要Map,给的字符串object，被转义了 ,可正常返回"
            val dataMapJson3 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data3\":\"{\\\"url\\\":\\\"https://www.baidu.com/event/emlesson\\\",\\\"intentData\\\":{\\\"className_ios\\\":\\\"WebViewBaseVC\\\",\\\"className_android\\\":\\\"com.fanye.serialization.MainActivity\\\"}}\"}"

            val dataMapName4 = "4、要Map,给的boolean,解析返回null"
            val dataMapJson4 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data4\":true}"

            val dataMapName5 = "5、要Map,给的字符串boolean，解析返回null"
            val dataMapJson5 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data5\":\"true\"}"

            val dataMapName6 = "6、要Map,给的字符串short/int/long/float/double，解析返回null"
            val dataMapJson6 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data6\":\"222\"}"

            val dataMapName7 = "7、要Map,给的空字符串，解析返回null"
            val dataMapJson7 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data7\":\"\"}"

            val dataMapName8 = "8、要Map,给的字符串，解析返回null"
            val dataMapJson8 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data8\":\"\"}"

            val dataMapName9 = "9、要Map,给的array，解析返回null"
            val dataMapJson9 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data9\":[{\"url\":\"https://www.baidu.com/event/emlesson\",\"intentData\":{\"className_ios\":\"WebViewBaseVC\",\"className_android\":\"com.fanye.serialization.MainActivity\"}}]}"

            val dataMapName10 = "10、要Map,给的字符串array，解析返回null"
            val dataMapJson10 = "{\"id\":\"111\",\"title\":\"培养一种习惯\",\"describe\":\"\",\"imageUrl\":\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1588652393&di=798779f1fbc18ed59bca3433c57ca01a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg\",\"data10\":\"[{\\\"url\\\":\\\"https://www.baidu.com/event/emlesson\\\",\\\"intentData\\\":{\\\"className_ios\\\":\\\"WebViewBaseVC\\\",\\\"className_android\\\":\\\"com.fanye.serialization.MainActivity\\\"}}]\"}"

            list.add(JsonItemEntity(dataMapName1, dataMapJson1))
            list.add(JsonItemEntity(dataMapName2, dataMapJson2))
            list.add(JsonItemEntity(dataMapName3, dataMapJson3))
            list.add(JsonItemEntity(dataMapName4, dataMapJson4))
            list.add(JsonItemEntity(dataMapName5, dataMapJson5))
            list.add(JsonItemEntity(dataMapName6, dataMapJson6))
            list.add(JsonItemEntity(dataMapName7, dataMapJson7))
            list.add(JsonItemEntity(dataMapName8, dataMapJson8))
            list.add(JsonItemEntity(dataMapName9, dataMapJson9))
            list.add(JsonItemEntity(dataMapName10, dataMapJson10))

            return  addIllegalLists(list)
        }

        private fun getStringList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataStringName1 = "1、要string,给的float/double,可正常解析"
            val dataStringJson1 = "{\"id\":122342424232424.333,\"name\":\"sssss\"}"

            val dataStringName2 = "2、要string ，给的int/short/long,可正常解析"
            val dataStringJson2 = "{\"id\":12234242423,\"name\":\"sssss\"}"

            val dataStringName3 = "3、要string ,给的boolean ,可正常解析，返回字符串boolean"
            val dataStringJson3 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataStringName4 = "4、要string ，给的空字符串 ，可正常解析，返回空字符串"
            val dataStringJson4 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataStringName5 = "5、要string ，给的字符串 ，可正常解析，返回字符串"
            val dataStringJson5 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataStringName6 = "6、要string ，给的字符串boolean，可正常解析，返回字符串boolean"
            val dataStringJson6 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataStringName7 = "7、要string 给的字符串float/double ，可正常解析，返回字符串float/double"
            val dataStringJson7 = "{\"id\":\"16.33\",\"name\":\"sssss\"}"

            val dataStringName8 = "8、要string 给的字符串int/long/short ，可正常解析,返回该字符串"
            val dataStringJson8 = "{\"id\":\"1564564646464646464466\",\"name\":\"sssss\"}"

            val dataStringName9 = "9、要string 给的object，返回转义的字符串"
            val dataStringJson9 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataStringName10 = "10、要string 给的array，返回转义的字符串"
            val dataStringJson10 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"

            list.add(JsonItemEntity(dataStringName1, dataStringJson1))
            list.add(JsonItemEntity(dataStringName2, dataStringJson2))
            list.add(JsonItemEntity(dataStringName3, dataStringJson3))
            list.add(JsonItemEntity(dataStringName4, dataStringJson4))
            list.add(JsonItemEntity(dataStringName5, dataStringJson5))
            list.add(JsonItemEntity(dataStringName6, dataStringJson6))
            list.add(JsonItemEntity(dataStringName7, dataStringJson7))
            list.add(JsonItemEntity(dataStringName8, dataStringJson8))
            list.add(JsonItemEntity(dataStringName9, dataStringJson9))
            list.add(JsonItemEntity(dataStringName10, dataStringJson10))

            return  addIllegalLists(list)
        }


        private fun getDoubleList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataDoubleName1 = "1、要double ，给的float/double,可正常解析"
            val dataDoubleJson1 = "{\"id\":122342424232424.333,\"name\":\"sssss\"}"

            val dataDoubleName2 = "2、要double ，给的int/short/long,可正常解析"
            val dataDoubleJson2 = "{\"id\":12234242423,\"name\":\"sssss\"}"

            val dataDoubleName3 = "3、要double ,给的boolean ,解析返回0.0"
            val dataDoubleJson3 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataDoubleName4 = "4、要double ，给的空字符串 ，解析返回0.0"
            val dataDoubleJson4 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataDoubleName5 = "5、要double ，给的字符串 ，解析返回0.0"
            val dataDoubleJson5 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataDoubleName6 = "6、要double ，给的字符串boolean，解析返回0.0"
            val dataDoubleJson6 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataDoubleName7 = "7、要double 给的字符串float/double ,没有问题，可正常解析"
            val dataDoubleJson7 = "{\"id\":\"16.33\",\"name\":\"sssss\"}"

            val dataDoubleName8 = "8、要double 给的字符串int/long/short ,没有问题，可正常解析"
            val dataDoubleJson8 = "{\"id\":\"1564564646464646464466\",\"name\":\"sssss\"}"

            val dataDoubleName9 = "9、要double 给的object，解析返回0.0"
            val dataDoubleJson9 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataDoubleName10 = "10、要double 给的array，解析返回0.0"
            val dataDoubleJson10 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"

            list.add(JsonItemEntity(dataDoubleName1, dataDoubleJson1))
            list.add(JsonItemEntity(dataDoubleName2, dataDoubleJson2))
            list.add(JsonItemEntity(dataDoubleName3, dataDoubleJson3))
            list.add(JsonItemEntity(dataDoubleName4, dataDoubleJson4))
            list.add(JsonItemEntity(dataDoubleName5, dataDoubleJson5))
            list.add(JsonItemEntity(dataDoubleName6, dataDoubleJson6))
            list.add(JsonItemEntity(dataDoubleName7, dataDoubleJson7))
            list.add(JsonItemEntity(dataDoubleName8, dataDoubleJson8))
            list.add(JsonItemEntity(dataDoubleName9, dataDoubleJson9))
            list.add(JsonItemEntity(dataDoubleName10, dataDoubleJson10))

            return  addIllegalLists(list)
        }

        private fun getFloatList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataFloatName1 = "1、要float ，给的float/double,可正常解析"
            val dataFloatJson1 = "{\"id\":122342424232424.333,\"name\":\"sssss\"}"

            val dataFloatName2 = "2、要float ，给的int/short/long,可正常解析"
            val dataFloatJson2 = "{\"id\":1223424456462423,\"name\":\"sssss\"}"

            val dataFloatName3 = "3、要float ,给的boolean ,解析返回0.0"
            val dataFloatJson3 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataFloatName4 = "4、要float ，给的空字符串 ，解析返回0.0"
            val dataFloatJson4 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataFloatName5 = "5、要float ，给的字符串 ，解析返回0.0"
            val dataFloatJson5 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataFloatName6 = "6、要float ，给的字符串boolean，解析返回0.0"
            val dataFloatJson6 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataFloatName7 = "7、要float 给的字符串float/double ,没有问题，可正常解析"
            val dataFloatJson7 = "{\"id\":\"16.33\",\"name\":\"sssss\"}"

            val dataFloatName8 = "8、要float 给的字符串int/long/short ,没有问题，可正常解析"
            val dataFloatJson8 = "{\"id\":\"1635353534535353553\",\"name\":\"sssss\"}"

            val dataFloatName9 = "9、要float 给的object，解析返回0.0"
            val dataFloatJson9 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataFloatName10 = "10、要float 给的array，解析返回0.0"
            val dataFloatJson10 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"

            list.add(JsonItemEntity(dataFloatName1, dataFloatJson1))
            list.add(JsonItemEntity(dataFloatName2, dataFloatJson2))
            list.add(JsonItemEntity(dataFloatName3, dataFloatJson3))
            list.add(JsonItemEntity(dataFloatName4, dataFloatJson4))
            list.add(JsonItemEntity(dataFloatName5, dataFloatJson5))
            list.add(JsonItemEntity(dataFloatName6, dataFloatJson6))
            list.add(JsonItemEntity(dataFloatName7, dataFloatJson7))
            list.add(JsonItemEntity(dataFloatName8, dataFloatJson8))
            list.add(JsonItemEntity(dataFloatName9, dataFloatJson9))
            list.add(JsonItemEntity(dataFloatName10, dataFloatJson10))

            return  addIllegalLists(list)
        }

        private fun getLongList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataLongName1 = "1、要long ，给的为long/int/short ,数据正常，解析正常"
            val dataLongJson1 = "{\"id\":122222221222,\"name\":\"sssss\"}"

            val dataLongName2 = "2、要long ，给的float或者double ,解析返回0，当然也可让程序崩溃"
            val dataLongJson2 = "{\"id\":1.6,\"name\":\"sssss\"}"

            val dataLongName3 = "3、要long ,给的boolean ,解析返回0"
            val dataLongJson3 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataLongName4 = "4、要long ，给的空字符串 ，解析返回0"
            val dataLongJson4 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataLongName5 = "5、要long ，给的字符串 ，解析返回0"
            val dataLongJson5 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataLongName6 = "6、要long ，给的字符串boolean，解析返回0"
            val dataLongJson6 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataLongName7 = "7、要long 给的字符串int/long/short ,可正常解析"
            val dataLongJson7 = "{\"id\":\"16\",\"name\":\"sssss\"}"

            val dataLongName8 = "8、要long 给的字符串float/double ,解析返回0"
            val dataLongJson8 = "{\"id\":\"1.6555555\",\"name\":\"sssss\"}"

            val dataLongName9 = "9、要long 给的object，解析返回0"
            val dataLongJson9 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataLongName10 = "10、要long 给的array，解析返回0"
            val dataLongJson10 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"

            list.add(JsonItemEntity(dataLongName1, dataLongJson1))
            list.add(JsonItemEntity(dataLongName2, dataLongJson2))
            list.add(JsonItemEntity(dataLongName3, dataLongJson3))
            list.add(JsonItemEntity(dataLongName4, dataLongJson4))
            list.add(JsonItemEntity(dataLongName5, dataLongJson5))
            list.add(JsonItemEntity(dataLongName6, dataLongJson6))
            list.add(JsonItemEntity(dataLongName7, dataLongJson7))
            list.add(JsonItemEntity(dataLongName8, dataLongJson8))
            list.add(JsonItemEntity(dataLongName9, dataLongJson9))
            list.add(JsonItemEntity(dataLongName10, dataLongJson10))
            return  addIllegalLists(list)
        }



        private fun getIntList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataIntName1 = "1、要int ，给的为int/short ,数据正常，解析正常"
            val dataIntJson1 = "{\"id\":100,\"name\":\"sssss\"}"

            val dataIntName2 = "2、要int ，给的float或者double ,解析返回0，，当然也可以让程序崩溃"
            val dataIntJson2 = "{\"id\":1.6,\"name\":\"sssss\"}"

            val dataIntName3 = "3、要int ，返回long，超过int的取值范围，解析返回0，当然也可以让程序崩溃"
            val dataIntJson3 = "{\"id\":21474836475,\"name\":\"sssss\"}"

            val dataIntName4 = "4、要int ,给的boolean ,解析返回0"
            val dataIntJson4 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataIntName5 = "5、要int ，给的空字符串 ，解析返回0"
            val dataIntJson5 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataIntName6 = "6、要int ，给的字符串 ，解析返回0"
            val dataIntJson6 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataIntName7 = "7、要int ，给的字符串boolean，解析返回0"
            val dataIntJson7 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataIntName8 = "8、要int 给的字符串int/short ，可正常解析"
            val dataIntJson8 = "{\"id\":\"16\",\"name\":\"sssss\"}"

            val dataIntName9 = "9、要int ，给的字符串long，解析返回0，当然也可让程序崩溃"
            val dataIntJson9 = "{\"id\":\"1623332234234242\",\"name\":\"sssss\"}"

            val dataIntName10 = "10、要int 给的字符串float/double ,解析返回0"
            val dataIntJson10 = "{\"id\":\"1.6555555\",\"name\":\"sssss\"}"

            val dataIntName11 = "11、要int 给的object，解析返回0"
            val dataIntJson11 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataIntName12 = "12要int 给的array，解析返回0"
            val dataIntJson12 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"


            list.add(JsonItemEntity(dataIntName1, dataIntJson1))
            list.add(JsonItemEntity(dataIntName2, dataIntJson2))
            list.add(JsonItemEntity(dataIntName3, dataIntJson3))
            list.add(JsonItemEntity(dataIntName4, dataIntJson4))
            list.add(JsonItemEntity(dataIntName5, dataIntJson5))
            list.add(JsonItemEntity(dataIntName6, dataIntJson6))
            list.add(JsonItemEntity(dataIntName7, dataIntJson7))
            list.add(JsonItemEntity(dataIntName8, dataIntJson8))
            list.add(JsonItemEntity(dataIntName9, dataIntJson9))
            list.add(JsonItemEntity(dataIntName10, dataIntJson10))
            list.add(JsonItemEntity(dataIntName11, dataIntJson11))
            list.add(JsonItemEntity(dataIntName12, dataIntJson12))
            return  addIllegalLists(list)
        }

        private fun getShortList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataShortName1 = "1、要short ，给的为short ,数据正常，解析正常"
            val dataShortJson1 = "{\"id\":1,\"name\":\"sssss\"}"

            val dataShortName2 = "2、要short ，给的float或者double ,解析返回 (short)0，当然也可让程序崩溃"
            val dataShortJson2 = "{\"id\":1232.6,\"name\":\"sssss\"}"

            val dataShortName3 = "3、要short ，short（-32768 32767）给的括号之外会的值（int/long），解析返回0 ，当然也可让程序崩溃"
            val dataShortJson3 = "{\"id\":32768,\"name\":\"sssss\"}"

            val dataShortName4 = "4、要short ,给的boolean ,解析返回0"
            val dataShortJson4 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataShortName5 = "5、要short ，给的空字符串 ，解析返回0"
            val dataShortJson5 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataShortName6 = "6、要short ，给的字符串 ，解析返回0"
            val dataShortJson6 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataShortName7 = "7、要short ，给的字符串boolean，解析返回0"
            val dataShortJson7 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataShortName8 = "8、要short 给的字符串short ,没有问题，可正常解析"
            val dataShortJson8 = "{\"id\":\"16\",\"name\":\"sssss\"}"

            val dataShortName9 = "9、要short 给的字符串short ,超过short取值范围（-32768 32767）, 解析返回0，当然也可让程序崩溃"
            val dataShortJson9 = "{\"id\":\"327671\",\"name\":\"sssss\"}"

            val dataShortName10 = "10、要short 给的字符串float/double ,解析返回0"
            val dataShortJson10 = "{\"id\":\"1.6555555\",\"name\":\"sssss\"}"

            val dataShortName11 = "11、要short 给的object，解析返回0"
            val dataShortJson11 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataShortName12 = "12、要short 给的array，解析返回0"
            val dataShortJson12 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"

            list.add(JsonItemEntity(dataShortName1, dataShortJson1))
            list.add(JsonItemEntity(dataShortName2, dataShortJson2))
            list.add(JsonItemEntity(dataShortName3, dataShortJson3))
            list.add(JsonItemEntity(dataShortName4, dataShortJson4))
            list.add(JsonItemEntity(dataShortName5, dataShortJson5))
            list.add(JsonItemEntity(dataShortName6, dataShortJson6))
            list.add(JsonItemEntity(dataShortName7, dataShortJson7))
            list.add(JsonItemEntity(dataShortName8, dataShortJson8))
            list.add(JsonItemEntity(dataShortName9, dataShortJson9))
            list.add(JsonItemEntity(dataShortName10, dataShortJson10))
            list.add(JsonItemEntity(dataShortName11, dataShortJson11))
            list.add(JsonItemEntity(dataShortName12, dataShortJson12))

            return addIllegalLists(list)
        }

        private fun getByteList(): MutableList<JsonItemEntity> {
            val list = mutableListOf<JsonItemEntity>()
            val dataByteName1 = "1、要byte ，给的为byte ,数据正常，解析正常"
            val dataByteJson1 = "{\"id\":1,\"name\":\"sssss\"}"

            val dataByteName2 = "2、要byte ，给的float或者double ,解析返回 (short)0，当然也可让程序崩溃"
            val dataByteJson2 = "{\"id\":1232.6,\"name\":\"sssss\"}"

            val dataByteName3 = "3、要byte ，short（-128 127）给的括号之外会的值（short/int/long），解析返回0 ，当然也可让程序崩溃"
            val dataByteJson3 = "{\"id\":128,\"name\":\"sssss\"}"

            val dataByteName4 = "4、要byte,给的boolean ,解析返回0"
            val dataByteJson4 = "{\"id\":true,\"name\":\"sssss\"}"

            val dataByteName5 = "5、要byte ，给的空字符串 ，解析返回0"
            val dataByteJson5 = "{\"id\":\"\",\"name\":\"sssss\"}"

            val dataByteName6 = "6、要byte ，给的字符串 ，解析返回0"
            val dataByteJson6 = "{\"id\":\"sdssdfs\",\"name\":\"sssss\"}"

            val dataByteName7 = "7、要byte ，给的字符串boolean，解析返回0"
            val dataByteJson7 = "{\"id\":\"true\",\"name\":\"sssss\"}"

            val dataByteName8 = "8、要byte 给的字符串short ,没有问题，可正常解析"
            val dataByteJson8 = "{\"id\":\"16\",\"name\":\"sssss\"}"

            val dataByteName9 = "9、要byte 给的字符串byte ,超过byte取值范围（-128 127）, 解析返回0，当然也可让程序崩溃"
            val dataByteJson9 = "{\"id\":\"128\",\"name\":\"sssss\"}"

            val dataByteName10 = "10、要byte 给的字符串float/double ,解析返回0"
            val dataByteJson10 = "{\"id\":\"1.6555555\",\"name\":\"sssss\"}"

            val dataByteName11 = "11、要byte 给的object，解析返回0"
            val dataByteJson11 = "{\"id\":{\"aa\":\"00\"},\"name\":\"sssss\"}"

            val dataByteName12 = "12、要byte 给的array，解析返回0"
            val dataByteJson12 = "{\"id\":[{\"aa\":\"00\"}],\"name\":\"sssss\"}"

            list.add(JsonItemEntity(dataByteName1, dataByteJson1))
            list.add(JsonItemEntity(dataByteName2, dataByteJson2))
            list.add(JsonItemEntity(dataByteName3, dataByteJson3))
            list.add(JsonItemEntity(dataByteName4, dataByteJson4))
            list.add(JsonItemEntity(dataByteName5, dataByteJson5))
            list.add(JsonItemEntity(dataByteName6, dataByteJson6))
            list.add(JsonItemEntity(dataByteName7, dataByteJson7))
            list.add(JsonItemEntity(dataByteName8, dataByteJson8))
            list.add(JsonItemEntity(dataByteName9, dataByteJson9))
            list.add(JsonItemEntity(dataByteName10, dataByteJson10))
            list.add(JsonItemEntity(dataByteName11, dataByteJson11))
            list.add(JsonItemEntity(dataByteName12, dataByteJson12))

            return addIllegalLists(list)
        }


    }


}