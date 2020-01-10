package cn.weiben.buildingshopping.api;


import java.util.List;

import cn.weiben.buildingshopping.api.model.BasePageModel;
import cn.weiben.buildingshopping.api.model.BaseResult;
import cn.weiben.buildingshopping.model.AddressModel;
import cn.weiben.buildingshopping.model.BonusBean;
import cn.weiben.buildingshopping.model.BuyGoods;
import cn.weiben.buildingshopping.model.CartBean;
import cn.weiben.buildingshopping.model.CategoryBean;
import cn.weiben.buildingshopping.model.CollectGoodsBean;
import cn.weiben.buildingshopping.model.CollectShopBean;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.GoodsDetail;
import cn.weiben.buildingshopping.model.HomeBean;
import cn.weiben.buildingshopping.model.LeaveMessageBean;
import cn.weiben.buildingshopping.model.LoginBean;
import cn.weiben.buildingshopping.model.MoneyManagerBean;
import cn.weiben.buildingshopping.model.NewDetails;
import cn.weiben.buildingshopping.model.NewsBean;
import cn.weiben.buildingshopping.model.OrderResultBean;
import cn.weiben.buildingshopping.model.ShopBean;
import cn.weiben.buildingshopping.model.ShopDetailsBean;
import cn.weiben.buildingshopping.model.UserBean;
import cn.weiben.buildingshopping.model.UserEditBean;
import cn.weiben.buildingshopping.model.address_picker.AddressBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
     * 领取红包接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/user.php?act=act_add_bonus")
    Observable<BaseResult> addBonus(@Field("bonus_sn") String bonus_sn);

    /**
     * 获取留言列表接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=message_list")
    Observable<BaseResult<LeaveMessageBean>> getMessage(@Query("page") int page);

    /**
     * 获取红包列表接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=bonus")
    Observable<BaseResult<BonusBean>> getBonusList(@Query("page") int page);

    /**
     * 用户添加留言接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/user.php?act=act_add_message")
    Observable<BaseResult> addMessage(@Field("msg_type") int type, @Field("msg_title") String msg_title, @Field("msg_content") String msg_content);


    /**
     * 获取当前用户地址列表
     *
     * @return
     */
    @GET("mobileapi/user.php?act=address_list")
    Observable<BaseResult<List<AddressModel>>> getAddressList();


    /**
     * 删除用户地址接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=drop_consignee")
    Observable<BaseResult> deleteAddress(@Query("address_id") String address_id);

    /**
     * 设置地址为默认地址
     *
     * @return
     */
    @GET("mobileapi/user.php?act=set_address")
    Observable<BaseResult> setDefaultAddress(@Query("address_id") String address_id);

    /**
     * 获取地区父子节点接口
     *
     * @return
     */
    @GET("mobileapi/region.php")
    Observable<BaseResult<List<AddressBean>>> getAddressArea();

    /**
     * 保存收货地址接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/user.php?act=act_edit_address")
    Observable<BaseResult> addAddressArea(@Field("consignee") String consignee, @Field("email") String email,
                                          @Field("country") String country, @Field("province") String province,
                                          @Field("city") String city, @Field("district") String district,
                                          @Field("xiangcun") String xiangcun, @Field("address") String address,
                                          @Field("mobile") String mobile, @Field("zipcode") String zipcode,
                                          @Field("address_id") String address_id);


    /**
     * 获取地址详情接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=address")
    Observable<BaseResult<AddressModel>> getAddressDetails(@Query("address_id") String address_id);


    /**
     * 我的资产接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=account_manage")
    Observable<BaseResult<MoneyManagerBean>> getAccountManage();

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
    @POST("mobileapi/user.php?act=act_edit_profile")
    Observable<BaseResult> setUserProfile(@Body RequestBody body);

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
     * 店铺详情接口
     *
     * @return
     */
    @GET("mobileapi/supplier.php")
    Observable<BaseResult<ShopDetailsBean>> getShopDetails(@Query("suppId") String suppId);

    /**
     * 获取商品详情接口
     *
     * @return
     */
    @GET("mobileapi/goods.php")
    Observable<BaseResult<GoodsDetail>> getGoodsDetail(@Query("id") String id);


    /**
     * 商品搜索接口
     *
     * @return
     */
    @GET("mobileapi/search.php")
    Observable<BaseResult<List<GoodsBean>>> searchGoods(@Query("keywords") String keywords,
                                                        @Query("sort") String sort,
                                                        @Query("last") int last,
                                                        @Query("amount") int amount,
                                                        @Query("order") String order);


    /**
     * 店铺搜索接口
     *
     * @return
     */
    @GET("mobileapi/search.php?type=1")
    Observable<BaseResult<List<ShopBean.SupplierListBean>>> searchShopList(@Query("page") int page, @Query("keywords") String keywords);


    /**
     * 商品收藏接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=collect")
    Observable<BaseResult> collectGoods(@Query("id") String id);


    /**
     * 店铺收藏接口
     *
     * @return
     */
    @GET("mobileapi/supplier.php?go=other&act=add_guanzhu")
    Observable<BaseResult> collectShop(@Query("suppId") String suppId);


    /**
     * 添加商品到购物车
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobileapi/flow.php?step=add_to_cart")
    Observable<BaseResult> addShopCart(@Field("goods") String goods);


    /**
     * 购物车列表
     *
     * @return
     */
    @GET("mobileapi/flow.php")
    Observable<BaseResult<CartBean>> getCartList();

    /**
     * 购物车删除批量接口
     *
     * @return
     */
    @GET("mobileapi/flow.php?step=drop_goods")
    Observable<BaseResult> deleteCartGoods(@Query("id") String ids);


    /**
     * 请求文章详情接口
     *
     * @return
     */
    @GET("mobileapi/article.php")
    Observable<BaseResult<NewDetails>> getNewsDetail(@Query("id") String id);


    /**
     * 订单列表
     *
     * @return
     */
    @GET("mobileapi/user.php?act=order_list")
    Observable<BaseResult<OrderResultBean>> getOrderList(@Query("page") int page, @Query("composite_status") int composite_status);


    /**
     * 收藏的宝贝接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=collection_list")
    Observable<BaseResult<List<CollectGoodsBean>>> getCollectGoodsList(@Query("page") int page);

    /**
     * 删除收藏的商品接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=delete_collection")
    Observable<BaseResult> deleteCollcetGoods(@Query("rec_id") String rec_id);


    /**
     * 删除收藏的店铺接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=del_follow")
    Observable<BaseResult> deleteCollcetShop(@Query("rec_id") String rec_id);


    /**
     * 收藏的宝贝接口
     *
     * @return
     */
    @GET("mobileapi/user.php?act=follow_shop")
    Observable<BaseResult<List<CollectShopBean>>> getCollectShopList(@Query("page") int page);


    /**
     * 测试数据接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/article/index")
    Observable<BaseResult<BasePageModel<NewsBean>>> index(@Field("module") String module, @Field("p") int p);


}