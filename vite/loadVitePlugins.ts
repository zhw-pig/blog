// vite加载插件的配置
import { webUpdateNotice } from '@plugin-web-update-notification/vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import Components from 'unplugin-vue-components/vite'
import type { Plugin } from 'vite'
import vueDevTools from 'vite-plugin-vue-devtools'

export default function loadVitePlugins() {
	const vitePlugins: (Plugin | Plugin[])[] = [
		vue(),
    vueDevTools(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
		webUpdateNotice({
			versionType: 'build_timestamp',
			notificationConfig: {
				placement: 'topRight',
				primaryColor: '#409EFF',
				secondaryColor: '#909399',
			},
		}),
	]
	return vitePlugins
}
