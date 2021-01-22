package com.javarestassuredtemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalParameters {
    public static String ENVIROMENT;
    public static String URL_DEFAULT;
    public static String REPORT_NAME;
    public static String REPORT_PATH;
    public static String DB_URL;
    public static String DB_NAME;
    public static String DB_USER;
    public static String DB_PASSWORD;
    public static String URL_TOKEN;
    public static String TOKEN;
    public static String AUTHENTICATOR_USER;
    public static String AUTHENTICATOR_PASSWORD;

    private Properties properties;

    public GlobalParameters(){
        properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("globalParameters.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        REPORT_NAME = properties.getProperty("report.name");
        REPORT_PATH = properties.getProperty("report.path");
        ENVIROMENT = properties.getProperty("enviroment");

        if(ENVIROMENT.equals("hml")){
            DB_URL = properties.getProperty("hml.db.url");
            DB_NAME = properties.getProperty("hml.db.name");
            DB_USER = properties.getProperty("hml.db.user");
            DB_PASSWORD = properties.getProperty("hml.db.password");
            URL_DEFAULT = properties.getProperty("hml.url.default");
            URL_TOKEN = properties.getProperty("hml.url.token");
            TOKEN = properties.getProperty("hml.token");
            AUTHENTICATOR_USER = properties.getProperty("hml.authenticator.user");
            AUTHENTICATOR_PASSWORD = properties.getProperty("hml.authenticator.password");
        }

        if(ENVIROMENT.equals("dev")){
            DB_URL = properties.getProperty("dev.db.url");
            DB_NAME = properties.getProperty("dev.db.name");
            DB_USER = properties.getProperty("dev.db.user");
            DB_PASSWORD = properties.getProperty("dev.db.password");
            URL_DEFAULT = properties.getProperty("dev.url.default");
            URL_TOKEN = properties.getProperty("dev.url.token");
            TOKEN = properties.getProperty("dev.token");
            AUTHENTICATOR_USER = properties.getProperty("dev.authenticator.user");
            AUTHENTICATOR_PASSWORD = properties.getProperty("dev.authenticator.password");
        }
    }

    public static void setToken(String token){
        TOKEN = token;
    }
}
