package link.servr.servrlink.core;

public class Core implements Runnable {

    private static Core core;

    public static Core getInstance() {
        return core;
    }

    public ServrLink servrLink;
    public ConfigManager configManager;

    public Core(ServrLink servrLink) {
        core = this;
        this.servrLink = servrLink;
    }

    @Override
    public void run() {
        configManager.setup();
    }
}
