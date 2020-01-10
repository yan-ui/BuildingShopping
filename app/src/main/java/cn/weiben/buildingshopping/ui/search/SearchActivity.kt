package cn.weiben.buildingshopping.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.ui.adapter.SearchHistoryRvAdapter
import cn.weiben.buildingshopping.ui.search.shop_list.ShopListActivity
import cn.weiben.buildingshopping.ui.search.sort_list.SortListActivity
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_search
    }

    private var searchStr = ""
    private var searchHistoryList: MutableList<String> = ArrayList()
    private var stringBuilder = StringBuilder()

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("关键搜索")

        searchHistoryList = SPUtils.getInstance().getString("history", "").split("|+|").toMutableList()
        val iterator = searchHistoryList.iterator()
        while (iterator.hasNext()) {
            val value = iterator.next()
            if (value.isEmpty()) {
                iterator.remove()
            }
        }


        mSearchListRecyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = SearchHistoryRvAdapter(R.layout.item_search_history_layout, searchHistoryList)
        mSearchListRecyclerView.adapter  = adapter
        adapter.bindToRecyclerView(mSearchListRecyclerView)
        (mSearchListRecyclerView.adapter as SearchHistoryRvAdapter).onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val searchStr = adapter!!.data[position] as String
                etSearch.setText(searchStr)
            }
        }

        btnSearch.setOnClickListener {
            searchStr = etSearch.text.toString().trim()

            if (searchStr.isEmpty()) {
                ToastUtils.showShort("请输入关键词")
                return@setOnClickListener
            }

            when (rgGroup.checkedRadioButtonId) {
                R.id.rbType1 -> {
                    val intent = Intent(this,SortListActivity::class.java)
                    intent.putExtra("search",searchStr)
                    startActivity(intent)
                }

                R.id.rbType2 -> {
                    val intent = Intent(this,ShopListActivity::class.java)
                    intent.putExtra("search",searchStr)
                    startActivity(intent)
                }
            }

            if (searchHistoryList.contains(searchStr.trim())) {
                return@setOnClickListener
            }

            if (searchHistoryList.size < 10) {
                searchHistoryList.add(searchStr)
            } else {
                searchHistoryList.set(0, searchStr)
            }

            adapter.notifyDataSetChanged()
        }

        btnClear.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("是否清空历史搜索记录")
                    .setNegativeButton("取消") { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("确定") { dialog, which ->
                        searchHistoryList.clear()
                        mSearchListRecyclerView.adapter!!.notifyDataSetChanged()
                        dialog?.dismiss()
                    }.create()
                    .show()

        }


    }


    override fun finish() {
        for ((index, item) in searchHistoryList.withIndex()) {
            if (index == searchHistoryList.size - 1) {
                stringBuilder.append("$item")
            } else {
                stringBuilder.append("$item|+|")
            }
        }
        SPUtils.getInstance().put("history", stringBuilder.toString())
        super.finish()
    }

}
