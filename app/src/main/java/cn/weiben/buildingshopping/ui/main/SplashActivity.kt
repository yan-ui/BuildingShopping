package cn.weiben.buildingshopping.ui.main


import android.Manifest
import android.content.Intent
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable

class SplashActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun isShowTitleBar(): Boolean {
        return false
    }

    private lateinit var disposable: Disposable
    override fun initView(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarVisibility(this, false)
        if (BarUtils.isSupportNavBar()) {
            BarUtils.setNavBarVisibility(this, false)
        }

        disposable = RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted) {
                        Thread {
                            Thread.sleep(1000)
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }.start()
                    } else {
                        ToastUtils.showShort("权限拒绝，无法使用！")
                        AppUtils.exitApp()
                    }

                }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(!disposable.isDisposed){
            disposable.dispose()
        }
    }


}
