import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import type { App } from 'vue'

const useElementPlusIcons = (app: App) => {
  Object.entries(ElementPlusIconsVue).forEach((itemIcon) => {
    const [key, component] = itemIcon
    app.component(key, component)
  })
}

export default useElementPlusIcons
