package cn.weiben.buildingshopping.ui.mine.setting;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 描述
 */
public class UserSettingPresenter extends BasePresenter<UserSettingContract.View> implements UserSettingContract.Presenter {

    @Override
    public void getUser() {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getUserProfile()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setUser(result.getData());
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void setUserProfile(List<LocalMedia> headimgFile, String user_name, String birthday, int sex, String email) {
        mView.showLoading();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user_name", user_name)
                .addFormDataPart("sex", "" + sex)
                .addFormDataPart("email", email)
                .addFormDataPart("birthday", birthday);

        if (headimgFile != null && headimgFile.size() > 0) {
            builder.addFormDataPart("headimg", headimgFile.get(0).getFileName(), RequestBody.create(MediaType.parse(headimgFile.get(0).getMimeType()), new File(headimgFile.get(0).getPath())));
        }


        RetrofitHelper.getInstance().getServer()
                .setUserProfile(builder.build())
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });


    }

    @Override
    public void setUserPassword(String oldPass, String newPass, String confirmPass) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .setUserPassword(oldPass, newPass, confirmPass)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
