package com.yuantiku.yyl.webadapter;

import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * @author lirui
 * @date 15/4/29.
 */
public class MyHTMLConvertor implements Converter {



    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        return null;
    }

    @Override
    public TypedOutput toBody(Object object) {
        return null;
    }
}
