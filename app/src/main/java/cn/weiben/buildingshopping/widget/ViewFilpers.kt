package cn.weiben.buildingshopping.widget

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ViewFlipper
import cn.weiben.buildingshopping.R
import com.blankj.utilcode.util.ConvertUtils

class ViewFilpers : ViewFlipper, View.OnClickListener {

    private var callBack: CallBack? = null

    private var con: Context? = null

    //实现两个构造方法

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    /**
     * 主要用于对ViewFilper进行一些边距和动画的设置
     */

    private fun init(context: Context) {

        this.con = context

        setPadding(dp2px(10f), dp2px(2f), dp2px(10f), dp2px(2f))//从左到下的内边距，顺时针方向

        inAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_come_in)//从下 进入时动画
        outAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_get_out)//从上 出去时动画
    }

    //重点方法
    fun addViewFlipper(con: Context, mlist: List<String>) {
        for (i in 0 until mlist.size) {

            val container = LinearLayout(con)
            container.orientation = LinearLayout.HORIZONTAL
            container.tag = i//为了给点击事件一个position。如果用不到点击事件这个可以忽略
            container.setOnClickListener(this)

            val tagView = TextView(con)
            tagView.gravity = Gravity.CENTER_VERTICAL
            tagView.text = "推荐"
            tagView.setTextColor(Color.RED)
            tagView.textSize = 14f
            tagView.gravity = Gravity.CENTER
            tagView.setLines(1)
            tagView.setBackgroundResource(R.drawable.shape_textview_red_border)


            val textView = TextView(con)
            textView.gravity = Gravity.CENTER_VERTICAL
            textView.text = mlist[i]
            textView.setTextColor(con.resources.getColor(R.color.black))
            textView.textSize = 15f
            textView.setLines(1)
            textView.ellipsize = TextUtils.TruncateAt.END//省略号用在末尾

            val tagLayoutParams = LinearLayout.LayoutParams(ConvertUtils.dp2px(50f), ConvertUtils.dp2px(25f))
            tagLayoutParams.setMargins(0, 0, ConvertUtils.dp2px(10f), 0)

            container.addView(tagView, tagLayoutParams)
            container.addView(textView, LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            this@ViewFilpers.addView(
                    container,
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
            )
        }
    }
    //此方法用于设置自定义的公告间隔时间

    fun setInterval(time: Int) {
        setFlipInterval(time)
    }

    override fun onClick(view: View) {
        callBack?.itemClick(view.tag as Int)
    }

    public fun setCallBack(callBack: CallBack) {
        this.callBack = callBack;
    }

    interface CallBack {
        fun itemClick(position: Int)
    }

    //setPadding()方法中用到的px,所以必须写一个转换
    private fun dp2px(dpValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, con!!.resources.displayMetrics)
                .toInt()
    }
}