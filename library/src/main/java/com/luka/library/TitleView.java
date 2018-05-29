package com.luka.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends FrameLayout implements View.OnClickListener {

    private Drawable mTitleBackDrawable;
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
    private RelativeLayout rlContainer;
    private TextView tvMore;

    private OnBackClickListener mBackClickListener;
    private OnMoreTextClickListener mMoreTextClickListener;
    private OnMoreImgClickListener mMoreImgClickListener;
    private OnMoreImg2ClickListener mMoreImg2ClickListener;

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
            } else if (attr == R.styleable.TitleView_titleBackDrawable) {
                mTitleBackDrawable = typedArray.getDrawable(attr);
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
        ivBack.setImageDrawable(mTitleBackDrawable);

        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(mTitleText);
        tvTitle.setTextColor(mTitleColor == 0 ? Color.BLACK : mTitleColor);

        ivMore = findViewById(R.id.ivMore);
        ivMore.setImageDrawable(mTitleMoreDrawable);

        ivMore2 = findViewById(R.id.ivMoreSecond);
        ivMore2.setImageDrawable(mTitleMore2Drawable);

        tvMore = findViewById(R.id.tvMore);
        if (!TextUtils.isEmpty(mTitleMoreText)) {
            tvMore.setText(mTitleMoreText);
            tvMore.setOnClickListener(this);
        }

        rlContainer = findViewById(R.id.rlContainer);
        rlContainer.setBackgroundColor(mBackground);

        View vDivider = findViewById(R.id.vDivider);
        vDivider.setVisibility(mShowDivider ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvMore) {
            if (mMoreTextClickListener != null) {
                mMoreTextClickListener.onMoreTextClick();
            }
            if (mBackClickListener != null) {
                mBackClickListener.onBackClick();
            }
            if (mMoreImgClickListener != null) {
                mMoreImgClickListener.onMoreImgClick();
            }
            if (mMoreImg2ClickListener != null) {
                mMoreImg2ClickListener.onMoreImg2Click();
            }
        }
    }

    public interface OnBackClickListener {
        void onBackClick();
    }

    public interface OnMoreTextClickListener {
        void onMoreTextClick();
    }

    public interface OnMoreImgClickListener {
        void onMoreImgClick();
    }

    public interface OnMoreImg2ClickListener {
        void onMoreImg2Click();
    }

    public void setOnMoreTextClickListener(OnMoreTextClickListener listener) {
        this.mMoreTextClickListener = listener;
    }

    public void setOnBackClickListener(OnBackClickListener listener) {
        this.mBackClickListener = listener;
    }

    public void setOnImgkClickListener(OnMoreImgClickListener listener) {
        this.mMoreImgClickListener = listener;
    }

    public void setOnImg2kClickListener(OnMoreImg2ClickListener listener) {
        this.mMoreImg2ClickListener = listener;
    }

}
