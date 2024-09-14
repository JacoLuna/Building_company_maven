package com.mycompany.app.classes.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mycompany.app.classes.People.Person;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonService <T extends Person>{
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .setDateFormat("dd-MM-YYYY")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }
    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            String jsonData = FileService.readFile(filePath);
            return GSON.fromJson(jsonData, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> readJsonList(String filePath, Class<T> clazz) {
        try {
            String jsonData = FileService.readFile(filePath);
            Type listType = TypeToken.getParameterized(List.class, clazz).getType();
            return GSON.fromJson(jsonData, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void writeJson(String filePath, T data) {
        try {
            String jsonData = GSON.toJson(data);
            FileService.writeFile(filePath, jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value != null) {
                out.value(value.format(formatter));
            } else {
                out.nullValue();
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in != null && in.peek() != null) {
                return LocalDate.parse(in.nextString(), formatter);
            }
            return null;
        }
    }
}
