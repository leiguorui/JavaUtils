package com.renrennet.utils.io.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * json中的int数值 转换为 boolan对象
 *
 * Created by Green Lei on 2015/8/21 10:34.
 */
public class BooleanAsIntAdapter extends TypeAdapter<Boolean>{

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value);
        }
    }

    @Override
    public Boolean read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {
            case BOOLEAN:
                return in.nextBoolean();
            case NULL:
                in.nextNull();
                return null;
            case NUMBER:
                return in.nextInt() != 0;
            case STRING:
                return Boolean.parseBoolean(in.nextString());
            default:
                throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
        }
    }


}
