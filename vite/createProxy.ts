// 代理配置转换
type Proxy = {
  secure?: boolean;
  target: string;
  changeOrigin: boolean;
  rewrite: (path: string) => string;
}

export default function createProxy(list: [string, string][]) {
	const proxy: StringKeyObject<Proxy> = {}
	list.forEach((apiItem) => {
    const isHttps = apiItem[1].startsWith('https')
		const proxyObj = {
			target: apiItem[1],
			changeOrigin: true,
			rewrite: (path: string) => path.replace(new RegExp(`^${apiItem[0]}`), ''),
			...(isHttps ? { secure: false } : {}),
		}
    proxy[apiItem[0]] = proxyObj
	})
	return proxy
}
