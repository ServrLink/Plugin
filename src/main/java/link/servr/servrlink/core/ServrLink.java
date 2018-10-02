package link.servr.servrlink.core;

import java.io.File;
import java.io.InputStream;

public interface ServrLink {

    File getDirectory();

    InputStream getResource(String name);

    void log(String message);

    void disable();

    void runAsync(Runnable runnable);

    void runSync(Runnable runnable);
}
