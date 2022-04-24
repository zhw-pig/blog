---
title: TypeScript基础
date: 2022-04-24 13:50
tags: [前端,TypeScript]
keywords: 前端,TypeScript基础
categories:
- TypeScript
image: https://img-blog.csdnimg.cn/210aa32942a2455d86a6ab4d4492d5c0.png
top: 1
---
# TypeScript基础语法总结
## ts原始类型
### 一、包括js所有的基础类型
1. string
2. boolean
3. number
4. undefined
5. null
6. symbol
### 二、函数返回类型
1. void
## ts非原始类型
1. object：**常用**,只检测数组或对象类型
2. Object：检测js基础数据类型，同时检测引用类型
3. {}：和Object一样
## 数组类型
    检测数组类型，有三种写法：
1. 第一种写法：
```ts
let arr:number[] = [1,2,3]  // 表示arr类型是一个数组，且数组的每一个元素都是number类型
```
2. 第二种写法：使用[泛型](https://www.tslang.cn/docs/handbook/generics.html)
```ts
let arr:Array<number> = [10,20,30];  // 表示arr类型是一个数组，且数组的每一个元素都是number类型
```
3. 第三种写法：使用[元组](https://www.tslang.cn/docs/handbook/basic-types.html)
```ts
let arr:[number | boolean] = [1,2,true,2,3,false]; // 表示arr这个数组类型中的元素类型，可以是number或者boolean
```
## 联合类型
> 联合类型即是使用 ‘|’ 符号，让一个变量，可以被多个类型检测     

```ts
let test:number | boolean  // 表示test这个变量可以是number，也可以是boolean类型
let obj : {a:1} | {b:2}  // 表示obj这个变量可以是{a:1}，也可以是{b:2}，也可以两个都有
```

## 交叉类型
> 交叉类型即是使用 ‘&’ 符号，让一个变量满足被 **&**连接的所有类型

## 交叉联合类型
> 交叉联合类型即是：| 和 & 的混合使用，只需要记住 **&优先|**
```ts
let obj : {name:string} & {age : number} & {age : 13}
obj = {name : '', age : 13}   // 这里的age属性只能是number且值为13
```
## any类型和unknown 类型的区别
> any 不再有TypeScript的类型校验；  
unknown 保持着类型校验

## TypeScript两种自定义类型
### 一、接口interface
> TypeScript的核心原则之一是对值所具有的结构进行类型检查。 它有时被称做“鸭式辨型法”或“结构性子类型化”。 在TypeScript里，接口的作用就是为这些类型命名和为你的代码或第三方代码定义契约。

上面这句定义摘自官网，比较抽象，其实接口就是
> 开发者自己使用**interface**关键字封装的一套类型校验规则，就是接口

#### 1、封装一套限制对象的类型规则
```ts
// 这是一个接口返回的数据类型校验
interface ObjIft {
    status : number;
    data? : {}[];  // 表示data这个数组中的每一个元素是对象，且data缺省
    message : string;
}
// 使用这个检验规则接口
let resObj:ObjIft = {
    status : 200,
    data : [
        {...}
    ],
    message : 'success'
}
```

#### 2、封装一套限制数组的类型规则
```ts
interface ArrIft {
    // [idx:number]  数组下标类型
    [idx:number]:number|string
}

let arr:ArrIft = [1,2,3,'4','5']
```

#### 3、封装一套限制函数的类型规则
```ts
interface FnIft {
    // (p:string) 函数参数及类型 : 函数返回值
    (p:string) : void;
}
let fn:FnIft = (p)=>{}
fn('')
```
#### 4、interface注意点
* 接口名首字母需要大写
* 接口里的属性可以设置只读规则，用**readonly**修饰
* 接口里的属性可以设置属性缺省，用**?**修饰
* 接口是可以同名的，也是可以通过**extends**继承其他接口的，**通过继承或者同名的接口，规则会合并**，使用时，需要遵循合并后的规则
* 实际开发中，可以设置全局接口。只需要：将全局接口放在**xxx.d.ts**文件中，无需导出该文件，即可在全局使用

### 二、别名type

```ts
type StrOrNum = string | number;
let str:StrOrNum;
str = '1'
str = 1

// 类型别名定义函数
// 参数及其类型 => 返回值类型
type FnType = (p:string) => void
```
### 接口和别名的区别
1. 都可以用来自定义类型
2. 类型别名支持联合和交叉类型定义
3. 别名不支持重复命名，接口可以

## TS函数中的参数写法
### 一、参数默认值
```ts
function fn(a:number = 3) {}
```
### 二、参数缺省
```ts
function fn(a?:number) {}
```
### 三、接收剩余参数
```ts
function fn(a:number,b:number,...arr:number[]) {
    console.log(arr)  // 输出 3,4,5
}
fn(1,2,3,4,5)
```

## TS中this指向问题
### 一、全局对象下的this
```ts
// ts中提供了Window接口，用来规范全局对象window的
// 这里通过接口可以重复命名，扩展了Window接口对window对象的规则
// 所以此时 window全局对象下 是有自定义属性：myName 的
interface Window {
    myName : string
}
// 在ts中，若要使用this，需要在函数形参的第一个位置，注明this指向对象的类型
// 这里this指向window对象，所以需要用Window接口规范
function Person(this:Window,name:string) {
    this.myName = name
}
window.Person = Person()  // 挂载
window.Person('少年歌行~无心')
```

### 二、普通对象下的this
```ts
// 自定义对象规范
type ObjType = {
    myName : string;
    Person : (name:string) => void
}
// 定义对象obj，并使用规则ObjType
let obj:ObjType = {
    myName : '',
    // this:ObjType|Other  this可以指向联合类型
    Person : (this:ObjType,name:string) =>{
        this.myName = name
    }
}
obj.Person('少年歌行~无心')
```

## 泛型
> 软件工程中，我们不仅要创建一致的定义良好的API，同时也要考虑可重用性。 组件不仅能够支持当前的数据类型，同时也能支持未来的数据类型，这在创建大型系统时为你提供了十分灵活的功能。
 在像C#和Java这样的语言中，可以使用泛型来创建可重用的组件，一个组件可以支持多种类型的数据。 这样用户就可以以自己的数据类型来使用组件。

官网对[泛型](https://www.tslang.cn/docs/handbook/generics.html)的介绍,太过晦涩。其实，简单讲，[泛型](https://www.tslang.cn/docs/handbook/generics.html)**即是类型的形参**
```ts
// T 是自定义的标识符，是形参，接受外部传来的类型
function fn<T>(n:T) {}

// <类型>  外部传的实参string类型
fn<string>('1')
```
### 泛型约束extends
```ts
type StrOrNum = string | number;
// <T extends StrOrNum> ： StrOrNum 规则约束T泛型
interface PersonItf<T extends StrOrNum> {
    name : T // name属性只能是StrOrNum规则
}
let zhw:PersonItf<string> = {
    name : 'zhw'
}
```
## 枚举enum
> 使用枚举我们可以定义一些带名字的常量。 使用枚举可以清晰地表达意图或创建一组有区别的用例。 TypeScript支持数字的和基于字符串的枚举。

需要注意的是：[枚举](https://www.tslang.cn/docs/handbook/enums.html)不是类型，而是用于列举数据的，让一些晦涩的数据，见名知意；

```ts
enum StatusCode {
    success = 200,
    paramsError = 400
}
let res:string | number = 200;
if(res === StatusCode.success) {
    console.log('请求成功')
} else if(res === StatusCode.paramsError) {
    console.log('失败，请求参数问题')
}

// 如果枚举中的属性没有值，则默认值为0，且递增1
enum test {
    value,  // test.value = 0
    value2, // test.value2 = 1
    ....
}
```

## 类class
> 来自官网：传统的JavaScript程序使用函数和基于原型的继承来创建可重用的组件，但对于熟悉使用面向对象方式的程序员来讲就有些棘手，因为他们用的是基于类的继承并且对象是由类构建出来的。 从ECMAScript 2015，也就是ECMAScript 6开始，JavaScript程序员将能够使用基于类的面向对象的方式。 使用TypeScript，我们允许开发者现在就使用这些特性，并且编译后的JavaScript可以在所有主流浏览器和平台上运行，而不需要等到下个JavaScript版本

**应该注意：** 
* 定义类的同时，相当于定义了同名称的接口;
* 类可以通过关键字implements实现一个接口，从而达到遵循这个接口的规则的作用

```ts
class Person {
    // 在类中，定义属性前，应该先声明这个属性的类型，也可以给这个属性设置默认值
    myName : string = '无心';
    constructor(name:string) {
        this.myName = name
    },
    getName() {
        return this.myName
    })
}
```

以上这个Person类，相当于下面这个接口
```ts
interface Person {
    myName : string,
    getName : () => string
}
```

## 扩展
### 一、typeof 
> typeof 是用来提取变量的类型
```ts
let str = '12';
type StrType = typeof str  // 此时自定义的别名StrType 就是提取str这个变量的类型
let test:StrType = 'xx'
```