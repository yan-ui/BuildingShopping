package cn.weiben.buildingshopping.api;


import java.util.List;

import cn.weiben.buildingshopping.api.model.BasePageModel;
import cn.weiben.buildingshopping.api.model.BaseResult;
import cn.weiben.buildingshopping.model.CategoryBean;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.GoodsDetail;
import cn.weiben.buildingshopping.model.HomeBean;
import cn.weiben.buildingshopping.model.LoginBean;
import cn.weiben.buildingshopping.model.NewDetails;
import cn.weiben.buildingshopping.model.NewsBean;
import cn.weiben.buildingshopping.model.ShopBean;
import cn.weiben.buildingshopping.model.UserBean;
import cn.weiben.buildingshopping.model.UserEditBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 首页接口
     *
     * @return
     */
    @GET("mobileapi/index.php")
    Observable<BaseResult<HomeBean>> getHomePage();


    /**
     * 首页请求商品列表接口
     *
     * @return
     */
    @GET("mobileapi/index_bestgoods.php")
    Observable<BaseResult<List<GoodsBean>>> getHomeGoodsList(@Query("last") int last,
                                                             @Query("amount") int amount);


    /**
     * 所有分类接口
     *
     * @return
     */
    @GET("mobileapi/catalog.php")
    Observable<BaseResult<List<CategoryBean>>> getCatalogList();

    /**
     * 获取分类下商品列表接口
     *
     * @return
     */
    @GET("mobileapi/category.php?act=ajax")
    Observable<BaseResult<List<GoodsBean>>> getCategorySortList(@Query("id") String id,
                                                                @Query("sort") String sort,
                                                                @Query("last") int last,
                                                                @Query("amount") int amount,
                                                                @Query("order") String order);

    /**
     * 用户信息
     *
     * @return
     */
    @GET("mobileapi/user.php?act=default")
    Observable<BaseResult<UserBean>> getUser();

    /**
     * 获取用户设置详情
     *
     * @return
     */
    @GET("mobileapi/user.php?act=profile")
    Observable<BaseResult<UserEditBean>> getUserProfile();

    /**
     * 修改用户信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/user.php?act=act_edit_profile")
    Observable<BaseResult> setUserProfile(@Field("headimg") String headimg, @Field("user_name") String user_name,
                                                        @Field("birthday") String birthday, @Field("sex") int sex,
                                                        @Field("email") String email);


    /**
     * 修改用户密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/user.php?act=act_edit_password")
    Observable<BaseResult> setUserPassword(@Field("old_password") String old_password, @Field("new_password") String new_password,
                                          @Field("comfirm_password") String comfirm_password);



    /**
     * 获取验证码图片
     *
     * @return
     */
    @GET("mobileapi/captcha.php")
    Observable<String> getCaptcha();


    /**
     * 获取短信验证码接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/register.php?act=send_mobile_code")
    Observable<BaseResult> getSms(@Field("mobile_phone") String mobile_phone, @Field("captcha") String captcha);


    /**
     * 登录接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/login.php")
    Observable<BaseResult<LoginBean>> login(@Field("username") String username, @Field("password") String password);


    /**
     * 注册接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/register.php?act=register")
    Observable<BaseResult> register(@Field("register_type") String mobile, @Field("mobile_phone") String mobile_phone,
                                    @Field("password") String password, @Field("captcha") String captcha,
                                    @Field("mobile_code") String mobile_code);


    /**
     * 首页店铺接口
     *
     * @return
     */
    @GET("mobileapi/supplier_pinpai.php")
    Observable<BaseResult<ShopBean>> getShopBean(@Query("page") int page);


    /**
     * 获取商品详情接口
     *
     * @return
     */
    @GET("mobileapi/goods.php")
    Observable<BaseResult<GoodsDetail>> getGoodsDetail(@Query("id") String id);


    /**
     * 请求文章详情接口
     *
     * @return
     */
    @GET("mobileapi/article.php")
    Observable<BaseResult<NewDetails>> getNewsDetail(@Query("id") String id);


    /**
     * 测试数据接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/article/index")
    Observable<BaseResult<BasePageModel<NewsBean>>> index(@Field("module") String module, @Field("p") int p);


}