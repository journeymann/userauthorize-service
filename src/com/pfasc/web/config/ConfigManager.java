package com.pfasc.web.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @version 1.1 $
 * @author cg48910 $
 *
 */
public class ConfigManager {

    private static ConfigManager instance = null;
    private static Properties properties = null;
    	
    private ConfigManager() {
        properties = new Properties();        
    }
    
    /**
     * Singleton pattern for retrieving properties
     * @return
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
            instance.loadProperties();
        }
        return instance;
    }
    
    
    /**
     * Reload the properties file.
     *
     */
    
    private void refresh() {
        if (instance != null) {
            instance.loadProperties();
        }
    }
    
    /**
     * Load the properties file.
     */
    public void loadProperties() {
        String domain = this.getDomain();
        String pathToFile = "/tech/appl/hlpp/codebase/" + 
					domain.trim() + "/data/webservices.properties";
        try {
            properties.load(new FileInputStream(pathToFile) );
            properties.setProperty("pathToFile",pathToFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public String getDomain() {
        return System.getProperty("system.Domain");        
    }

    public Properties getProperties() {
    	this.refresh();
        return properties;        
    }
    
    
    
}