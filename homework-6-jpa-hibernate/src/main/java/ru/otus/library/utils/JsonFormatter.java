package ru.otus.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonFormatter implements Formatter {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String format(Object object) {
        return gson.toJson(object);
    }
}
