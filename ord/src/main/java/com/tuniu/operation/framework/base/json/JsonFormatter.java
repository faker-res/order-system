package com.tuniu.operation.framework.base.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;

/**
 * @author fangzhongwei
 * 
 */
public class JsonFormatter {

    private static final ThreadLocal<ObjectMapper> MAPPER_NULL_LOCAL = new ThreadLocal<ObjectMapper>();

    private static final ThreadLocal<ObjectMapper> MAPPER_NOT_NULL_LOCAL = new ThreadLocal<ObjectMapper>();

    private static ObjectMapper get(boolean serializeNull) {
        ThreadLocal<ObjectMapper> tl = serializeNull ? MAPPER_NULL_LOCAL : MAPPER_NOT_NULL_LOCAL;
        if (null == tl.get()) {
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.disable(new DeserializationConfig.Feature[] { DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES });
            objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            if (!serializeNull) {
                objMapper.setSerializationInclusion(Inclusion.NON_NULL);
                objMapper.disable(new SerializationConfig.Feature[] { SerializationConfig.Feature.WRITE_NULL_MAP_VALUES });
            }
            tl.set(objMapper);
        }
        return tl.get();
    }

    public static void setDateFormat(DateFormat dateFormat) {
        get(true).setDateFormat(dateFormat);
        get(false).setDateFormat(dateFormat);
    }

    // 以下是带默认参数(允许为空)的反序列化方法
    public static <T> T toObject(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return toObject(content, type, true);
    }

    public static <T> T toObject(String content, Class<?> collectionClass, Class<?>... elementClasses)
            throws JsonParseException, JsonMappingException, IOException {
        return get(true).readValue(content, getCollectionType(get(true), collectionClass, elementClasses));
    }

    private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T toObject(File src, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return toObject(src, type, true);
    }

    public static <T> T toObject(InputStream in, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return toObject(in, type, true);
    }

    public static <T> T toObject(Reader reader, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return toObject(reader, type, true);
    }

    public static <T> T toObject(URL url, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return toObject(url, type, true);
    }

    public static <T> T toObject(byte[] src, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return toObject(src, type, true);
    }

    // 以下是带参数(是否允许为空)的反序列化方法
    public static <T> T toObject(String content, Class<T> type, boolean serializeNull) throws JsonParseException,
            JsonMappingException, IOException {
        return get(serializeNull).readValue(content, type);
    }

    public static <T> T toObject(File src, Class<T> type, boolean serializeNull) throws JsonParseException,
            JsonMappingException, IOException {
        return get(serializeNull).readValue(src, type);
    }

    public static <T> T toObject(InputStream in, Class<T> type, boolean serializeNull) throws JsonParseException,
            JsonMappingException, IOException {
        return get(serializeNull).readValue(in, type);
    }

    public static <T> T toObject(Reader reader, Class<T> type, boolean serializeNull) throws JsonParseException,
            JsonMappingException, IOException {
        return get(serializeNull).readValue(reader, type);
    }

    public static <T> T toObject(URL url, Class<T> type, boolean serializeNull) throws JsonParseException,
            JsonMappingException, IOException {
        return get(serializeNull).readValue(url, type);
    }

    public static <T> T toObject(byte[] src, Class<T> type, boolean serializeNull) throws JsonParseException,
            JsonMappingException, IOException {
        return get(serializeNull).readValue(src, type);
    }

    // 以下是带默认参数(允许为空)的序列化方法
    public static String toJsonAsString(Object value) throws JsonGenerationException, JsonMappingException, IOException {
        return toJsonAsString(value, true);
    }

    public static byte[] toJsonAsByte(Object value) throws JsonGenerationException, JsonMappingException, IOException {
        return toJsonAsByte(value, true);
    }

    public static void toJsonToFile(File resultFile, Object value) throws JsonGenerationException, JsonMappingException,
            IOException {
        toJsonToFile(resultFile, value, true);
    }

    public static void toJsonToOutputSteam(OutputStream out, Object value) throws JsonGenerationException,
            JsonMappingException, IOException {
        toJsonToOutputSteam(out, value, true);
    }

    public static void toJsonToWriter(Writer writer, Object value) throws JsonGenerationException, JsonMappingException,
            IOException {
        toJsonToWriter(writer, value, true);
    }

    // 以下是带参数(是否允许为空)的序列化方法
    public static String toJsonAsString(Object value, boolean serializeNull) throws JsonGenerationException,
            JsonMappingException, IOException {
        return get(serializeNull).writeValueAsString(value);
    }

    public static byte[] toJsonAsByte(Object value, boolean serializeNull) throws JsonGenerationException,
            JsonMappingException, IOException {
        return get(serializeNull).writeValueAsBytes(value);
    }

    public static void toJsonToFile(File resultFile, Object value, boolean serializeNull) throws JsonGenerationException,
            JsonMappingException, IOException {
        get(serializeNull).writeValue(resultFile, value);
    }

    public static void toJsonToOutputSteam(OutputStream out, Object value, boolean serializeNull)
            throws JsonGenerationException, JsonMappingException, IOException {
        get(serializeNull).writeValue(out, value);
    }

    public static void toJsonToWriter(Writer writer, Object value, boolean serializeNull) throws JsonGenerationException,
            JsonMappingException, IOException {
        get(serializeNull).writeValue(writer, value);
    }

}
