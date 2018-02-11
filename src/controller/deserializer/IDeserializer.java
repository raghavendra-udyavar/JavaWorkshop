package controller.deserializer;

interface IDeserializer<T> {
    T Deserialize (String input);
}