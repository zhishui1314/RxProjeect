package com.anke.rxprojeect;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 创建作者： 张蔡奇
 * 创建时间： 2016/11/1
 * 创建公司： 珠海市安克电子技术有限公司合肥分公司
 *
 */
public class XImageView extends RelativeLayout {
    private ImageView imageView;
    private int mResrco;

    public XImageView(Context context) {
        this(context, null);
    }

    public XImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XImageView, defStyleAttr, 0);
        mResrco = a.getResourceId(R.styleable.XImageView_custsrc,R.mipmap.ic_launcher);
        a.recycle();
        initUI();
    }

    private void initUI() {
        View rootView = View.inflate(getContext(), R.layout.click_view, this);
        imageView =rootView.findViewById(R.id.img_icon);
        imageView.setImageResource(mResrco);
    }
}
