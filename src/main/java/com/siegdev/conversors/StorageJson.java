package com.siegdev.conversors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

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
}
