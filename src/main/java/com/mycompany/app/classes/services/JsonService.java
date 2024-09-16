package com.mycompany.app.classes.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.projects.types.*;
import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.TypeOfSoil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JsonService<T extends Person> {
    private static final Gson GSON;
    public static final String folder = "json";

    static {
        GSON = new GsonBuilder()
                .setDateFormat("dd-MM-YYYY")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Structure.class, new StructureAdapter())
                .create();
    }

    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            String jsonData = FileService.readFile(folder + "\\" + filePath, true);
            return GSON.fromJson(jsonData, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> readJsonList(String filePath, Class<T> clazz) {
        try {
            String jsonData = FileService.readFile(folder + "\\" + filePath, true);
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
            FileService.writeFile(folder + "\\" + filePath, jsonData, true);
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

    static class StructureAdapter extends TypeAdapter<Structure> {
        private static final Gson GSON = new Gson();

        @Override
        public void write(JsonWriter out, Structure structure) throws IOException {
            out.beginObject();
            out.name("id").value(structure.getId());
            out.name("name").value(structure.getName().name);
            out.name("price").value(structure.getPrice());
            out.name("squareMeters").value(structure.getSquareMeters());

            switch (structure) {
                case ApartmentBuilding apartmentBuilding -> {
                    out.name("storeys").value(apartmentBuilding.getStoreys());
                    out.name("MRP").value(apartmentBuilding.isMRP());
                }
                case Garden garden -> {
                    out.name("typeOfSoil").value(garden.getTypeOfSoil().name());
                    out.name("squareMetersOfSoil").value(garden.getSquareMetersOfSoil());
                }
                case House house -> {
                    out.name("rooms").value(house.getRooms());
                    out.name("bathrooms").value(house.getBathrooms());
                }
                case Pool pool -> {
                    out.name("depth").value(pool.getDepth());
                    out.name("temperature").value(pool.getTemperature());
                }
                default -> {
                }
            }

            out.endObject();
        }

        @Override
        public Structure read(JsonReader in) throws IOException {
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> properties = GSON.fromJson(in, type);

            String projectsStr = (String) properties.get("name");
            TypeOfProject typeOfProject = TypeOfProject.valueOf(projectsStr);
            int id = ((Double)properties.get("id")).intValue();
            long squareMeters = ((Double) properties.get("squareMeters")).longValue();

            switch (typeOfProject) {
                case APARTMENT:
                    int storeys = ((Double) properties.get("storeys")).intValue();
                    boolean MRP = (boolean) properties.get("MRP");
                    return new ApartmentBuilding(id, squareMeters, storeys, MRP);
                case GARDEN:
                    TypeOfSoil typeOfSoil = TypeOfSoil.valueOf((String) properties.get("typeOfSoil"));
                    float squareMetersOfSoil = ((Double)properties.get("squareMetersOfSoil")).floatValue();
                    return new Garden(id, squareMeters, typeOfSoil, squareMetersOfSoil);
                case HOUSE:
                    int rooms = ((Double) properties.get("rooms")).intValue();
                    int bathrooms = ((Double) properties.get("bathrooms")).intValue();
                    return new House(id, squareMeters, rooms, bathrooms);
                case POOL:
                    float depth = ((Double) properties.get("depth")).floatValue();
                    float temperature = ((Double) properties.get("temperature")).floatValue();
                    return new Pool(id, squareMeters, depth, temperature);
                default:
                    throw new IllegalArgumentException("Unknown species: " + typeOfProject);
            }
        }
    }
}
