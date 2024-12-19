import { resolve } from 'path'

const pathResolve = (dir: string): string => resolve(__dirname, dir)
const alias: Record<string, string> = {
	'@': pathResolve('../src'),
	'/assets': pathResolve('../src/assets'),
}

export default alias
