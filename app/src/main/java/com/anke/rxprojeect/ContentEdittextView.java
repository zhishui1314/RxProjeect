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
public class ContentEdittextView extends RelativeLayout {
    private TextView tvName;//名字
    private TextView edContent;//内容
    private RelativeLayout rlContentView;
    private RelativeLayout rlCEV;//删除按钮
    private ImageView imgDraw;//图片
    private String name;
    private String content;
    private int model;// 模式  0 右中 1左中
    private int mResrco;
    //    private int mResrco;

    public ContentEdittextView(Context context) {
        this(context, null);
    }

    public ContentEdittextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentEdittextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ContentEdittextView, defStyleAttr, 0);
        name = a.getString(R.styleable.ContentEdittextView_cevName);
        content = a.getString(R.styleable.ContentEdittextView_cevContent);
        model = a.getInt(R.styleable.ContentEdittextView_cevShowModel, 0);
        mResrco = a.getResourceId(R.styleable.ContentEdittextView_cevDraw, R.mipmap.ic_launcher);
        a.recycle();
        initUI();
    }

    private void initUI() {
        View rootView = View.inflate(getContext(), R.layout.content_edittext_view, this);
        tvName = rootView.findViewById(R.id.tv_cedcontent_name);
        edContent = rootView.findViewById(R.id.ed_cedcontent_content);
        imgDraw = rootView.findViewById(R.id.img_cev_drw);
        rlContentView = rootView.findViewById(R.id.rl_cev_view);
        rlCEV = rootView.findViewById(R.id.rl_cev);
        tvName.setText(name);
        edContent.setText(content);
        setModel(model);
        setRightDraw(mResrco);
        rlCEV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               edContent.setText("");
            }
        });
    }

    /**
     * 设置图片
     *
     * @param recourse
     */
    public void setRightDraw(int recourse) {
        imgDraw.setImageResource(recourse);
    }

    /**
     * 获取内容
     *
     * @return
     */
    public String getContentText() {
        return edContent.getText().toString().trim();
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(String content) {
        edContent.setText(TextUtils.isEmpty(content) ? "" : content);
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
            edContent.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        } else {
            edContent.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        }
    }

    public void setShow(Boolean defaultValue) {
        rlContentView.setVisibility(defaultValue ? View.VISIBLE : View.GONE);
    }

    private ContentEdittextViewOnclickListener contentEdittextViewOnclickListener;

    public ContentEdittextViewOnclickListener getContentEdittextViewOnclickListener() {
        return contentEdittextViewOnclickListener;
    }

    public void setContentEdittextViewOnclickListener(ContentEdittextViewOnclickListener contentEdittextViewOnclickListener) {
        this.contentEdittextViewOnclickListener = contentEdittextViewOnclickListener;
    }

    interface ContentEdittextViewOnclickListener {
        void onClick(View v);
    }
}
