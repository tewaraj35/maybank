package com.maybank.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tewaraj
 */
public final class ObjectUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtil.class);

    private ObjectUtil() {
    }

    /**
     * Converts {@link Object} to {@code JSON}.
     *
     * @param object the {@link Object} to be converted to {@code JSON}
     * @return {@code JSON} in {@link String}
     */
    public static String toJson(final Object object) {
        String json = null;
        try {
            json = objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Converts {@link Object} to {@code JSON}.
     *
     * @param object the {@link Object} to be converted to {@code JSON}
     * @param module module to be registered
     * @return {@code JSON} in {@link String}
     */
    public static String toJson(final Object object, final Module module) {
        String json = null;
        try {
            json = objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Converts {@link Object} to {@code JSON}.
     *
     * @param object the {@link Object} to be converted to {@code JSON}
     * @param module module to be registered
     * @return a pretty printed {@code JSON} in {@link String}
     */
    public static String toPrettyJson(final Object object, final Module module) {
        String json = null;
        try {
            json = objectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Converts {@code JSON} to {@link Object}. This will throw an exception if the {@code JSON} is holding an invalid property.
     *
     * @param json  the {@code JSON} to be converted to an {@link Object}
     * @param clazz the {@link Class} to be used for {@code JSON} conversion
     * @param <T>   the {@link Class} or object to be generically transformed
     * @return the converted {@code JSON}
     * @throws IOException if the {@code JSON} could not be converted to the specified class
     */
    public static <T> T toObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = objectMapper();

        return mapper.readValue(json, clazz);
    }

    /**
     * Converts {@code Map} to {@link Object}. This will throw an exception if the {@code Map} is holding an invalid property.
     *
     * @param map   the {@code Map} to be converted to an {@link Object}
     * @param clazz the {@link Class} to be used for {@code Map} conversion
     * @param <T>   the {@link Class} or object to be generically transformed
     * @return the converted {@code JSON}
     * @throws IOException if the {@code Map} could not be converted to the specified class
     */
    public static <T> T toObject(Map<?,?> map, Class<T> clazz) throws IOException {
        ObjectMapper mapper = objectMapper();

        return mapper.convertValue(map, clazz);
    }

    /**
     * Converts {@code Map} to {@link Object}. This will throw an exception if the {@code Map} is holding an invalid property.
     *
     * @param object the {@code Object} to be converted to an {@link Object}
     * @param clazz  the {@link Class} to be used for {@code Map} conversion
     * @param <T>    the {@link Class} or object to be generically transformed
     * @return the converted {@code JSON}
     * @throws IOException if the {@code Map} could not be converted to the specified class
     */
    public static <T> T toObject(Object object, Class<T> clazz) throws IOException {
        return objectMapper().convertValue(object, clazz);
    }

    /**
     * Converts {@code JSON} to {@link Object}, ignoring unknown properties. Not recommended to use unless necessary.
     * <p>
     * This is useful when objects are being updated or transformed in the future, but the {@code JSON} is still holding old properties.
     * <p>
     * Some reasons that a {@code JSON} could be still be holding old properties are, e.g. Auditing, and Backups.
     *
     * @param json  the {@code JSON} to be converted to an {@link Object}
     * @param clazz the {@link Class} to be used for {@code JSON} conversion
     * @param <T>   the {@link Class} or object to be generically transformed
     * @return the converted {@code JSON}
     * @throws IOException if the {@code JSON} could not be converted to the specified class
     */
    public static <T> T toObjectIgnoreUnknownProperties(String json, Class<T> clazz) throws IOException {
        return objectMapper().readValue(json, clazz);
    }

    /**
     * Converts {@code JSON} objects to {@link Object}. This will throw an exception if the {@code JSON} is holding an invalid property.
     *
     * @param json  the {@code JSON} to be converted to an {@link Object}
     * @param clazz the {@link Class} to be used for {@code JSON} conversion
     * @param <T>   the {@link Class} or object to be generically transformed
     * @return the converted {@code JSON}
     * @throws IOException if the {@code JSON} collection could not be converted to the specified class
     */
    public static <T> List<T> toCollection(String json, Class<T> clazz) throws IOException {
        TypeFactory factory = TypeFactory.defaultInstance();

        return new ObjectMapper().readValue(json, factory.constructCollectionType(List.class, clazz));
    }

    /**
     * Converts {@code JSON} objects to {@link Object}. This will throw an exception if the {@code JSON} is holding an invalid property.
     *
     * @param object the {@code Object} to be converted to an {@link Object}
     * @param clazz the {@link Class} to be used for {@code JSON} conversion
     * @param <T>   the {@link Class} or object to be generically transformed
     * @return the converted {@code JSON}
     * @throws IOException if the {@code JSON} collection could not be converted to the specified class
     */
    public static <T> List<T> toCollection(Object object, Class<T> clazz) throws IOException {
        String json = objectMapper().writeValueAsString(object);

        return objectMapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
    }

    /**
     * Converts objects to {@link Map} where key holds the exact field name, and value holds the field value.
     *
     * @param object the object to be converted
     * @return the converted object to map.
     * @throws IOException              if the conversion fails.
     * @throws IllegalArgumentException if the conversion fails.
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(Object object) throws IOException, IllegalArgumentException {
        return objectMapper().convertValue(object, HashMap.class);
    }

    private static ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }

}
