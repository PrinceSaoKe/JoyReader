# 遇到的问题

* ~~基于ShapeableImageView封装头像控件闪退，如何用代码设置shapeAppearance？~~（已解决，init块中layoutParams可能还未被设置，不能使用）
* 拦截器判断token失效后如何跳转登录页面？拿不到context
* 在何时页面初始化？初始化后如何刷新数据？
* mmkv缓存数据更新了，要如何通知ViewModel更新数据？（如登录后改变用户名）