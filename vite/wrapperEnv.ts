// 转换全部环境变量属性
type RealName = number | string | boolean
const realNameMap:StringKeyObject<boolean> = {
	true: true,
	false: false,
}
export default function wrapperEnv(envConf: Record<string, string>) {
	const ret: StringKeyObject<RealName> = {}

	const keys = Object.keys(envConf)
  keys.forEach((envName) => {
    let realName: RealName = envConf[envName].replace(/\\n/g, '\n')
		realName = realNameMap[realName] || realName
		if (envName === 'VITE_PORT') {
			realName = Number(realName)
		}
		if (envName === 'VITE_PROXY' && realName && typeof realName === 'string') {
			try {
				realName = JSON.parse(realName.replace(/'/g, '"'))
			} catch (error) {
        throw new Error(`获取env错误：${error}`)
			}
		}
		ret[envName] = realName
		if (typeof realName === 'string') {
			process.env[envName] = realName
		} else if (typeof realName === 'object') {
			process.env[envName] = JSON.stringify(realName)
		}
  })

	return ret
}
