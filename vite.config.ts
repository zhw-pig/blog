import { fileURLToPath, URL } from 'node:url'
import { ConfigEnv, loadEnv } from 'vite'
import buildOutDir from './vite/buildOutDir'
import loadVitePlugins from './vite/loadVitePlugins'
import wrapperEnv from './vite/wrapperEnv'

export default ({ mode }: ConfigEnv) => {
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
      port: +VITE_PORT,
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
    css: {
      preprocessorOptions: {
        scss: {
          javascriptEnabled: true,
          // vite下：可以不需要需要借助sass-loader
          // 如果是vue-cli构建的项目：这里需要写成@路径别名，而不能用./src
          // 这里只能用@import，不能用@use，但是@import快过期了， 如果用@use不会生效
          // @use 会创建模块作用域，所以不能在全局使用@use导入scss文件
          // @import 不会创建模块作用域
          // TODO:  @use 和 @import的兼容处理
          additionalData: '@import "@/styles/variable.scss";',
        },
      },
    },
    build: {
      outDir: buildOutDir(mode),
      manifest: true,
      rollupOptions: {
        output: {
          manualChunks(id: string) {
            if (id.includes('element-plus')) return 'element-plus'
            if (id.includes('ca.config.ts')) return 'ca-config'
          },
          chunkFileNames: (chunkInfo: StringKeyObject<string>) => {
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
