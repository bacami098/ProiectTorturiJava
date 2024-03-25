package com.example.haiterog.repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingsComanda
{
    private static SettingsComanda instance;

    private String repoType;

    private final String repoFile;

    private SettingsComanda(String repoType, String repoFile)
    {
        this.repoFile = repoFile;
        this.repoType = repoType;
    }

    public String getRepoType()
    {
        return this.repoType;
    }

    public String getRepoFile()
    {
        return this.repoFile;
    }

    private static Properties loadSettings()
    {
        try (FileReader fr = new FileReader("D:\\MAP\\haiterog\\src\\main\\java\\com\\example\\haiterog\\repository\\settingsComanda.properties"))
        {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static synchronized SettingsComanda getInstance()
    {
        Properties properties = loadSettings();
        instance = new SettingsComanda(properties.getProperty("repo_type"), properties.getProperty("repo_file"));
        return instance;
    }
}