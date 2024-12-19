// vite加载插件的配置
import type { Plugin } from 'vite'
import vue from '@vitejs/plugin-vue'
import { webUpdateNotice } from '@plugin-web-update-notification/vite'
import vueDevTools from 'vite-plugin-vue-devtools'

export default function loadVitePlugins() {
	const vitePlugins: (Plugin | Plugin[])[] = [
		vue(),
    vueDevTools(),
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
