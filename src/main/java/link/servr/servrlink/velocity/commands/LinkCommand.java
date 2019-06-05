package link.servr.servrlink.velocity.commands;

import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import link.servr.servrlink.core.Core;
import net.kyori.text.Component;
import net.kyori.text.ComponentBuilder;
import net.kyori.text.TextComponent;
import net.kyori.text.event.ClickEvent;
import net.kyori.text.event.HoverEvent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

public class LinkCommand implements Command {

    private Core core;
    private Toml toml;

    public LinkCommand(Core core, Toml toml){
        this.core = core;
        this.toml = toml;
    }


    @Override
    public void execute(CommandSource source, @NonNull String[] args) {
        TextComponent.Builder serverListBuilder = TextComponent.builder("");

        TextComponent infoComponent = TextComponent.of(toml.getString("link-command.message", "Click on this message to visit the ServrLink website for information on linking your account"));


        infoComponent = infoComponent.color(TextColor.GRAY)
                .clickEvent(ClickEvent.openUrl(toml.getString("link-command.url", "https://servr.link")))
                .hoverEvent(HoverEvent.showText(
                        TextComponent.of("Click here")));

        serverListBuilder.append(infoComponent);




        source.sendMessage(serverListBuilder.build());

    }
}
