package com.example.haiterog.domain;

public interface EntityConverter<T extends Entity>
{
    String toString(T o);

    T fromString(String line);
}
