package controllers.serializers;

interface IDeserializer<T> {
    T deserialize (String input) throws Exception;
}