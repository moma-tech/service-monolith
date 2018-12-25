package com.moma.zoffy.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.moma.zoffy.handler.exception.exceptions.ZoffyException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * JacksonHelper
 *
 * <p>Jackson Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 4:52 PM.
 */
public class JacksonHelper {

  private static ObjectMapper objectMapper;

  static {
    objectMapper = getObjectMapper(new ObjectMapper());
  }

  /**
   * @param objectMapper : Any Existed ObjectMapper
   * @return com.fasterxml.jackson.databind.ObjectMapper
   * @author Created by ivan on 5:16 PM 12/13/18.
   *     <p>Get Wapper
   */
  public static ObjectMapper getObjectMapper(ObjectMapper objectMapper) {
    if (Objects.isNull(objectMapper)) {
      objectMapper = new ObjectMapper();
    }
    return createObjectMapper(objectMapper);
  }

  /**
   * @param objectMapper : Any Existed ObjectMapper
   * @return com.fasterxml.jackson.databind.ObjectMapper
   * @author Created by ivan on 5:16 PM 12/13/18.
   *     <p>Set & Create ObjectMapper
   * @see <a href="https://github
   *     .com/FasterXML/jackson-databind/wiki/Deserialization-Features">Deserialization Features</a>
   * @see <a
   *     href="https://github.com/FasterXML/jackson-databind/wiki/Serialization-Features">Serialization
   *     features</a>
   * @see <a href="https://github.com/FasterXML/jackson-databind/wiki/Mapper-Features">Mapper
   *     Features</a>
   */
  static ObjectMapper createObjectMapper(ObjectMapper objectMapper) {
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    // Set Feature
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
    objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

    // Handle Java Time
    objectMapper.registerModule(new JavaTimeModule());

    // Handle JSR310 Time
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(
        LocalDateTime.class,
        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    simpleModule.addSerializer(
        LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    simpleModule.addSerializer(
        LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    // Handle long
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    // Handle XSS
    simpleModule.addSerializer(new XssStringJsonSerializer());
    objectMapper.registerModule(simpleModule);
    return objectMapper;
  }

  /**
   * @param jsonString : Income Json String
   * @return java.lang.Object
   * @author Created by ivan on 5:48 PM 12/13/18.
   *     <p>Parse Json String to Object
   */
  public static Object parse(String jsonString) {
    Object object = null;
    try {
      object = getObjectMapper().readValue(jsonString, Object.class);
    } catch (Exception ignore) {
    }
    return object;
  }

  /**
   * @param json : Income Json String
   * @param clazz : Expect Class
   * @return T
   * @author Created by ivan on 5:48 PM 12/13/18.
   *     <p>Parse Json String to specified Class
   */
  public static <T> T readValue(String json, Class<T> clazz) {
    T t = null;
    try {
      t = getObjectMapper().readValue(json, clazz);
    } catch (Exception ignore) {
    }
    return t;
  }

  /**
   * @param json : Income Json String
   * @param valueTypeRef : Expect Class with Generic Type
   * @return T
   * @author Created by ivan on 5:49 PM 12/13/18.
   *     <p>Parse Json String to Generic Class Type
   */
  public static <T> T readValue(String json, TypeReference valueTypeRef) {
    T t = null;
    try {
      t = getObjectMapper().readValue(json, valueTypeRef);
    } catch (Exception ignore) {
    }
    return t;
  }

  /**
   * @param object : Income Object
   * @return java.lang.String
   * @author Created by ivan on 5:52 PM 12/13/18.
   *     <p>Parse Object to Json String
   */
  public static String toJson(Object object) {
    if (Objects.nonNull(object) && CharSequence.class.isAssignableFrom(object.getClass())) {
      return object.toString();
    }
    try {
      return getObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new ZoffyException(e);
    }
  }

  /**
   * @return com.fasterxml.jackson.databind.ObjectMapper
   * @author Created by ivan on 5:36 PM 12/13/18.
   *     <p>//get ObjectMapper
   */
  public static ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  /**
   * JacksonHelper
   *
   * <p>Custom Serializer for Json XSS
   *
   * @author Created by ivan on 8:39 PM 12/13/18.
   * @version 1.0
   */
  static class XssStringJsonSerializer extends JsonSerializer<String> {
    @Override
    public Class<String> handledType() {
      return String.class;
    }

    @Override
    public void serialize(
        String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
      if (value != null) {
        // TODO 暂时不进行处理
        // String encodedValue = HtmlUtils.htmlEscape(value);
        // jsonGenerator.writeString(encodedValue);
        jsonGenerator.writeString(value);
      }
    }
  }
}
