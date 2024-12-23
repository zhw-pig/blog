import { environment } from '@/evn.config'

class EncryptionConstant {
  /**
   * 后端私钥base64转hex获得
   */
  public static readonly SM3_PRIVATE_KEY: string = environment.VITE_SM3_PRIVATE_KEY
  /**
   * rsa公钥base64,后端提供, 公钥加密
   */
  public static readonly PUBLIC_KEY: string = environment.VITE_RSA_PUBLIC_KEY
  /**
   * rsa私钥base64,后端提供， 私钥解密
   */
  public static readonly PRIVATE_KEY: string = environment.VITE_RSA_PRIVATE_KEY

  /**
   * sm3加密mode
   */
  public static readonly SM3_MODE = 'hmac'
  /**
   * sm4 mode
   */
  public static readonly SM4_MODE = 'cbc'

  /**
   * 系统密文解密分隔符，和后端约定
   */
  public static readonly SPLIT_SIGN = '}{'
}

export default EncryptionConstant
