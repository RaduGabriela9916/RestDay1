package com.endava.petclinic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {
    private static Properties properties = new Properties();

    static{
        InputStream is = EnvReader.class.getClassLoader().getResourceAsStream( "env.properties" );

        try {
            properties.load( is );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static String getBaseUri(){
        return properties.getProperty( "baseUri" );
    }

    public static String getBasePath(){
        return properties.getProperty( "basePath" );
    }

    public static Integer getPort(){
        return Integer.parseInt( properties.getProperty( "port" ) );
    }

    public static Integer getPortSecured(){
        return Integer.parseInt(properties.getProperty("portSecured"));
    }

    public static String getApiKey(){
        return properties.getProperty("ApiKey");
    }

    public static String getApiKeySecret(){
        return properties.getProperty("ApiKeysecret");
    }

    public static String getAccessToken(){
        return properties.getProperty("AccessToken");
    }

    public static String getAccessTokenSecret(){
        return properties.getProperty("AccessTokenSecret");
    }

    public static String getTwitterUri(){
        return properties.getProperty("twitterUri");
    }

    public static String getTwitterPath(){
        return properties.getProperty("twitterPath");
    }


}