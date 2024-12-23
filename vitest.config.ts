import { fileURLToPath } from 'node:url'
import { configDefaults, defineConfig, mergeConfig, ViteUserConfig } from 'vitest/config'
import viteConfig from './vite.config'

export default mergeConfig<object, ViteUserConfig>(
  viteConfig,
  defineConfig({
    test: {
      environment: 'jsdom',
      exclude: [...configDefaults.exclude, 'e2e/**'],
      root: fileURLToPath(new URL('./', import.meta.url)),
    },
  }),
)
