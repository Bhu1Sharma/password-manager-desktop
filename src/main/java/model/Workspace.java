package model;

import java.net.URL;
import java.util.List;

public interface Workspace {
    URL path();
    List<Profile> profiles();
}
