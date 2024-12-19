import { environment } from '@/evn.config'
import NodeRsa from 'jsencrypt'
import { sm4, sm3 } from 'sm-crypto'
import { base64ToHex, createNonceStr, hexToBase64, stringToHex, strToBase64 } from './encryptTool'

// 公钥加密，私钥解密
export const publicKey: string = environment.VITE_RSA_PUBLIC_KEY
export const privateKey: string = environment.VITE_RSA_PRIVATE_KEY

// 后端私钥base64转hex获得
const sm3PrivateKey: string = environment.VITE_SM3_PRIVATE_KEY

/**
 * @param value 要加密的原始数据
 * key：是16进制字符串的密钥，后端转化后所得
 * @returns 返回的是十六进制的hexString
 */
export const createHashBySm3 = (value: string) => {
  const hexString = sm3(value, {
    key: sm3PrivateKey,
    mode: 'hmac',
  })
  return hexString
}

/**
 *
 * @param value 要加密的原始数据
 * @param key16 key必须是16进制字符串
 * @param iv16  iv必须是16进制字符串
 * @returns  返回的是十六进制的hexString
 */
export function encryptSm4(value: string, key16: string, iv16: string) {
  const mode: object = {
    iv: iv16,
    padding: 'pkcs#5',
    mode: 'cbc',
  }
  const strEN = sm4.encrypt(value, key16, mode)
  return strEN
}

/**
 * rsa使用公钥加密sm4所需要的key和iv
 */
export function encryptRsa(value: string): string {
  const nodeRsa = new NodeRsa({ default_key_size: '2048' })
  nodeRsa.setPublicKey(publicKey)
  // nodeRsa.importKey(pubKey, 'pkcs8-public')
  const strEN = nodeRsa.encrypt(value) as string
  return strEN
}

/**
 * sm4解密：将字符串用key（16进制）进行加密
 * @param {string} value 要解密的数据
 * @param {string} key_16 十六进制的key
 */
export function decryptSm4(value16: string, key16: string, iv16: string) {
  const modeParam: object = {
    iv: iv16,
    padding: 'pkcs#5', // "none" | "pkcs#5" | "pkcs#7"
    mode: 'cbc',
  }
  const str = sm4.decrypt(value16, key16, modeParam)
  return str
}
/**
 * node-rsa解密：使用公钥解密
 * @param {string} value 要解密的数据
 * @param {string} pubKey 公钥
 */
export function decryptRsa(value: string): string {
  const nodeRsa = new NodeRsa({ default_key_size: '2048' })
  // setKey
  nodeRsa.setPrivateKey(privateKey)
  const str = nodeRsa.decrypt(value) as string
  return str
}

/**
 * 公钥加密
 * @param {string} value 要加密的字符串
 * @param {string} publicKey 公钥
 * // {hash}{key}{iv}{value}
 */
export function encryption(value: string): string {
  // 使用私钥生成sm3处理的hash
  const hashHex = createHashBySm3(value)
  const base64Hash = hexToBase64(hashHex)
  // 使用R公钥RSA方式加密随机key和随机iv字符串
  const key = createNonceStr()
  const iv = createNonceStr()
  const base64Key = strToBase64(key)
  // key在加密时，需要先转base64，再使用rsa加密转换后的base64
  const rsaKey = encryptRsa(base64Key)
  // iv则是，使用rsa加密原始的随机字符串
  // rsa加密本身返回的就是base64
  const rsaIv = encryptRsa(iv)
  // 16为随机字符串转成16进制的key
  const key16 = stringToHex(key)
  const iv16 = stringToHex(iv)
  // sm4加密将原文加密，key和iv必须是16进制
  // 参数一：value: number[] | UTF8String
  // 参数二：key16: number[] | HexString
  // 参数三：iv16: number[] | HexString
  const encryptHexString = encryptSm4(value, key16, iv16)
  // sm4加密返回的也是HexString，需要转成base64
  const encryptBase64Value = hexToBase64(encryptHexString)
  // 拼接每个单独的{}里面的都是base64
  return `{${base64Hash}}{${rsaKey}}{${rsaIv}}{${encryptBase64Value}}`
}

/**
 * 私钥解密
 * @param {string} value 要加密的字符串
 * @param {string} publicKey 公钥
 * 1、rsa解密key和iv
 * 2、使用rsa解密出来的key和iv进行sm4解密密文数据
 */
export function decrypt(value: string): string {
  try {
    const arr = value.split('}{')
    // const base64Hash = arr[0].slice(1)  // 前端不需要做hash完整性校验
    const rsaKey = arr[1]
    const rsaIv = arr[2]
    // 从索引0开始提取字符串，直到倒数第一个字符（不包括倒数第一个字符）
    const encryptBase64Value = arr[3].slice(0, -1)
    // rsa解密出key和iv
    const base64Key = decryptRsa(rsaKey)
    // base64转字符串
    // const strKey = base64ToString(base64Key)
    const strIv = decryptRsa(rsaIv)

    // 16为随机字符串转成16进制的key
    const key16 = base64ToHex(base64Key)
    const iv16 = stringToHex(strIv)
    // sm4解密的是hexString，后端如果返回的是base64，需要先转hexString
    const valHex = base64ToHex(encryptBase64Value)
    const text = decryptSm4(valHex, key16, iv16)
    return text
  } catch (error) {
    console.log(error)
    return ''
  }
}
