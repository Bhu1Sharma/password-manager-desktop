package model;
import exceptions.AppSetupException;

import java.nio.file.Path;

public interface AppConfig {
    Path getWorkspaceUrl() throws AppSetupException;
}
