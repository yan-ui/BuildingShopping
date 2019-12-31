package cn.weiben.buildingshopping.manager

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import java.security.MessageDigest


object GlideManager {

    /**
     * 默认圆角大小
     */
    private const val DEFAULT_PLACEHOLDER_ROUND_RADIUS = 4f

    /**
     * 默认占位图颜色
     */
    @ColorInt
    private const val DEFAULT_PLACEHOLDER_COLOR = Color.LTGRAY


    /**
     * 默认通用占位图资源
     * 优先显示此占位图，若此资源无效则默认使用以下资源
     * @see sCommonPlaceholderDrawable
     */
    private const val DEFAULT_COMMON_PLACEHOLDER = -1
    private var sCommonPlaceholderDrawable: Drawable? = null

    /**
     * 默认圆形图片占位图资源
     * 优先显示此占位图，若此资源无效则默认使用以下资源
     * @see sCirclePlaceholderDrawable
     */
    private const val DEFAULT_CIRCLE_PLACEHOLDER = -1
    private var sCirclePlaceholderDrawable: Drawable? = null

    /**
     * 默认圆角图片占位图资源
     * 优先显示此占位图，若此资源无效则默认使用以下资源
     * @see sRoundPlaceholderDrawable
     */
    private const val DEFAULT_ROUND_PLACEHOLDER = -1
    private var sRoundPlaceholderDrawable: Drawable? = null


    private val requestOptions: RequestOptions = RequestOptions()
            .centerCrop()// 填充方式
            .priority(Priority.HIGH)  //优先级
            .diskCacheStrategy(DiskCacheStrategy.ALL)  //缓存策略

    private val requestOptionsCenterInside: RequestOptions = RequestOptions()
            .centerInside()// 填充方式
            .priority(Priority.HIGH)//优先级
            .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略

    private val requestOptionsAuto: RequestOptions = RequestOptions()
            .priority(Priority.HIGH)//优先级
            .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略

    /**
     * 初始化占位图
     * GradientDrawable：shape标签的代码实现
     */
    init {
        sCommonPlaceholderDrawable = GradientDrawable()
        sCirclePlaceholderDrawable = GradientDrawable()
        sRoundPlaceholderDrawable = GradientDrawable()
        setDrawable(sCommonPlaceholderDrawable as GradientDrawable, 0f)
        setDrawable(sCirclePlaceholderDrawable as GradientDrawable, 10000f)
        setDrawable(sRoundPlaceholderDrawable as GradientDrawable, DEFAULT_PLACEHOLDER_ROUND_RADIUS)
    }


    /**
     * 设置圆形图片的占位图
     *
     * @param circlePlaceholder
     */
    fun setCirclePlaceholder(circlePlaceholder: Drawable) {
        sCirclePlaceholderDrawable = circlePlaceholder
    }

    /**
     * 设置正常图片的占位符
     *
     * @param commonPlaceholder
     */
    fun setCommonPlaceholder(commonPlaceholder: Drawable) {
        sCommonPlaceholderDrawable = commonPlaceholder
    }

    /**
     * 设置圆角图片的占位符
     *
     * @param roundPlaceholder
     */
    fun setRoundPlaceholder(roundPlaceholder: Drawable) {
        sRoundPlaceholderDrawable = roundPlaceholder
    }

    /**
     * 普通加载图片
     *
     * @param obj 图片路径
     * @param iv  需要显示的ImageView
     * @param placeholderResource
     */
    @JvmOverloads
    fun loadImg(obj: Any, iv: ImageView, placeholderResource: Int = DEFAULT_COMMON_PLACEHOLDER) {
        val placeholder = getDrawable(iv.context, placeholderResource)

        Glide.with(iv.context)
                .load(obj)
                .apply(requestOptions
                        .error(placeholder ?: sCommonPlaceholderDrawable)
                        .placeholder(placeholder ?: sCommonPlaceholderDrawable)
                        .fallback(placeholder ?: sCommonPlaceholderDrawable)
                ).into(iv)
    }

    @JvmOverloads
    fun loadImgCenterInside(obj: Any, iv: ImageView, placeholderResource: Int = DEFAULT_COMMON_PLACEHOLDER) {
        val placeholder = getDrawable(iv.context, placeholderResource)

        Glide.with(iv.context)
                .load(obj)
                .apply(requestOptionsCenterInside
                        .error(placeholder ?: sCommonPlaceholderDrawable)
                        .placeholder(placeholder ?: sCommonPlaceholderDrawable)
                        .fallback(placeholder ?: sCommonPlaceholderDrawable)
                ).into(iv)
    }

    @JvmOverloads
    fun loadImgAuto(obj: Any, iv: ImageView, placeholderResource: Int = DEFAULT_COMMON_PLACEHOLDER) {
        val placeholder = getDrawable(iv.context, placeholderResource)
        Glide.with(iv.context)
                .load(obj)
                .apply(requestOptionsAuto
                        .error(placeholder ?: sCommonPlaceholderDrawable)
                        .placeholder(placeholder ?: sCommonPlaceholderDrawable)
                        .fallback(placeholder ?: sCommonPlaceholderDrawable)
                ).into(iv)
    }

    /**
     * 加载圆形图片
     *
     * @param obj
     * @param iv
     * @param placeholder 占位图
     */
    @JvmOverloads
    fun loadCircleImg(obj: Any, iv: ImageView, placeholderResource: Int = DEFAULT_CIRCLE_PLACEHOLDER) {
        val placeholder = getDrawable(iv.context, placeholderResource)

        Glide.with(iv.context)
                .load(obj)
                .apply(requestOptions
                        .error(placeholder ?: sCirclePlaceholderDrawable)
                        .placeholder(placeholder ?: sCirclePlaceholderDrawable)
                        .fallback(placeholder ?: sCirclePlaceholderDrawable)
                        .transform(CircleCrop())
                ).into(iv)
    }

    /**
     * 加载圆角图片
     *
     * @param obj                 加载的图片资源
     * @param iv                  ImageView
     * @param dp_radius           圆角尺寸-dp
     * @param placeholder         -占位图
     * @param isOfficial          -是否官方模式圆角
     */
    @JvmOverloads
    fun loadRoundImg(obj: Any, iv: ImageView, dp_radius: Float = DEFAULT_PLACEHOLDER_ROUND_RADIUS, placeholderResource: Int = DEFAULT_ROUND_PLACEHOLDER, isOfficial: Boolean = false) {
        val placeholder = getDrawable(iv.context, placeholderResource)

        Glide.with(iv.context)
                .load(obj)
                .apply(requestOptions
                        .error(placeholder ?: sRoundPlaceholderDrawable)
                        .placeholder(placeholder ?: sRoundPlaceholderDrawable)
                        .fallback(placeholder ?: sRoundPlaceholderDrawable)
                        .transform(if (isOfficial) RoundedCorners(dp2px(dp_radius)) else CenterCropRoundCornerTransform(dp2px(dp_radius)))
                ).into(iv)

    }


    /**
     * 自定义加载部分圆角图片
     *
     * @param obj                 加载的图片资源
     * @param iv                  ImageView
     * @param dp_radius           圆角尺寸-dp
     * @param leftTop
     * @param rightTop
     * @param leftBottom
     * @param rightBottom
     *
     */
    @JvmOverloads
    fun loadCustomRoundImg(obj: Any, iv: ImageView, dp_radius: Float = DEFAULT_PLACEHOLDER_ROUND_RADIUS, leftTop: Boolean = true, rightTop: Boolean = true, leftBottom: Boolean = true, rightBottom: Boolean = true) {
        val transform = RoundedCornersTransform(iv.context, dp2px(dp_radius).toFloat())
        transform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom)

        Glide.with(iv.context)
                .asBitmap()
                .load(obj)
                .apply(requestOptions
                        .error(sRoundPlaceholderDrawable)
                        .placeholder(sRoundPlaceholderDrawable)
                        .fallback(sRoundPlaceholderDrawable)
                        .transform(transform)
                ).into(iv)
    }


    private class CenterCropRoundCornerTransform(private val radius: Int) : CenterCrop() {

        override fun transform(pool: BitmapPool, toTransform: Bitmap,
                               outWidth: Int, outHeight: Int): Bitmap? {
            val transform = super.transform(pool, toTransform, outWidth, outHeight)
            return roundCrop(pool, transform)
        }

        private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
            if (source == null)
                return null
            var result: Bitmap? = pool.get(source.width, source.height,
                    Bitmap.Config.ARGB_8888)
            if (result == null) {
                result = Bitmap.createBitmap(source.width, source.height,
                        Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(result!!)
            val paint = Paint()
            paint.shader = BitmapShader(source, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP)
            paint.isAntiAlias = true
            val rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
            canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)
            return result
        }

    }

    /**
     * 设置图片部分圆角
     * @param context 上下文
     * @param radius  圆角幅度
     */
    class RoundedCornersTransform(context: Context, private var radius: Float) : Transformation<Bitmap> {

        private val mBitmapPool: BitmapPool

        private var isLeftTop: Boolean = false
        private var isRightTop: Boolean = false
        private var isLeftBottom: Boolean = false
        private var isRightBotoom: Boolean = false

        /**
         * 需要设置圆角的部分
         *
         * @param leftTop     左上角
         * @param rightTop    右上角
         * @param leftBottom  左下角
         * @param rightBottom 右下角
         */
        fun setNeedCorner(leftTop: Boolean, rightTop: Boolean, leftBottom: Boolean, rightBottom: Boolean) {
            isLeftTop = leftTop
            isRightTop = rightTop
            isLeftBottom = leftBottom
            isRightBotoom = rightBottom
        }

        init {
            this.mBitmapPool = Glide.get(context).bitmapPool
        }

        override fun transform(context: Context, resource: Resource<Bitmap>, outWidth: Int, outHeight: Int): Resource<Bitmap> {

            val source = resource.get()
            var finalWidth: Int
            var finalHeight: Int
            //输出目标的宽高或高宽比例
            var scale: Float
            if (outWidth > outHeight) {
                //如果 输出宽度 > 输出高度 求高宽比

                scale = outHeight.toFloat() / outWidth.toFloat()
                finalWidth = source.width
                //固定原图宽度,求最终高度
                finalHeight = (source.width.toFloat() * scale).toInt()
                if (finalHeight > source.height) {
                    //如果 求出的最终高度 > 原图高度 求宽高比

                    scale = outWidth.toFloat() / outHeight.toFloat()
                    finalHeight = source.height
                    //固定原图高度,求最终宽度
                    finalWidth = (source.height.toFloat() * scale).toInt()
                }
            } else if (outWidth < outHeight) {
                //如果 输出宽度 < 输出高度 求宽高比

                scale = outWidth.toFloat() / outHeight.toFloat()
                finalHeight = source.height
                //固定原图高度,求最终宽度
                finalWidth = (source.height.toFloat() * scale).toInt()
                if (finalWidth > source.width) {
                    //如果 求出的最终宽度 > 原图宽度 求高宽比

                    scale = outHeight.toFloat() / outWidth.toFloat()
                    finalWidth = source.width
                    finalHeight = (source.width.toFloat() * scale).toInt()
                }
            } else {
                //如果 输出宽度=输出高度
                finalHeight = source.height
                finalWidth = finalHeight
            }

            //修正圆角
            this.radius *= finalHeight.toFloat() / outHeight.toFloat()
            var outBitmap: Bitmap? = this.mBitmapPool.get(finalWidth, finalHeight, Bitmap.Config.ARGB_8888)
            if (outBitmap == null) {
                outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888)
            }

            val canvas = Canvas(outBitmap!!)
            val paint = Paint()
            //关联画笔绘制的原图bitmap
            val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            //计算中心位置,进行偏移
            val width = (source.width - finalWidth) / 2
            val height = (source.height - finalHeight) / 2
            if (width != 0 || height != 0) {
                val matrix = Matrix()
                matrix.setTranslate((-width).toFloat(), (-height).toFloat())
                shader.setLocalMatrix(matrix)
            }

            paint.shader = shader
            paint.isAntiAlias = true
            val rectF = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
            //先绘制圆角矩形
            canvas.drawRoundRect(rectF, this.radius, this.radius, paint)

            //左上角圆角
            if (!isLeftTop) {
                canvas.drawRect(0f, 0f, radius, radius, paint)
            }
            //右上角圆角
            if (!isRightTop) {
                canvas.drawRect(canvas.width - radius, 0f, canvas.width.toFloat(), radius, paint)
            }
            //左下角圆角
            if (!isLeftBottom) {
                canvas.drawRect(0f, canvas.height - radius, radius, canvas.height.toFloat(), paint)
            }
            //右下角圆角
            if (!isRightBotoom) {
                canvas.drawRect(canvas.width - radius, canvas.height - radius, canvas.width.toFloat(), canvas.height.toFloat(), paint)
            }

            return BitmapResource.obtain(outBitmap, this.mBitmapPool)!!
        }


        override fun updateDiskCacheKey(messageDigest: MessageDigest) {}
    }

    private fun dp2px(dipValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    private fun getDrawable(context: Context, @DrawableRes res: Int): Drawable? {
        var drawable: Drawable? = null
        try {
            drawable = context.resources.getDrawable(res)
        } catch (e: Exception) {

        }
        return drawable
    }

    private fun setDrawable(gd: GradientDrawable, radius: Float) {
        gd.setColor(DEFAULT_PLACEHOLDER_COLOR)
        gd.cornerRadius = radius
    }
}