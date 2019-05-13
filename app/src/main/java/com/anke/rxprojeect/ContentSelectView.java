package com.anke.rxprojeect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 创建作者： 张蔡奇
 * 创建时间： 2016/11/1
 * 创建公司： 珠海市安克电子技术有限公司合肥分公司
 */
public class ContentSelectView extends RelativeLayout {
    private TextView tvName;//名字
    private TextView tvContent;//内容
    private RelativeLayout rlContentView;
    private String name;
    private String content;
    private int model;// 模式  0 右中 1左中
    private int mResrco;
    //    private int mResrco;

    public ContentSelectView(Context context) {
        this(context, null);
    }

    public ContentSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ContentSelectView, defStyleAttr, 0);
//        mResrco = a.getResourceId(R.styleable.XImageView_custsrc,0);
        name = a.getString(R.styleable.ContentSelectView_csvName);
        content = a.getString(R.styleable.ContentSelectView_csvContent);
        model = a.getInt(R.styleable.ContentSelectView_showModel, 0);
        mResrco = a.getResourceId(R.styleable.ContentSelectView_csvDraw, R.mipmap.ic_launcher);
        a.recycle();
        initUI();
    }

    private void initUI() {
        View rootView = View.inflate(getContext(), R.layout.content_select_view, this);
        tvName = rootView.findViewById(R.id.tv_content_name);
        tvContent = rootView.findViewById(R.id.tv_content_content);
        rlContentView = rootView.findViewById(R.id.rl_content_view);
        tvName.setText(name);
        tvContent.setText(content);
        setModel(model);
        setRightDraw(mResrco);
        tvContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                contentSelectViewOnclickListener.onClick(v);
            }
        });
    }

    /**
     * 设置图片
     *
     * @param recourse
     */
    public void setRightDraw(int recourse) {
        Drawable drawable = getResources().getDrawable(recourse);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvContent.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 获取内容
     *
     * @return
     */
    public String getContentText() {
        return tvContent.getText().toString().trim();
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(String content) {
        tvContent.setText(TextUtils.isEmpty(content) ? "" : content);
    }

    /**
     * 设置名字
     *
     * @param name
     */
    public void setName(String name) {
        tvName.setText(TextUtils.isEmpty(name) ? "" : name);
    }

    /**
     * 设置模式 0 左中  1 右中
     *
     * @param model
     */
    public void setModel(int model) {
        if (model == 0) {
            tvContent.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        } else {
            tvContent.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        }
    }

    public void setShow(Boolean defaultValue) {
        rlContentView.setVisibility(defaultValue ? View.VISIBLE : View.GONE);
    }

    private ContentSelectViewOnclickListener contentSelectViewOnclickListener;

    public ContentSelectViewOnclickListener getContentSelectViewOnclickListener() {
        return contentSelectViewOnclickListener;
    }

    public void setContentSelectViewOnclickListener(ContentSelectViewOnclickListener contentSelectViewOnclickListener) {
        this.contentSelectViewOnclickListener = contentSelectViewOnclickListener;
    }

    interface ContentSelectViewOnclickListener {
        void onClick(View v);
    }
}
