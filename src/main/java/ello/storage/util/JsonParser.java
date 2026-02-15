package ello.storage.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Static utility class that encapsulates a configured Gson instance.
 * Provides type-safe methods for serializing and deserializing objects.
 */
public final class JsonParser {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
    }

    private JsonParser() {
    }

    /**
     * Serializes an object to a JSON string.
     *
     * @param <T>    The type of the object.
     * @param object The object to serialize.
     * @return A JSON string representation of the object.
     */
    public static <T> String toJson(T object) {
        return GSON.toJson(object);
    }

    /**
     * Deserializes a JSON string to a List of the specified element type.
     *
     * @param <T>          The type of list elements.
     * @param json         The JSON string to deserialize.
     * @param elementClass The class of list elements.
     * @return A List of the specified element type.
     */
    public static <T> List<T> listFromJson(String json, Class<T> elementClass) {
        Type listType = TypeToken.getParameterized(List.class, elementClass).getType();
        return GSON.fromJson(json, listType);
    }
}
