// 这里的declare只能用在src下,所以需要在tsconfig.node.json中配置include
declare type Nullable<T> = T | null
declare type NonNullable<T> = T extends null | undefined ? never : T

// 定义key都是string的对象类型，值是对应的T
declare type StringKeyObject<T> = Record<string, T>
// 定义path-browserify库的类型
declare module 'path-browserify'

declare namespace Menu {
  interface MenuOptions {
    path: string
    name: string
    component?: string | (() => Promise<object>)
    redirect?: string
    meta: MetaProps
    children?: MenuOptions[]
  }
  interface MetaProps {
    icon?: string
    name?: string
    activeMenu?: string
    isLink?: string
    isHide?: boolean
    isFull?: boolean
    isAffix?: boolean
    isKeepAlive?: boolean
  }
}
