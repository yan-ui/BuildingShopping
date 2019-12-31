package cn.weiben.buildingshopping.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.NewsBean;


public class TestRVAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {

    public TestRVAdapter(List<NewsBean> data) {
        super(R.layout.item_my_integral_detail, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsBean bean) {

        helper.addOnClickListener(R.id.ivImageOne);

        helper.setGone(R.id.wechatLayout, false);
        helper.setGone(R.id.newsLayout, true);

        helper.setText(R.id.tvTitle, bean.getName());
        helper.setText(R.id.tvTime, bean.getBegintime());

        if (!StringUtils.isEmpty(bean.getVideo())) {
            helper.setGone(R.id.videoLayout, true);
            helper.setGone(R.id.llMultiImage, false);
            helper.setGone(R.id.ivImageOne, false);

            // 加载网络图片
            Glide.with(mContext).load(bean.getImage()).into((ImageView) helper.getView(R.id.ivVideoBg));

            helper.addOnClickListener(R.id.ivStartPlayer);
        } else {
            helper.setGone(R.id.videoLayout, false);

            if (!StringUtils.isEmpty(bean.getImage())) {
                //一张图片
                helper.setGone(R.id.llMultiImage, false);
                helper.setGone(R.id.ivImageOne, true);

                GlideManager.INSTANCE.loadRoundImg(bean.getImage(), helper.getView(R.id.ivImageOne));

            } else {

                if (bean.getImages() == null || bean.getImages().size() == 0) {
                    //没有图片
                    helper.setGone(R.id.llMultiImage, false);
                    helper.setGone(R.id.ivImageOne, false);

                } else {
                    if (bean.getImages().size() < 3) {
                        //一张图片
                        helper.setGone(R.id.llMultiImage, false);
                        helper.setGone(R.id.ivImageOne, true);

                        GlideManager.INSTANCE.loadRoundImg(bean.getImages().get(0), helper.getView(R.id.ivImageOne));
                    } else {
                        //多张图片
                        helper.setGone(R.id.llMultiImage, true);
                        helper.setGone(R.id.ivImageOne, false);

                        GlideManager.INSTANCE.loadRoundImg(bean.getImages().get(0), helper.getView(R.id.ivImageFirst));
                        GlideManager.INSTANCE.loadRoundImg(bean.getImages().get(1), helper.getView(R.id.ivImageSecond));
                        GlideManager.INSTANCE.loadRoundImg(bean.getImages().get(2), helper.getView(R.id.ivImageThree));
                    }
                }
            }

        }

    }
}
