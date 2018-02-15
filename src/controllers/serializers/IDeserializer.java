package controllers.serializers;

interface IDeserializer<T> {
    T Deserialize (String input);
}