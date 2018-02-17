package controllers.serializers;

import java.util.List;

interface IDeserializer<T> {
    T[] deserialize (String input) throws Exception;
}