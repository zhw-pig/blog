export default (mode: string) => {
	switch (mode) {
		case 'dev':
			return 'dist-dev'
		default:
			return 'dist'
	}
}
