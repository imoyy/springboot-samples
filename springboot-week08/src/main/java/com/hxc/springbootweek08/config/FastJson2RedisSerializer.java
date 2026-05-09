package com.hxc.springbootweek08.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Serialize Redis values with FastJSON2 and keep type metadata,
 * so domain objects do not need to implement java.io.Serializable.
 */
public class FastJson2RedisSerializer<T> implements RedisSerializer<T> {

    private final Class<T> type;

    public FastJson2RedisSerializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T value) throws SerializationException {
        if (value == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONBytes(value, JSONWriter.Feature.WriteClassName);
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize Redis value", ex);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return JSON.parseObject(bytes, type, JSONReader.Feature.SupportAutoType);
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize Redis value", ex);
        }
    }
}
