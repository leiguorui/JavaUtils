package com.renrennet.utils.io.serialize.protostuff;

/**
 * @Auther: leiguorui
 * @Date: 2020/3/31 14:45
 * @Description:
 */
public class SerializeWrapper<T> {

    private T data;

    public static <T> SerializeWrapper<T> builder(T data) {
        SerializeWrapper<T> wrapper = new SerializeWrapper<>();
        wrapper.setData(data);
        return wrapper;
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }
}
