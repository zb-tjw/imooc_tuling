# imooc_tuling
慕课网图灵机器人课程项目<hr/>
<img src="https://github.com/zb-tjw/imooc_tuling/blob/master/images/shotImg.png" width="480" height="600" alt="效果展示图"/>
<br/>
# 项目简介
1. 图灵API已更换版本，所以对教学视频中的请求和解析代码做了改动
   1. HttpUtils文件中舍弃Get请求方式，转为Post请求方式
   2. HttpUtils文件中保留了HTTPClient请求方式(需引入相应的包)，最终版本采用的是OKHTTP框架
   3. 对请求url做了修改，同时因为请求和返回数据内容已发生变动，舍弃Result Bean，不再采用gson映射到bean的解析方式，而是直接提取最终的内容文本
   4. 对对话消息文本背景做了更换，自制了新的.9图，课程提供的原背景素材.9图存在一行文本上下间距过大的问题，美观程度不行，但不影响实际功能
2. 视频中老师的Item布局存在小的缺陷，当发送大量文本数据时发送者的头像和名字将会被挤出屏幕外，对其布局进行了修改
## 尚未完成的部分
1. 发送消息的文本框，应设置最小高度与最大高度，不仅仅是写死高度只能显示一行文本
2. 键盘弹出时，listview布局没有相应变化，遮挡了对话内容，需手动滑动listView才能看到最新的对话内容，后期需要研究一下QQ微信是怎么实现聊天布局抬升的，
有懂的欢迎指教