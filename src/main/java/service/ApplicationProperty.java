package service;

import utils.CachedSupplier;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperty {

    private static final CachedSupplier<Properties> propertiesSupplier;

    static {
        propertiesSupplier = new CachedSupplier<>(() -> {
            try {
                final Properties applicationProperties = new Properties();
                applicationProperties.load(ApplicationProperty.class.getClassLoader().getResourceAsStream("application.properties"));
                return applicationProperties;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    public static String get(String key) {
        if (key == null) {
            return null;
        }
        return propertiesSupplier.get().get(key).toString();
    }

}
