/* eslint-disable @typescript-eslint/no-unused-expressions */

const base64EncodeChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'

const base64encode = function (e: string) {
  let r
  let a
  let c
  let h
  let o
  let t
  for (c = e.length, a = 0, r = ''; a < c; ) {
    if (((h = 255 & e.charCodeAt(a++)), a == c)) {
      ;(r += base64EncodeChars.charAt(h >> 2)),
        (r += base64EncodeChars.charAt((3 & h) << 4)),
        (r += '==')
      break
    }
    if (((o = e.charCodeAt(a++)), a == c)) {
      ;(r += base64EncodeChars.charAt(h >> 2)),
        (r += base64EncodeChars.charAt(((3 & h) << 4) | ((240 & o) >> 4))),
        (r += base64EncodeChars.charAt((15 & o) << 2)),
        (r += '=')
      break
    }
    ;(t = e.charCodeAt(a++)),
      (r += base64EncodeChars.charAt(h >> 2)),
      (r += base64EncodeChars.charAt(((3 & h) << 4) | ((240 & o) >> 4))),
      (r += base64EncodeChars.charAt(((15 & o) << 2) | ((192 & t) >> 6))),
      (r += base64EncodeChars.charAt(63 & t))
  }
  return r
}

/**
 *
	16进制转base64
 */
export const hexToBase64 = function (str: string) {
  const strArr = str
    .replace(/\r|\n/g, '')
    .replace(/([\da-fA-F]{2}) ?/g, '0x$1 ')
    .replace(/ +$/, '')
    .split(' ')
  const numStr = strArr.map((item) => +item)
  return base64encode(String.fromCharCode.apply(null, numStr))
}

/**
 * base64转为16进制
 */
export function base64ToHex(base64: string): string {
  const raw = atob(base64)

  let HEX = ''

  for (let i = 0; i < raw.length; i++) {
    const _hex = raw.charCodeAt(i).toString(16)

    HEX += _hex.length === 2 ? _hex : `0${_hex}`
  }
  return HEX.toUpperCase()
}

/**
 * 随机生成16位字符串
 */
export function createNonceStr(): string {
  const chars = [
    '0',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'V',
    'W',
    'X',
    'Y',
    'Z',
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z',
  ]
  let numStr = ''

  for (let i = 0; i < 16; i++) {
    // 这里是几位就要在这里不改变

    const id = parseInt((Math.random() * 61).toString(), 10)
    numStr += chars[id]
  }
  return numStr
}

/**
 * 字符串转成16进制
 */
export function stringToHex(value: string): string {
  let str = ''

  for (let i = 0; i < value.length; i++) {
    if (value === '') str = value.charCodeAt(i).toString(16)
    else str += value.charCodeAt(i).toString(16)
  }
  return str
}

// 字符串转base64
export function strToBase64(preStr: string) {
  const str = encodeURI(preStr)
  const base64 = btoa(str)
  return base64
}

export function base64ToString(base64: string): string {
  return atob(base64)
}
