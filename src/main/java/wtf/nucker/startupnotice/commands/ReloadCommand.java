package wtf.nucker.startupnotice.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wtf.nucker.startupnotice.StartUpNotice;

import java.util.Arrays;

import static wtf.nucker.startupnotice.StartUpNotice.msg;

@CommandAlias("startupnotice|sun|startun|supn|sunotice")
@Description("Information command for start up notice")
public class ReloadCommand extends BaseCommand {

    StartUpNotice pl = StartUpNotice.getPlugin(StartUpNotice.class);

    @Subcommand("reload|rl")
    @Description("Reloads configs")
    @CommandPermission("startupnotice.reload")
    public void onReload(CommandSender p) {
        pl.reloadConfig();
        p.sendMessage(msg("&aReloaded configs!"));
    }

    @Default
    @Description("Sends plugin information")
    public void sendInfo(CommandSender p) {
        p.sendMessage(new String[] {
                msg("&bStartUpNotice"),
                msg("&9Developed by &bNucker"),
                msg("&9Join discord here: &b&uhttp://nckr.link/discord"),
                msg("&9Check out github here: &b&uhttps://github.com/Nuckerr/StartUpNotice")
        });
    }

    @HelpCommand
    public void onHelp(CommandSender p) {
        p.sendMessage(msg("&cInvalid usage: /sun (reload)"));
    }
}
