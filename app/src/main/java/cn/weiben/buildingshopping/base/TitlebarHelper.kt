package cn.weiben.buildingshopping.base

import com.wuhenzhizao.titlebar.widget.CommonTitleBar


/**
 * 标题栏辅助类
 */
class TitlebarHelper(private val titleBar: CommonTitleBar) {


    /**
     * 设置标题栏中间文字内容
     *
     * @param title  中间文字
     * @param subTitle 中间副标题文字内容
     */
    @JvmOverloads
    fun setTitle(title: String, subTitle: String = "") {
        titleBar.centerTextView.text = title
        if (subTitle.isNotEmpty()) {
            titleBar.centerSubTextView.text = subTitle
        }
    }

    @JvmOverloads
    fun setNavigationText(leftText: String,leftDrawable: Int = 0) {
        titleBar.setLeftContent(CommonTitleBar.TYPE_LEFT_TEXTVIEW, leftText, leftDrawable, 0, 0)
    }

    @JvmOverloads
    fun setNavigationImage(leftImageResource: Int = 0) {
        titleBar.setLeftContent(CommonTitleBar.TYPE_LEFT_IMAGEBUTTON, "", 0, leftImageResource, 0)
    }

    fun setNavigationCustom(leftCustomViewRes: Int) {
        titleBar.setLeftContent(CommonTitleBar.TYPE_LEFT_CUSTOM_VIEW, "", 0, 0, leftCustomViewRes)
    }

    fun setNavigationOnClickListener(listener: CommonTitleBar.OnNavigationListener) {
        titleBar.setNavigationListener(listener)
    }


    fun setPositiveText(rightText: String) {
        titleBar.setRightContent(CommonTitleBar.TYPE_RIGHT_TEXTVIEW, rightText, 0, 0)
    }

    fun setPositiveImage(rightImageResource: Int) {
        titleBar.setRightContent(CommonTitleBar.TYPE_RIGHT_IMAGEBUTTON, "", rightImageResource, 0)
    }

    fun setPositiveCustom(rightCustomViewRes: Int) {
        titleBar.setRightContent(CommonTitleBar.TYPE_RIGHT_CUSTOM_VIEW, "", 0, rightCustomViewRes)
    }

    fun setPositiveOnClickListener(listener: CommonTitleBar.OnPositiveListener) {
        titleBar.setPositiveListener(listener)
    }


    //==============================================================================================
}