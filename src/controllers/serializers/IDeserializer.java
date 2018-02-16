package controllers.serializers;

import java.util.List;

interface IDeserializer<T> {
    List<T> deserialize (String input) throws Exception;
}