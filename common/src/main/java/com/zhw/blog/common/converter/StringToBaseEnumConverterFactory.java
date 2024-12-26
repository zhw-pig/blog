package com.zhw.blog.common.converter;

import com.zhw.blog.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

// get方式的反序列化
// 反序列化=》前端参数转后端实体中的枚举类型
// 这个反序列化工厂类，app和admin模块都有用到，
// 所以model.enums.BaseEnum要加到common模块中
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                for (T enumConstant : targetType.getEnumConstants()) {
                    if (enumConstant.getCode().equals(Integer.valueOf(source))) {
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("非法的枚举值:" + source);
            }
        };
    }
}