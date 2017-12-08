package linchange.com.customtemplate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lkmc2 on 2017/12/9.
 */

public class HeaderBar extends RelativeLayout {

    private TextView tvTitle; //标题文字
    private Button leftButton, rightButton; //左按钮、右按钮

    //标题文字，左按钮文字，右按钮文字
    private String titleText, leftButtonText, rightButtonText;
    
    private int titleTextColor; //标题文字颜色

    //标题文字大小，左按钮文字大小，右按钮文字颜色
    private float titleTextSize, leftButtonTextSize, rightButtonTextSize;
    
    //左按钮背景，右按钮背景
    private Drawable leftButtonBackground, rightButtonBackground;

    //标题参数，左按钮参数，右按钮参数
    private LayoutParams titleParams, leftButtonParams, rightButtonParams;

    public HeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initStyles(context, attrs); //初始化样式
        initViews(context); //初始化界面控件
        setStylesToViews(); //将样式设置到对应的控件
        addViewsToViewGroup(); //将控件添加到ViewGroup中
        setButtonClickEvent(); //设置按钮的点击事件
    }

    /**
     * 初始化样式
     * @param context 上下文
     * @param attrs 样式文件
     */
    private void initStyles(Context context, AttributeSet attrs) {
        //将之前声明的HeaderBar的样式转换成样式数组
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar);

        titleText = ta.getString(R.styleable.HeaderBar_titleText); //标题文字
        titleTextSize = ta.getDimension(R.styleable.HeaderBar_titleTextSize, 0); //标题字体大小
        titleTextColor = ta.getColor(R.styleable.HeaderBar_titleTextColor, 0); //标题字体颜色

        leftButtonText = ta.getString(R.styleable.HeaderBar_leftButtonText); //左按钮文字
        leftButtonTextSize = ta.getDimension(R.styleable.HeaderBar_leftButtonTextSize, 0); //左按钮字体大小
        leftButtonBackground = ta.getDrawable(R.styleable.HeaderBar_leftButtonBackground); //左按钮背景
        
        rightButtonText = ta.getString(R.styleable.HeaderBar_rightButtonText); //右按钮文字
        rightButtonTextSize = ta.getDimension(R.styleable.HeaderBar_rightButtonTextSize, 0); //右按钮字体大小
        rightButtonBackground = ta.getDrawable(R.styleable.HeaderBar_rightButtonBackground); //右按钮背景

        ta.recycle(); //释放样式数组
    }

    /**
     * 初始化界面控件
     * @param context 上下文
     */
    private void initViews(Context context) {
        tvTitle = new TextView(context); //标题文字控件
        leftButton = new Button(context); //左按钮
        rightButton = new Button(context); //右按钮
    }

    /**
     * 将样式设置到对应的控件
     */
    private void setStylesToViews() {
        tvTitle.setText(titleText); //标题文字
        tvTitle.setTextSize(titleTextSize); //标题文字大小
        tvTitle.setTextColor(titleTextColor); //标题文字颜色
        tvTitle.setGravity(Gravity.CENTER); //标题文字居中
        
        leftButton.setText(leftButtonText); //左按钮文字
        leftButton.setTextSize(leftButtonTextSize); //左按钮文字大小
        leftButton.setBackground(leftButtonBackground); //左按钮背景

        rightButton.setText(rightButtonText); //右按钮文字
        rightButton.setTextSize(rightButtonTextSize); //右按钮文字大小
        rightButton.setBackground(rightButtonBackground); //右按钮背景

        setBackgroundColor(0xFFF56535); //设置整体背景色
    }

    /**
     * 将控件添加到ViewGroup中
      */
    private void addViewsToViewGroup() {
        //设置布局参数
        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvTitle, titleParams); //将控件和参数添加到ViewGroup中

        leftButtonParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftButtonParams);

        rightButtonParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightButtonParams);
    }


    public interface HeaderBarClickListener { //监听器接口
        void leftClick(); //左按钮点击事件
        void rightClick(); //右按钮点击事件
    }

    private HeaderBarClickListener listener; //监听器

    public void setOnClickListener(HeaderBarClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置按钮的点击事件
     */
    private void setButtonClickEvent() {
        leftButton.setOnClickListener(new OnClickListener() { //给左按钮设置点击事件
            @Override
            public void onClick(View v) {
                listener.leftClick(); //调用接口的左按钮点击方法
            }
        });

        rightButton.setOnClickListener(new OnClickListener() { //给右按钮设置点击事件
            @Override
            public void onClick(View v) {
                listener.rightClick(); //调用接口的右按钮点击方法
            }
        });
    }

    /**
     * 设置右按钮的点击事件
     * @param isShow 是否显示右按钮
     */
    public void setRightButtonVisibility(boolean isShow) {
        rightButton.setVisibility(isShow ? VISIBLE : GONE);
    }
}
