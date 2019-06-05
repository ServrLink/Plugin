package link.servr.servrlink.velocity;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import link.servr.servrlink.core.Core;
import link.servr.servrlink.core.ServrLink;
import link.servr.servrlink.velocity.commands.LinkCommand;
import lombok.Getter;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "velocityserverlink", name = "ServrLink", version = "1.0-SNAPSHOT",
        description = "I did it!", authors = {"TheCoolOne123", "TCOOfficiall", "ryan."})
public class VelocityServerLink implements ServrLink {

    private Core core;


    @Inject
    @Getter
    public ProxyServer proxy;

    @Inject
    @Getter
    private Logger logger;

    @Inject
    @DataDirectory
    private Path configPath;


    @Inject
    public VelocityServerLink(ProxyServer server, Logger logger, @DataDirectory Path configPath) {
        this.proxy = server;
        this.logger = logger;
        this.configPath = configPath;

        logger.info("Hello there, it's a test plugin I made!");
    }


    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        this.core = new Core(this);
        Toml toml = loadConfig(configPath);
        proxy.getCommandManager().register(new LinkCommand(core, toml), "link", "vlink");
    }


    @Override
    public File getDirectory() {
        return configPath.toFile();
    }

    @Override
    public InputStream getResource(String name) {
        return null;
    }

    @Override
    public void log(String message) {
        logger.info("[ServrLink] " + message);
    }

    @Override
    public void disable() {
        logger.info("[ServrLink] Turning off...");
    }
    @Override
    public void runAsync(Runnable runnable) {
        proxy.getScheduler().buildTask(this, runnable);
    }

    @Override
    public void runSync(Runnable runnable) {
        proxy.getScheduler().buildTask(this, runnable);
    }

    private Toml loadConfig(Path path) {
        File folder = path.toFile();
        File file = new File(folder, "config.toml");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try (InputStream input = getClass().getResourceAsStream("/" + file.getName())) {
                if (input != null) {
                    Files.copy(input, file.toPath());
                } else {
                    file.createNewFile();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return null;
            }
        }

        return new Toml().read(file);
    }
}
