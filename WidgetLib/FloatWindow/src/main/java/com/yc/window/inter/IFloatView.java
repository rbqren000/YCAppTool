package com.yc.window.inter;


import android.view.View;

import com.yc.window.FloatWindow;

/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2018/08/18
 *     desc  : 自定义悬浮窗接口
 *     revise:
 * </pre>
 */
public interface IFloatView {

    /**
     * 设置大小
     * @param width                 宽
     * @param height                高
     */
    FloatWindow setSize(int width, int height);

    /**
     * 设置显示的位置
     * @param gravity               显示
     * @param xOffset               x偏移
     * @param yOffset               y偏移
     */
    FloatWindow setGravity(int gravity, int xOffset, int yOffset);

    /**
     * 销毁
     */
    void dismiss();

}
