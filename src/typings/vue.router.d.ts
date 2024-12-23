import 'vue-router' // 必写
// 扩展路由meta信息  RouteRecordRaw 的类型继承了 _RouteRecordBase接口类型
declare module 'vue-router' {
  interface RouteRecordSingleView {
    title?: string
    // 路由动画
    transition?: string
    fullPath?: string
    redirect?: string
  }
}
