package config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class ConfigReader {

    private final JsonObject jsonObject;

    public ConfigReader(String path) {
        try (FileReader reader = new FileReader(path)) {
            jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonElement getValueByKey(String key) {
        return jsonObject.get(key);
    }
}
