import { fileURLToPath, URL } from 'node:url'
import { ConfigEnv, loadEnv, UserConfigExport } from 'vite'
import wrapperEnv from './vite/wrapperEnv'
import loadVitePlugins from './vite/loadVitePlugins'
import buildOutDir from './vite/buildOutDir'

export default ({ mode }: ConfigEnv): UserConfigExport => {
  const root = process.cwd()
  const env = loadEnv(mode, root)
  // 转换所有环境变量中的变量类型
  const viteEnv = wrapperEnv(env)
  const { VITE_PORT } = viteEnv // VITE_PROXY
  return {
    root,
    base: './',
    server: {
      host: true,
      port: VITE_PORT,
      // proxy: {
      //   '/api': {
      //     target: 'http://192.168.102.252:30100',
      //     changeOrigin: true,
      //     rewrite: (path) => path.replace(/^\/api/, ''),
      //   },
      // },
    },
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url)),
      },
    },
    //  插件配置
    plugins: loadVitePlugins(),
    build: {
      outDir: buildOutDir(mode),
      manifest: true,
      rollupOptions: {
        output: {
          manualChunks(id) {
            if (id.includes('element-plus')) return 'element-plus'
            if (id.includes('ca.config.ts')) return 'ca-config'
          },
          chunkFileNames: (chunkInfo) => {
            if (chunkInfo.name === 'app-config') {
              return '[name].js'
            }
            if (chunkInfo.name === 'ca-config') {
              return '[name].js'
            }
            return 'js/[name].[hash].js'
          },
        },
      },
      chunkSizeWarningLimit: 600,
    },
  }
}
