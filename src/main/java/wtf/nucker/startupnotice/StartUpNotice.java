package wtf.nucker.startupnotice;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.nucker.startupnotice.commands.ReloadCommand;

public final class StartUpNotice extends JavaPlugin {

    public WebhookClient client;
    public PaperCommandManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        WebhookClientBuilder builder = new WebhookClientBuilder(getConfig().getString("webhook-url"));
        client = builder.build();

        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(0x14cc0e)
                .setDescription(getConfig().getString("messages.startup").replace("{servername}", getConfig().getString("servername")))
                .build();
        client.send(embed);
        manager = new PaperCommandManager(this);
        manager.registerCommand(new ReloadCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(0xcc170e)
                .setDescription(getConfig().getString("messages.shutdown").replace("{servername}", getConfig().getString("servername")))
                .build();
        client.send(embed);
    }

    // Util methods
    public static String msg(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static void log(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(msg(message));
    }
}
