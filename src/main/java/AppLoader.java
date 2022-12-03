import service.AppConfigRepository;
import service.ApplicationProperty;

public class AppLoader {

    public static void main(String[] args) {
        System.out.println("application.version " + ApplicationProperty.get("application.version"));
        System.out.println(AppConfigRepository.get().get().getWorkspaceUrl());
    }
}
