package controllers.serializers;

public interface IMultilineDeserializer <T> {
    T[] deserializeMultiline (String input) throws Exception;
}
