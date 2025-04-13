package com.siegdev.conversors.configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.siegdev.conversors.utils.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageJson {

    private Conversors plugin;
    public StorageJson(Conversors plugin)
    {
        this.plugin = plugin;
    }

    public void saveJson(Object obj, String fileName) throws IOException {
        Gson gson = new Gson();
        File file = new File(plugin.getDataFolder().getAbsolutePath(), "recipes/" + fileName + ".json");

        file.getParentFile().mkdirs();
        try(Writer writer = new FileWriter(file,false)){
            gson.toJson(obj,writer);
        }
    }

    public JsonObject getJson(String fileName) throws IOException {
        Gson gson = new Gson();
        File file = new File(plugin.getDataFolder(),"recipes/" + fileName + ".json");

        if(!file.exists())
            throw new FileNotFoundException("Arquivo não encontrado: " + file.getPath());

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, JsonObject.class);
        }
    }

    public List<Pair<String, Pair<String,String>>> readAllRecipeJsons() {
        List<Pair<String, Pair<String,String>>> recipes = new ArrayList<>();

        File folder = new File(plugin.getDataFolder(), "recipes");

        if (!folder.exists() || !folder.isDirectory()) {
            return recipes;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null) return recipes;

        for (File file : files) {
            try (FileReader reader = new FileReader(file)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                if (json.has("input") && json.has("output")) {
                    String input = json.get("input").getAsString();
                    String output = json.get("output").getAsString();
                    var inputAndOutput = new Pair<String,String>(input,output);
                    String fileName = file.getName().replace(".json", "");
                    recipes.add(new Pair<>(fileName, inputAndOutput));
                }
            } catch (Exception e) {
                plugin.getLogger().warning("Erro ao ler " + file.getName() + ": " + e.getMessage());
            }
        }

        return recipes;
    }

    public boolean IsIdValid(String fileName) {
        File file = new File(plugin.getDataFolder(), "recipes/" + fileName + ".json");
        return !file.exists();
    }
}
