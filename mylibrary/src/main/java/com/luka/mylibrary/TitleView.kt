package com.luka.mylibrary

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.sdk25.coroutines.onClick


internal class TitleView : FrameLayout {

    private var mTitleText: String = ""
    private var mTitleMoreText: String = ""
    private lateinit var mTitleMoreDrawable: Drawable
    private var mTitleColor: Int = context.resources.getColor(R.color.black)
    private var mTitleBackgroundColor = context.resources.getColor(R.color.white)
    private var mIsShowBack: Boolean = true
    private var mBackBlack: Boolean = true
    private var mShowDivider = true

    private lateinit var tvTitle: TextView
    private lateinit var tvMore: TextView
    private lateinit var rlyt_title: RelativeLayout
    private lateinit var ivBack: ImageView


    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.TitleView)
        (0..typedArray.indexCount)
                .map { typedArray.getIndex(it) }
                .forEach {
                    when (it) {
                        R.styleable.TitleView_titleText ->
                            mTitleText = typedArray.getString(it)
                        R.styleable.TitleView_titleMoreText ->
                            mTitleMoreText = typedArray.getString(it)
                        R.styleable.TitleView_titleMoreDrawable ->
                            mTitleMoreDrawable = typedArray.getDrawable(it)
                        R.styleable.TitleView_titleBackground ->
                            mTitleBackgroundColor = typedArray.getColor(it, context.resources.getColor(R.color.white))
                        R.styleable.TitleView_titleColor ->
                            mTitleColor = typedArray.getColor(it, context.resources.getColor(R.color.gray33))
                        R.styleable.TitleView_showBack ->
                            mIsShowBack = typedArray.getBoolean(it, false)
                        R.styleable.TitleView_backBlack ->
                            mBackBlack = typedArray.getBoolean(it, false)
                        R.styleable.TitleView_showDivider ->
                            mShowDivider = typedArray.getBoolean(it, true)
                    }
                }
        typedArray.recycle()

        LayoutInflater.from(context).inflate(R.layout.layout_widget_title, this)
        initView()
    }


    private fun initView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvMore = findViewById(R.id.tvMore)
        ivBack = findViewById(R.id.ivBack)
        rlyt_title = findViewById(R.id.rlyt_title)
        tvTitle.text = mTitleText
        tvTitle.setTextColor(mTitleColor)
        rlyt_title.setBackgroundColor(mTitleBackgroundColor)
        ivBack.visibility = if (mIsShowBack) View.VISIBLE else View.GONE
        ivBack.setImageResource(if (mBackBlack) {
            R.mipmap.ic_back
        } else {
            R.mipmap.ic_back_white
        })
        ivBack.onClick {
//            (context as BaseActivity).myFinish()
        }
        if (!TextUtils.isEmpty(mTitleMoreText)) {
            tvMore.visibility = View.VISIBLE
            tvMore.text = mTitleMoreText
        }
//        ivMore.setImageDrawable(mTitleMoreDrawable)
//        llLine.visibility = if (mShowDivider) View.VISIBLE else View.GONE
        postInvalidate()
    }

    fun setOnMoreClickListener(listener: OnClickListener) {
        tvMore.setOnClickListener(listener)
    }

    fun setMoreText(text: String) {
        tvMore.text = text
    }

    fun setMoreDrawable(drawable: Drawable) {
//        ivMore.setImageDrawable(drawable)
    }


    fun setTitle(text: String?) {
        if (!TextUtils.isEmpty(text)) {
            tvTitle.text = text
            postInvalidate()
        }
    }

    fun showDivider(visible: Int) {
//        llLine.visibility = visible
    }
}