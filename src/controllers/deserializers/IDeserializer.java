package controllers.deserializers;

interface IDeserializer<T> {
    T Deserialize (String input);
}