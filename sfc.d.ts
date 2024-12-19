// 支持sfc项目类型支持

// 声明vue2的单文件组件
// declare module '*.vue' {
//   import Vue from 'vue'
//   export default Vue
// }

// 声明vue3的单文件组件
declare module '*.vue' {
  import type { DefineComponent, ComputedOptions } from 'vue'
  const component: DefineComponent<object, object, ComputedOptions>
  export default component
}
