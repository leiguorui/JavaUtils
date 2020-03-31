package com.renrennet.utils.io.serialize.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * java序列化工具
 * string序列化后的字节数，要大于VO类
 *
 * @Auther: leiguorui
 * @Date: 2020/3/31 14:35
 * @Description:
 */
public class SerializeUtilsProtostuff {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtilsProtostuff.class);

    private static final Map<Class<?>, Schema<?>> schemaMap = new ConcurrentHashMap<>();

    private static final Schema<SerializeWrapper> wrapperSchema = RuntimeSchema.createFrom(SerializeWrapper.class);


    private static <T> Schema<T> getSchema(Class<T> clazz) {
        if (!schemaMap.containsKey(clazz)) {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            if (Objects.nonNull(schema)) {
                schemaMap.put(clazz, schema);
            }
        }
        return (Schema<T>) schemaMap.get(clazz);
    }

    /**
     * Java对象序列化方法
     * @param object 需要序列化的对象
     * @param <T> 泛型
     * @return 序列化后的结果
     */
    public static <T> byte[] serialize(T object) {
        if (null == object) {
            return null;
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Class<T> clazz = (Class<T>) object.getClass();
            Schema schema = wrapperSchema;
            Object serializeObject = object;
            if (Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
                serializeObject = SerializeWrapper.builder(object);
            } else {
                schema = getSchema(clazz);
            }
            return ProtostuffIOUtil.toByteArray(serializeObject, schema, buffer);
        } catch (Exception e) {
            logger.error("serialize [{}] exception: {}", object, e);
            throw new IllegalStateException(e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * Java对象反序列化方法
     * @param data 需要反序列化的数据
     * @param clazz 反序列化后的类型
     * @param <T> 泛型
     * @return 反序列化后的结果
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        if (null == data) {
            return null;
        }
        try {
            if (Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
                SerializeWrapper<T> wrapper = new SerializeWrapper<>();
                ProtostuffIOUtil.mergeFrom(data, wrapper, wrapperSchema);
                return wrapper.getData();
            } else {
                Schema<T> schema = getSchema(clazz);
                T object = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(data, object, schema);
                return object;
            }
        } catch (Exception e) {
            logger.error("deserialize [{}] exception: {}", clazz.getName(), e);
            throw new IllegalStateException(e);
        }
    }
}