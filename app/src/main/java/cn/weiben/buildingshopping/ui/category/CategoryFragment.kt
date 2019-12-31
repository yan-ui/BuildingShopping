package cn.weiben.buildingshopping.ui.category

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment
import cn.weiben.buildingshopping.model.CategoryBean
import cn.weiben.buildingshopping.model.ChildBean
import cn.weiben.buildingshopping.ui.adapter.CategoryContentRVAdapter
import cn.weiben.buildingshopping.ui.adapter.CategoryMenuRVAdapter
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : BaseMVPFragment<CategoryPresenter>(), CategoryContract.View {

    override fun initPresenter(): CategoryPresenter {
        return CategoryPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {
        categoryTitleBar.setBackgroundResource(R.color.white)

        mPresenter.getCategoryList()
    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getCategoryList()
    }


    override fun lazyData() {

    }

    private lateinit var menuRVAdapter: CategoryMenuRVAdapter
    private lateinit var contentRVAdapter: CategoryContentRVAdapter


    override fun setCategoryList(list: List<CategoryBean>?) {
        if (list == null) {
            return
        }

        val menuList = ArrayList<String>()
        list.forEach {
            menuList.add(it.name)
        }

        initMenuRecyclerView(menuList, list)
        if (list.isNotEmpty()) {
            initContentRecyclerView(list[0].child)
        }

    }

    private fun initContentRecyclerView(categoryBean: MutableList<ChildBean>) {
        rightRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        contentRVAdapter = CategoryContentRVAdapter(categoryBean)
        contentRVAdapter.bindToRecyclerView(rightRecyclerView)
        rightRecyclerView.adapter = contentRVAdapter

    }


    private fun initMenuRecyclerView(menuList: List<String>, listBean: List<CategoryBean>) {
        leftRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        menuRVAdapter = CategoryMenuRVAdapter(menuList)
        menuRVAdapter.bindToRecyclerView(leftRecyclerView)
        leftRecyclerView.adapter = menuRVAdapter
        leftRecyclerView.addItemDecoration(RecycleViewDivider(mActivity, LinearLayout.VERTICAL))

        menuRVAdapter.setOnItemClickListener { adapter, view, position ->
            menuRVAdapter.setSelectItemPosition(position)
            contentRVAdapter.setNewData(listBean[position].child)
        }
    }

}
