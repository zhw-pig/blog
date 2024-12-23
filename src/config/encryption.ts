import EncryptionConstant from '@/constants/encryptionConstant'
import { Sm4PaddingEnum } from '@/enums/sm4PaddingEnum'
import type { HexString, SM4ModeBase } from 'sm-crypto'

export const getSM4Mode = (iv: number[] | HexString) => {
  const sm4ModeBase: SM4ModeBase = {
    iv,
    padding: Sm4PaddingEnum.PKCS5,
    mode: EncryptionConstant.SM4_MODE,
  }
  return sm4ModeBase
}
