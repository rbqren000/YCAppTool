# 圆角控件库
#### 目录介绍
- 01.基础概念介绍
- 02.常见思路和做法
- 03.Api调用说明
- 04.遇到的坑分析
- 05.图片圆角性能



### 01.基础概念介绍
- 业务背景介绍
    - 在显示图片是有时候需要显示圆角图片，我们应该都知道圆角显示肯定是更加耗费内存和性能，会导致图片的过度绘制等问题。
- 给控件设置圆角，代码层面一般怎么做？
    - 第一种：比如给TextView设置Shape圆角，大概的原理：
    - 第二种：自定义控件实现，大概原理：要实圆角或者圆形的显示效果，就是对图片显示的内容区域进行“裁剪”，只显示指定的区域即可。
    - 第三种：使用Glide加载图片设置圆角，大概原理：
- clipPath切割圆角的核心原理
    - 第一步：图片是被绘制在画布上的，所以用 canvas 的 clipPath()方法先将画布裁剪成指定形状。由于clipPath()方法不支持抗锯齿，图片边缘会有明显的毛糙感，体验并不理想。
- setXfermode切割圆角的核心原理
    - 使用图像的 Alpha 合成模式。整个过程就是先绘制目标图像，也就是图片；再绘制原图像，即一个圆角矩形或者圆形，这样最终目标图像只显示和原图像重合的区域。如果是图片，需要通过src属性或者对应的方法来设置图片，否则不能达到预期效果。
    - 第一步：核心逻辑是在draw方法中进行绘制，首先调用canvas.saveLayer设置离屏缓存，新建一个控件区域大小的图层
    - 第二步：相当于使用super.onDraw绘制自己，这一步调用super方法即可
    - 第三步：设置画笔Paint属性，先调用setXfermode设置混合模式，然后在调用canvas.drawPath(path, paint)绘制path，最后再清除Xfermode
    - 第四步：添加边框，只需要绘制一个指定样式的圆角矩形或者圆形即可。比如绘制圆角，调用Path.addRoundRect添加path，然后调用canvas.drawPath绘制path


### 02.常见思路和做法
- 目前设置控件圆角有哪些方式
    - 第一种：比如给TextView设置Shape圆角，非常常见的使用
    - 第二种：使用背景图片
    - 第三种：自定义控件实现
    - 第四种：使用ViewOutlineProvider裁剪View
    - 第五种：使用CardView
    - 第六种：使用Glide加载图片设置圆角
    - 第七种：使用BitmapShader
- 各种设置圆角的优缺点对比
    - 第一种：shape常见，简单直观。缺点是项目中xml，越写越多
    - 第二种：使用切图没什么说的，使用起来不方便
    - 第三种：自定义控件，弥补shape上不足，采用attr属性设置圆角，那样圆角样式多，使用起来方便
    - 第四种：用于实现view阴影和轮廓
    - 第五种：使用CardView，官方支持阴影和圆角控件
    - 第六种：使用Glide加载圆角，一般用于图片设置，比较方便
    - 第七种：使用BitmapShader，占用内存较大，实现有点小复杂。



### 03.Api调用说明




### 04.遇到的坑分析
- 绘制的边框会覆盖在图片上，如果边框太宽会导致图片的可见区域变小。
    - 如何让边框不覆盖在图片上呢？可以在 Alpha 合成绘制前先将画布缩小一定比例，最后再绘制边框，这样问题就解决。



### 05.图片圆角性能
- 方案1：直接采用Canvas.clipPath 相关api，裁剪出一个圆角区域。
    - 该方案简单暴力，通用性强。如果只是一个静态的单图视图，该方法问题不大，但如果是复杂页面，滚动的时候，测试就会跟你说，页面卡顿了，要优化。原因就是 Canvas.clip的相关api损耗相对较大。
- 方案2：系统提供的CardView设置圆角
    - 把原来全工程各个视频控件和图片控件的外层，都加上一层CardView。改造成本大，布局层级更深一层，layout时间加长。
- 方案3：使用setXfermode法
    - 此种方式就是再new一个相同尺寸的bitmap，然后使用paint.setXfermode；先画圆角矩形，再画原始bitmap，然后就得到了一个圆角的bitmap。早期用得较多，占用bitmap双倍内存。
- 方案4：图片加载库比如Glide，Fresco等
    - 在底层，无非也是使用上面的这两种种方式。早期的使用setXfermode来实现，后来使用BitmapShader实现。使用简单，稳定。
- 方案5：遮罩
    - 还是使用setXfermode，不过与方法一不同的是：不对图片作任何更改，只在圆角之外再画一层与背景颜色相同的四个角来遮挡，在视觉上造成圆角图片的效果。




#### 6.2 参考博客
- 圆角图片
    - https://mp.weixin.qq.com/s/dExd6EUcvmkUCO-RZHQOWw









