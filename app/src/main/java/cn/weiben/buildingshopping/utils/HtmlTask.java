package cn.weiben.buildingshopping.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * TextView加载带图片html文字
 */
public class HtmlTask extends AsyncTask<String, Integer, Spanned> {
    WeakReference<TextView> v;
    int lineSpacingExtra;

    public HtmlTask(TextView v) {
        this.v = new WeakReference<>(v);
        lineSpacingExtra = (int) v.getLineSpacingExtra();
    }

    @Override
    protected Spanned doInBackground(String... strings) {
        Spanned spanned = Html.fromHtml(strings[0], new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                InputStream is = null;
                try {
                    is = (InputStream) new URL(source).getContent();
                    Drawable d = Drawable.createFromStream(is, "src");

                    double scale = ScreenUtils.getScreenWidth() * 1.0 / d.getIntrinsicWidth();
                    d.setBounds(0, 0, ScreenUtils.getScreenWidth(),
                            (int) (d.getIntrinsicHeight() * scale));
                    is.close();
                    return d;
                } catch (Exception e) {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    return null;
                }
            }
        }, null);
        if (spanned instanceof SpannableStringBuilder) {
            ImageSpan[] imageSpans = spanned.getSpans(0, spanned.length(), ImageSpan.class);
            for (ImageSpan imageSpan : imageSpans) {
                int start = spanned.getSpanStart(imageSpan);
                int end = spanned.getSpanEnd(imageSpan);
                Drawable d = imageSpan.getDrawable();
                ImageSpan newImageSpan = new StickerSpan(d, ImageSpan.ALIGN_BASELINE, lineSpacingExtra);
                ((SpannableStringBuilder) spanned).setSpan(newImageSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                ((SpannableStringBuilder) spanned).removeSpan(imageSpan);
            }
        }

        return spanned;
    }

    @Override
    protected void onPostExecute(Spanned spanned) {
        if (v.get() != null) {
            v.get().setText(spanned);
        }
    }
}

/**
 * 让html中图片和文字对齐
 */
class StickerSpan extends ImageSpan {
    int lineSpacingExtra;

    StickerSpan(Drawable b, int verticalAlignment, int lineSpacingExtra) {
        super(b, verticalAlignment);
        this.lineSpacingExtra = lineSpacingExtra;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = bottom - b.getBounds().bottom - lineSpacingExtra;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                if (Character.isLetterOrDigit(text.charAt(i))) {
                    transY -= paint.getFontMetricsInt().descent;
                    break;
                }
            }
        }
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}