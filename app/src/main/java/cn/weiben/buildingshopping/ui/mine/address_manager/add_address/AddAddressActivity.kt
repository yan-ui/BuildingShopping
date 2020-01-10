package cn.weiben.buildingshopping.ui.mine.address_manager.add_address

import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.model.AddressModel
import cn.weiben.buildingshopping.model.address_picker.AddressBean
import cn.weiben.buildingshopping.model.address_picker.AreaPickerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add_address.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class AddAddressActivity : BaseMVPActivity<AddAddressPresenter>(), AddAddressContract.View {


    override fun initPresenter(): AddAddressPresenter {
        return AddAddressPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_add_address
    }

    private var i: IntArray? = null
    private var type: Int = 0
    private var addressId: String = "0"
    private lateinit var addressBeans: List<AddressBean>
    override fun initView(savedInstanceState: Bundle?) {
        setTitle("收货地址编辑")
        type = intent.getIntExtra("type", 0)

        val gson = Gson()
        addressBeans = gson.fromJson<List<AddressBean>>(getCityJson(), object : TypeToken<List<AddressBean>>() {
        }.type)

        val areaPickerView = AreaPickerView(this, R.style.Dialog, addressBeans)
        areaPickerView.setAreaPickerViewCallback(AreaPickerView.AreaPickerViewCallback { value ->
            i = value
            if (value.size == 3)
                btnSelectAddress.text = addressBeans.get(value[0]).region_name + "-" + addressBeans.get(value[0]).child.get(value[1]).region_name + "-" + addressBeans.get(value[0]).child.get(value[1]).child.get(value[2]).region_name
            else
                btnSelectAddress.text = addressBeans.get(value[0]).region_name + "-" + addressBeans.get(value[0]).child.get(value[1]).region_name
        })

        btnSelectAddress.setOnClickListener {
            areaPickerView.setSelect()
            areaPickerView.show()
        }


        if (type == 0) {
            btnAddAddress.setOnClickListener {
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val mobile = etMobile.text.toString().trim()
                val address = etAddress.text.toString().trim()
                val zipcode = etZipCode.text.toString().trim()
                val selectAddress = btnSelectAddress.text.toString().trim()

                if (name.isEmpty()) {
                    ToastUtils.showShort("请填写收货人")
                    return@setOnClickListener
                }


                if (mobile.isEmpty()) {
                    ToastUtils.showShort("请填写联系方式")
                    return@setOnClickListener
                }

                if (selectAddress.isEmpty()) {
                    ToastUtils.showShort("请选择所在区域")
                    return@setOnClickListener
                }

                if (address.isEmpty()) {
                    ToastUtils.showShort("请填写详细地址")
                    return@setOnClickListener
                }

                if (i == null) {
                    ToastUtils.showShort("请选择所在区域")
                    return@setOnClickListener
                }


                mPresenter.addAddressArea(name, email, addressBeans[i!![0]].region_id, addressBeans[i!![0]].child[i!![1]].region_id, addressBeans[i!![0]].child[i!![1]].child[i!![2]].region_id,
                        address, mobile, zipcode, "0")

            }
        } else {
            addressId = intent.getStringExtra("address_id")
            mPresenter.getAddressDetails(addressId)
        }

    }


    override fun setAddressDetails(bean: AddressModel) {
        etName.setText(bean.consignee)
        etEmail.setText(bean.email)
        etMobile.setText(bean.mobile)
        etAddress.setText(bean.address)
        etZipCode.setText(bean.zipcode)
        btnSelectAddress.text = bean.province_name + "-" + bean.city_name + "-" + bean.district_name

        var province = bean.province
        var city = bean.city
        var district = bean.district


        btnAddAddress.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val zipcode = etZipCode.text.toString().trim()
            val selectAddress = btnSelectAddress.text.toString().trim()

            if (name.isEmpty()) {
                ToastUtils.showShort("请填写收货人")
                return@setOnClickListener
            }


            if (mobile.isEmpty()) {
                ToastUtils.showShort("请填写联系方式")
                return@setOnClickListener
            }

            if (selectAddress.isEmpty()) {
                ToastUtils.showShort("请选择所在区域")
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                ToastUtils.showShort("请填写详细地址")
                return@setOnClickListener
            }

            if (i != null) {
                province = addressBeans[i!![0]].region_id
                city = addressBeans[i!![0]].child[i!![1]].region_id
                district = addressBeans[i!![0]].child[i!![1]].child[i!![2]].region_id
            }

            mPresenter.addAddressArea(name, email, province, city, district,
                    address, mobile, zipcode, addressId)

        }
    }


    override fun addSuccess() {
        finish()
    }

    private fun getCityJson(): String {
        val stringBuilder = StringBuilder()
        try {
            val assetManager = this.assets
            val bf = BufferedReader(InputStreamReader(
                    assetManager.open("region.json")))
            var line = bf.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bf.readLine()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }


}
