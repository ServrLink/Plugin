package link.servr.servrlink.spigot;

import link.servr.servrlink.core.Core;
import link.servr.servrlink.core.ServrLink;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SpigotServrLink extends JavaPlugin implements ServrLink {

    private Core core;

    @Override
    public void onEnable() {
        core = new Core(this);
        core.run();
    }

    @Override
    public void runAsync(Runnable runnable) {
        getServer().getScheduler().runTaskAsynchronously(this, runnable);
    }

    @Override
    public void runSync(Runnable runnable) {
        getServer().getScheduler().runTask(this, runnable);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("link")) {
            BaseComponent[] message = new ComponentBuilder("» ")
                    .color(ChatColor.DARK_GRAY)
                    .append(core.configManager.getGeneric("link-command.message", "Click on this message to visit the ServrLink website for information on linking your account"))
                    .color(ChatColor.DARK_AQUA)
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here").color(ChatColor.GREEN).create()))
                    .event(new ClickEvent(ClickEvent.Action.OPEN_URL, core.configManager.getGeneric("link-command.url", "https://servr.link")))
                    .create();

            sender.spigot().sendMessage(message);
        }
        return true;
    }

    @Override
    public File getDirectory() {
        return getDataFolder();
    }

    @Override
    public void log(String message) {
        Bukkit.getLogger().info("[ServrLink] " + message);
    }

    @Override
    public void disable() {
        getServer().getPluginManager().disablePlugin(this);
    }
}
