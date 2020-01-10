package cn.weiben.buildingshopping.ui.cart

import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import android.widget.ExpandableListView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment
import cn.weiben.buildingshopping.model.CartBean
import cn.weiben.buildingshopping.ui.adapter.ShoppingCarAdapter
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : BaseMVPFragment<CartPresenter>(),CartContract.View {

    override fun initPresenter(): CartPresenter {
        return CartPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_cart
    }

    override fun lazyData() {

    }

    private var datas: List<CartBean.GoodsListBeanX>? = null
    private lateinit var shoppingCarAdapter: ShoppingCarAdapter

    override fun initView() {
        tvTitlebarLeft.setOnClickListener {
            mPresenter.getCartList()
        }

        tvTitlebarRight.setOnClickListener {
            val edit = tvTitlebarRight.text.toString().trim()
            if (edit == "编辑") {
                tvTitlebarRight.text = "完成"
                rlTotalPrice.visibility = View.GONE
                btnOrder.visibility = View.GONE
                btnDelete.visibility = View.VISIBLE
            } else {
                tvTitlebarRight.text = "编辑"
                rlTotalPrice.visibility = View.VISIBLE
                btnOrder.visibility = View.VISIBLE
                btnDelete.visibility = View.GONE
            }
        }

        mPresenter.getCartList()
    }

    override fun setCartList(bean: CartBean) {
        datas = bean.goods_list
        initExpandableListView()
        initExpandableListViewData(datas)
    }

    /**
     * 初始化ExpandableListView的数据
     * 并在数据刷新时，页面保持当前位置
     *
     * @param datas 购物车的数据
     */
    private fun initExpandableListViewData(datas: List<CartBean.GoodsListBeanX>?) {
        if (datas != null && datas.size > 0) {
            //刷新数据时，保持当前位置
            shoppingCarAdapter.setData(datas)

            //使所有组展开
            for (i in 0 until shoppingCarAdapter.groupCount) {
                elvShoppingCar.expandGroup(i)
            }

            //使组点击无效果
            elvShoppingCar.setOnGroupClickListener(object : ExpandableListView.OnGroupClickListener {

                override fun onGroupClick(parent: ExpandableListView, v: View,
                                          groupPosition: Int, id: Long): Boolean {
                    return true
                }
            })

            tvTitlebarRight.visibility = View.VISIBLE
            tvTitlebarRight.text = "编辑"
            rlNoContant.visibility = View.GONE
            elvShoppingCar.visibility = View.VISIBLE
            rl.visibility = View.VISIBLE
            rlTotalPrice.visibility = View.VISIBLE
            btnOrder.visibility = View.VISIBLE
            btnDelete.visibility = View.GONE
        } else {
            tvTitlebarRight.visibility = View.GONE
            rlNoContant.visibility = View.VISIBLE
            elvShoppingCar.visibility = View.GONE
            rl.visibility = View.GONE
        }
    }


    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private fun initExpandableListView() {
        shoppingCarAdapter = ShoppingCarAdapter(context, llSelectAll, ivSelectAll, btnOrder, btnDelete, rlTotalPrice, tvTotalPrice)
        elvShoppingCar.setAdapter(shoppingCarAdapter)

        //删除的回调
        shoppingCarAdapter.setOnDeleteListener(ShoppingCarAdapter.OnDeleteListener {
            initDelete()
            /**
             * 实际开发中，在此请求删除接口，删除成功后，
             * 通过initExpandableListViewData（）方法刷新购物车数据。
             * 注：通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
             * GoodsBean的isSelect属性，判断商品是否被选中，
             * （true为选中，false为未选中）
             */
            /**
             * 实际开发中，在此请求删除接口，删除成功后，
             * 通过initExpandableListViewData（）方法刷新购物车数据。
             * 注：通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
             * GoodsBean的isSelect属性，判断商品是否被选中，
             * （true为选中，false为未选中）
             */
        })

        //修改商品数量的回调
        shoppingCarAdapter.setOnChangeCountListener(/**
         * 实际开发中，在此请求修改商品数量的接口，商品数量修改成功后，
         * 通过initExpandableListViewData（）方法刷新购物车数据。
         */
                ShoppingCarAdapter.OnChangeCountListener {
                    /**
                     * 实际开发中，在此请求修改商品数量的接口，商品数量修改成功后，
                     * 通过initExpandableListViewData（）方法刷新购物车数据。
                     */
                })
    }

    /**
     * 判断是否要弹出删除的dialog
     * 通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
     * GoodsBean的isSelect属性，判断商品是否被选中，
     */
    private fun initDelete() {

        //判断是否有店铺或商品被选中
        //true为有，则需要刷新数据；反之，则不需要；
        var hasSelect = false
        //创建临时的List，用于存储没有被选中的购物车数据
        val datasTemp = ArrayList<CartBean.GoodsListBeanX>()

        for (i in datas!!.indices) {
            val goods = datas!!.get(i).goods_list
            val isSelect_shop = datas!!.get(i).isSelect_shop

            if (isSelect_shop) {
                hasSelect = true
                //跳出本次循环，继续下次循环。
                continue
            } else {
                datasTemp.add(datas!!.get(i).clone())
                datasTemp.get(datasTemp.size - 1).goods_list = (ArrayList<CartBean.GoodsListBeanX.GoodsListBean>())
            }

            for (y in goods.indices) {
                val goodsBean = goods.get(y)
                val isSelect = goodsBean.getIsSelect()

                if (isSelect) {
                    hasSelect = true
                } else {
                    datasTemp.get(datasTemp.size - 1).goods_list.add(goodsBean)
                }
            }
        }

        if (hasSelect) {
            showDeleteDialog(datasTemp)
        } else {
            ToastUtils.showShort("请选择要删除的商品")
        }
    }

    /**
     * 展示删除的dialog（可以自定义弹窗，不用删除即可）
     *
     * @param datasTemp
     */
    private fun showDeleteDialog(datasTemp: List<CartBean.GoodsListBeanX>) {
        androidx.appcompat.app.AlertDialog.Builder(mActivity)
                .setMessage("确定要删除商品吗？")
                .setNegativeButton("取消") { dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton("确定") { dialog, which ->
                    datas = datasTemp
                    initExpandableListViewData(datas)
                    dialog?.dismiss()
                }.create()
                .show()

    }

    var keylistener: DialogInterface.OnKeyListener = object : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface, keyCode: Int, event: KeyEvent): Boolean {
            return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() === 0
        }
    }


}
