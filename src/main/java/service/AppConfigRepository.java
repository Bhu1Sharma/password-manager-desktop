package service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exceptions.AppSetupException;
import model.AppConfig;
import utils.CachedSupplier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AppConfigRepository {

    private static final CachedSupplier<AppConfig> CACHED_SUPPLIER = new CachedSupplier<>(() -> {
        String strPath = ApplicationProperty.get("appconfig.path");
        if (strPath == null) {
            throw new IllegalStateException("appconfig.path is not configured in application.properties");
        }
        try {
            return new XmlMapper().readValue(new File(strPath), AppConfigImpl.class);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Cannot read Application Config XML File: %s", strPath));
        }
    });

    public static Optional<AppConfig> get() {
        return Optional.ofNullable(CACHED_SUPPLIER.get());
    }

    private static class AppConfigImpl implements AppConfig {

        private String workspaceUrl;

        public void setWorkspaceUrl(String workspaceUrl) {
            this.workspaceUrl = workspaceUrl;
        }

        @Override
        public Path getWorkspaceUrl() throws AppSetupException {
            return Paths.get(workspaceUrl);
        }

    }

}
