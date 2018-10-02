package link.servr.servrlink.core;

import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigManager {

    private ServrLink servrLink = Core.getInstance().servrLink;

    private String name;

    private File directory;
    private File cfile;

    private YamlConfiguration config;

    public ConfigManager(String name, File directory) {
        this.name = name;
        this.directory = directory;
    }

    public void setup() {
        try {
            directory.mkdir();
            this.cfile = new File(directory, name);

            if(!cfile.exists()) {
                cfile.createNewFile();

                InputStream is = servrLink.getResource(name);
                OutputStream os = new FileOutputStream(cfile);

                ByteStreams.copy(is, os);

                is.close();
                os.close();
            }

            this.config = YamlConfiguration.loadConfiguration(cfile);
        } catch(IOException ex) {
            servrLink.log("An error occurred while create the config, disabling");
            ex.printStackTrace();
            servrLink.disable();
        }
    }

    public<T> T getGenericOrNull(String key) {
        try {
            return (T) config.get(key);
        } catch(Exception ex) {
            return null;
        }
    }

    public<T> T getGeneric(String key, T fallback) {
        T value = getGenericOrNull(key);

        if(value == null)
            return fallback;
        else
            return value;
    }
}
