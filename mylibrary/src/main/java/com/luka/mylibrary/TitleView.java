package com.luka.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends FrameLayout implements View.OnClickListener {

    private Drawable mTitleMoreDrawable;
    private Drawable mTitleMore2Drawable;
    private boolean mShowDivider;
    private int mBackBlack;
    private String mTitleMoreText;
    private int mTitleColor;
    private boolean mIsShowBack;
    private int mBackground;
    private TextView tvTitle;
    private ImageView ivMore;
    private ImageView ivMore2;
    private String mTitleText;
    private ImageView ivBack;
    private ImageView ivMoreSecond;
    private RelativeLayout rlyt_title;
    private LinearLayout llLine;
    private TextView tvMore;

    private OnTitleClickListener listener;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.TitleView_titleText) {
                mTitleText = typedArray.getString(attr);
            } else if (attr == R.styleable.TitleView_titleColor) {
                mTitleColor = typedArray.getColor(attr, defStyleAttr);
            } else if (attr == R.styleable.TitleView_showBack) {
                mIsShowBack = typedArray.getBoolean(attr, false);
            } else if (attr == R.styleable.TitleView_titleBackground) {
                mBackground = typedArray.getColor(attr, defStyleAttr);
            } else if (attr == R.styleable.TitleView_titleMoreText) {
                mTitleMoreText = typedArray.getString(attr);
            } else if (attr == R.styleable.TitleView_titleMoreDrawable) {
                mTitleMoreDrawable = typedArray.getDrawable(attr);
            } else if (attr == R.styleable.TitleView_titleMore2Drawable) {
                mTitleMore2Drawable = typedArray.getDrawable(attr);
            } else if (attr == R.styleable.TitleView_showDivider) {
                mShowDivider = typedArray.getBoolean(attr, true);
            } else if (attr == R.styleable.TitleView_backBlack) {
                mBackBlack = typedArray.getColor(attr, defStyleAttr);
            }
        }
        typedArray.recycle();

        LayoutInflater.from(context).inflate(R.layout.layout_widget_title, this);
        initView();
    }

    private void initView() {
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        ivBack.setVisibility(mIsShowBack ? VISIBLE : GONE);

        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(mTitleText);
        tvTitle.setTextColor(mTitleColor == 0 ? Color.BLACK : mTitleColor);

        tvMore = findViewById(R.id.tvMore);
        tvMore.setText(mTitleMoreText);
        tvMore.setOnClickListener(this);

        ivMore = findViewById(R.id.ivMore);
        ivMore.setImageDrawable(mTitleMoreDrawable);

        ivMore2 = findViewById(R.id.ivMoreSecond);
        ivMore2.setImageDrawable(mTitleMore2Drawable);

        rlyt_title = findViewById(R.id.rlyt_title);
        rlyt_title.setBackgroundColor(mBackground);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            if (v.getId() == R.id.tvMore) {
                listener.onMoreTextClick();
            }
            if (v.getId() == R.id.ivBack) {
                listener.onBackClick();
            }
        }
    }

    public interface OnTitleClickListener {
        void onBackClick();

        void onMoreTextClick();

        void onMoreImgClick();

        void onMoreImg2Click();
    }

    public void setOnTitleClickListener(OnTitleClickListener listener) {
        this.listener = listener;
    }


    public void setTitle(String text) {
        if (text.length() > 12) {
            tvTitle.setText(text.substring(0, 12));
        } else {
            tvTitle.setText(text);
        }
    }

    public void setTitleVisible(boolean visible) {
        tvTitle.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置右边的图片
     *
     * @param drawable
     */
    public void setMoreImg(int drawable, OnClickListener listener) {
        ivMore = (ImageView) findViewById(R.id.ivMore);
        ivMore.setVisibility(View.VISIBLE);
        ivMore.setImageResource(drawable);
        ivMore.setOnClickListener(listener);
    }

    /**
     * 设置右边的点击事件
     *
     * @param listener
     */
    public void setMoreClickListener(OnClickListener listener) {
        if (ivMore == null) {
            ivMore = (ImageView) findViewById(R.id.ivMore);
        }
        ivMore.setVisibility(View.VISIBLE);
        ivMore.setOnClickListener(listener);
    }

    /**
     * 设置右边的文字
     *
     * @param text
     */
    public void setMoreText(String text, OnClickListener listener) {
        tvMore.setVisibility(VISIBLE);
        tvMore.setText(text);
        tvMore.setOnClickListener(listener);
    }

    public void setBackListener(OnClickListener listener) {
        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setVisibility(VISIBLE);
        ivBack.setOnClickListener(listener);
    }

    public void setBackVisible(boolean visible) {
        ivBack.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置右边的图片
     *
     * @param drawable
     */
    public void setMoreSecondImg(int drawable, OnClickListener listener) {
        ivMoreSecond = findViewById(R.id.ivMoreSecond);
        ivMoreSecond.setVisibility(View.VISIBLE);
        ivMoreSecond.setImageResource(drawable);
        ivMoreSecond.setOnClickListener(listener);
    }

    public void setTitleTextColor(int color) {
        tvTitle.setTextColor(color);
        tvMore.setTextColor(color);
//        ivBack.setImageResource(R.drawable.icon_back_white);
    }

    public void setBackDrawable(int drawable) {
        ivBack.setImageResource(drawable);
    }

    /**
     * 分割线是否可见
     *
     * @param isInvisible
     */
    public void setSplitLineInvisible(boolean isInvisible) {
        if (isInvisible) llLine.setVisibility(GONE);
    }

    public void setBackColorWhite(boolean b) {
//        if (b) ivBack.setImageResource(R.drawable.icon_back_white);
    }
}
