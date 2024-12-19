// 这里的declare只能用在src下,所以需要在tsconfig.node.json中配置include
declare type Nullable<T> = T | null
declare type NonNullable<T> = T extends null | undefined ? never : T

// 定义key都是string的对象类型，值是对应的T
declare type StringKeyObject<T> = Record<string, T>
