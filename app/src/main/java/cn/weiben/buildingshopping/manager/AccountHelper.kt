package cn.weiben.buildingshopping.manager

import com.blankj.utilcode.util.SPStaticUtils

object AccountHelper {

    fun saveToken(token: String) {
        SPStaticUtils.put("token", token)
    }


    fun getToken(): String {
        return SPStaticUtils.getString("token", "")
    }
}