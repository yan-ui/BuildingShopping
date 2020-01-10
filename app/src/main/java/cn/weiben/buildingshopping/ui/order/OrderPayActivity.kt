package cn.weiben.buildingshopping.ui.order

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.model.AddressModel
import cn.weiben.buildingshopping.model.CartPayBean
import cn.weiben.buildingshopping.ui.adapter.OrderPayGoodsRvAdapter
import cn.weiben.buildingshopping.ui.adapter.ShippingTypeRvAdapter
import cn.weiben.buildingshopping.ui.mine.address_manager.AddressManagerActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_order_pay.*

class OrderPayActivity : BaseMVPActivity<OrderPayPresenter>(), OrderPayContract.View {


    override fun initPresenter(): OrderPayPresenter {
        return OrderPayPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_order_pay
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("确认订单")
        val isBuy = intent.getBooleanExtra("is_buy", false)
        if (isBuy) {
            val goods = intent.getStringExtra("goods")
            mPresenter.payCartGoods("1", goods)
        } else {
            mPresenter.payCartGoods(null, null)
        }

        mPresenter.getAddressList()
    }


    private var shipping_id = ""

    override fun setCartPayData(bean: CartPayBean?) {
        if (bean == null) {
            return
        }
        tvShopName.text = bean.cart_goods[0].seller
        mGoodsRecyclerView.layoutManager = LinearLayoutManager(mContext)
        val adapter = OrderPayGoodsRvAdapter(bean.cart_goods)
        adapter.bindToRecyclerView(mGoodsRecyclerView)
        mGoodsRecyclerView.adapter = adapter

        will_get_integral.text = "*该订单完成后，您将获得${bean.total.will_get_integral}积分"
        will_get_bonus.text = "以及价值${bean.total.will_get_bonus}的红包"
        shipping_fee.text = "+配送费用：${bean.total.shipping_fee}"
        goods_price.text = "商品总价：${bean.total.goods_price}"
        amount.text = "应付款金额：${bean.total.amount}"
        tvMoney.text = "¥" + bean.total.amount

        mShippingRecyclerView.layoutManager = GridLayoutManager(mContext, 2)
        val ship_adapter = ShippingTypeRvAdapter(bean.shipping_list)
        ship_adapter.bindToRecyclerView(mShippingRecyclerView)
        mShippingRecyclerView.adapter = ship_adapter
        ship_adapter.setIOnCheckListener {
            shipping_id = it
        }

        btnSubmitOrder.setOnClickListener {


            if (addressId.isEmpty()) {
                ToastUtils.showShort("请选择收货地址")
                return@setOnClickListener
            }

            if (shipping_id.isEmpty()) {
                ToastUtils.showShort("请选择配送方式")
                return@setOnClickListener
            }

            var time = ""
            when (rgTime.checkedRadioButtonId) {
                R.id.rbTime1 -> {
                    time = "仅工作日送货"
                }

                R.id.rbTime2 -> {
                    time = "仅周末送货"
                }

                R.id.rbTime3 -> {
                    time = "工作日/周末/假日均可"
                }
            }

            if (time.isEmpty()) {
                ToastUtils.showShort("请选择送货时间")
                return@setOnClickListener
            }

            //缺货处理
            var type = -1
            when (rgGroup.checkedRadioButtonId) {
                R.id.rbType1 -> {
                    type = 0
                }

                R.id.rbType2 -> {
                    type = 1
                }

                R.id.rbType3 -> {
                    type = 2
                }
            }


            if (type == -1) {
                ToastUtils.showShort("请选择缺货处理方式")
                return@setOnClickListener
            }


            //缺货处理
            var pay_type = -1
            when (rgPayGroup.checkedRadioButtonId) {
                R.id.rbAlipay -> {
                    pay_type = 1
                }

                R.id.rbWxPay -> {
                    pay_type = 6
                }
            }


            if (pay_type == -1) {
                ToastUtils.showShort("请选择支付方式")
                return@setOnClickListener
            }

            val integral = etInput.text.toString().trim()
            mPresenter.payOrder(shipping_id, time, type, etRemark.text.toString().trim(), integral, pay_type, addressId)

        }


    }

    override fun payOrderSuccess() {
        finish()
    }


    private var addressId = ""
    override fun setAddressList(datas: MutableList<AddressModel>?) {
        if (datas == null || datas.size == 0) {
            btnAddAddress.visibility = View.VISIBLE
            llAddress.visibility = View.GONE
            btnAddAddress.setOnClickListener {
                val intent = Intent(this, AddressManagerActivity::class.java)
                intent.putExtra("type", 1)
                startActivityForResult(intent, 0)
            }
        } else {
            llAddress.visibility = View.VISIBLE
            btnAddAddress.visibility = View.GONE
            tvNickName.text = datas[0].consignee
            tvMobile.text = datas[0].mobile
            tvAddress.text = datas[0].province_name + datas[0].city_name + datas[0].district_name + datas[0].address
            addressId = datas[0].address_id
            llAddress.setOnClickListener {
                val intent = Intent(this, AddressManagerActivity::class.java)
                intent.putExtra("type", 1)
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                when (requestCode) {
                    0 -> {

                        llAddress.visibility = View.VISIBLE
                        btnAddAddress.visibility = View.GONE

                        llAddress.setOnClickListener {
                            val intent = Intent(this, AddressManagerActivity::class.java)
                            intent.putExtra("type", 1)
                            startActivityForResult(intent, 1)
                        }

                        addressId = data.getStringExtra("id")
                        tvNickName.text = data.getStringExtra("name")
                        tvMobile.text = data.getStringExtra("mobile")
                        tvAddress.text = data.getStringExtra("address")
                    }

                    1 -> {
                        llAddress.visibility = View.VISIBLE
                        btnAddAddress.visibility = View.GONE

                        addressId = data.getStringExtra("id")
                        tvNickName.text = data.getStringExtra("name")
                        tvMobile.text = data.getStringExtra("mobile")
                        tvAddress.text = data.getStringExtra("address")
                    }
                }
            }
        }


    }


}
