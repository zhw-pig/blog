import NProgress, { type NProgressOptions } from 'nprogress'
import 'nprogress/nprogress.css'

// Partial是一个内置的泛型工具类型，它可以将某个类型的所有属性变为可选的
// TODO: 可配置
const nProgressOptions: Partial<NProgressOptions> = {
  easing: 'ease', // 动画方式
  speed: 500, // 递增进度条的速度
  showSpinner: false, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3, // 初始化时的最小百分比
}

NProgress.configure(nProgressOptions)

export default NProgress
