import type {
  ApplicationConfig,
  zhwAdminProAppConfigRaw,
} from '@zhw/types/global';

/**
 * 由 vite-inject-app-config 注入的全局配置
 */
export function useAppConfig(
  env: Record<string, any>,
  isProduction: boolean,
): ApplicationConfig {
  // 生产环境下，直接使用 window._zhw_ADMIN_PRO_APP_CONF_ 全局变量
  const config = isProduction
    ? window._zhw_ADMIN_PRO_APP_CONF_
    : (env as zhwAdminProAppConfigRaw);

  const { VITE_GLOB_API_URL } = config;

  return {
    apiURL: VITE_GLOB_API_URL,
  };
}
