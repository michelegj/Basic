package me.etel.basic.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.etel.basic.Basic;
import org.bukkit.command.CommandSender;

@CommandAlias("basic")
public class BasicCommand extends BaseCommand {

    private static final Basic plugin = Basic.getInstance();

    @Default
    public boolean execute(CommandSender sender) {
        sender.sendMessage(
                Basic.translate(
                        "&7&m----------------------" +
                        "\n&fCurrently running &e&lBasic" +
                        "\n&fAuthors: &e" + String.join("&f, ", plugin.getDescription().getAuthors()) +
                        "\n&fVersion: &e" + plugin.getDescription().getVersion() +
                        "\n&7&m----------------------"
                )
        );
        return false;
    }



}
