package cn.weiben.buildingshopping.ui.test

import android.content.Intent
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment
import cn.weiben.buildingshopping.ui.main.MainActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.login_view.*

class HomeFragment : BaseMVPFragment<TestPresenter>(), TestContract.LoginView {

    override fun initPresenter(): TestPresenter {
        return TestPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.login_view
    }

    override fun initView() {

        btnGetCode.setOnClickListener {
            val intent = Intent(mActivity, WebTestActivity::class.java)
            intent.putExtra("url", "http://www.baidu.com/")
            startActivity(intent)
        }

        btnGetCode1.setOnClickListener {

            val html = "<p>\n" +
                    "    <img title=\"\" alt=\"\" src=\"http://qiniu.dashuiniu.com.cn//qiniu/20191029/FhGjxrTW6xtxwNJj9K2_nuDmslIK.jpg\"/>\n" +
                    "</p>\n" +
                    "<p style=\"text-indent: 2em;\">\n" +
                    "    孙伦正自1994年12月参加工作以来，先后6次荣立个人三等功，受到各级嘉奖25次，连续8年被怀远县公安局评为教育训练先进个人，3次被评为优秀共产党员。他用25年勤奋踏实的工作，诠释了共产党人的忠诚和担当，印证了为民服务的初心和使命。\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    <br/>\n" +
                    "</p>\n" +
                    "<p style=\"text-indent: 2em;\">\n" +
                    "    2017年6月，孙伦正调入唐集镇派出所任所长，到任之初，就对窗口民警提出工作要求，重点塑造公安派出所的窗口形象。\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    <img title=\"\" alt=\"\" src=\"http://qiniu.dashuiniu.com.cn//qiniu/20191029/FjIgJyGZdPIxuDEXcEtpkT9LRkMA.jpg\"/>\n" +
                    "</p>\n" +
                    "<p style=\"text-indent: 2em;\">\n" +
                    "    在做好窗口服务的同时，孙伦正还立足本职工作，积极助力脱贫攻坚。2017年11月，得知辖区内有30多个贫困户没有户籍，国家扶贫帮扶政策无法落实。孙伦正了解情况后，积极入户核实，帮助他们补全了证明材料，户口得以顺利补录。\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    <img title=\"\" alt=\"\" src=\"http://qiniu.dashuiniu.com.cn//qiniu/20191029/Fspf-HdgZ64XNr4-a9dVu9jhsw0o.jpg\"/>\n" +
                    "</p>\n" +
                    "<p style=\"text-indent: 2em;\">\n" +
                    "    为民办实事、解难题是孙伦正的工作动力。辖区内的平阿山林场，职工大多因为以前手写档案与身份证信息不同步，导致工资发放不掉，这成了林场职工多年来的心病。孙伦正了解情况后，主动联系走访，帮助职工解决了工资发放问题。\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    <img title=\"\" alt=\"\" src=\"http://qiniu.dashuiniu.com.cn//qiniu/20191029/Fjqmhmcu474S_K4DHDEuBC5wdo-Z.jpg\"/>\n" +
                    "</p>\n" +
                    "<p style=\"text-indent: 2em;\">\n" +
                    "    又到了孙伦正带队巡逻的时间，他和同事们整理好制服，开始了一天的日常巡逻，日复一日的坚守，他和同事们积极履行职责，加强治安防控，切实担负起守护一方平安的重任。\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    <img title=\"\" alt=\"\" src=\"http://qiniu.dashuiniu.com.cn//qiniu/20191029/FkNcZuAoKRgfP-v8Zbgsz_LfKwm0.jpg\"/>\n" +
                    "</p>"
            val intent = Intent(mActivity, WebTestActivity::class.java)
            intent.putExtra("isHtml", true)
            intent.putExtra("content", html)
            startActivity(intent)
        }

        btnTest.setOnClickListener {
            startActivity(Intent(mActivity, PageStatusTestActivity::class.java))
        }

        btnLogin.setOnClickListener {
            mPresenter.login("", "")
        }

    }

    override fun lazyData() {
    }


    override fun loginSuccess(msg: String?) {
        ToastUtils.showShort(msg)
        startActivity(Intent(context, MainActivity::class.java))
    }

    override fun loginError(msg: String?) {
        ToastUtils.showShort(msg)
    }

}