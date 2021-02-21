package com.zenya.aurora.util;

import com.zenya.aurora.scheduler.TrackTPSTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ChatUtils {
    public static String translateColor(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public static String parseMessage(String message) {
        message = translateColor(message);
        message = message.replaceAll("%tps%", Float.toString(TrackTPSTask.getInstance().getAverageTps()));
        return message;
    }

    public static void sendMessage(Player player, String message) {
        if(message == "") return;

        message = parseMessage(message);
        player.sendMessage(message);
    }

    public static void sendMessage(CommandSender sender, String message) {
        if(message == "") return;

        message = parseMessage(message);
        sender.sendMessage(message);
    }

    public static void sendBroadcast(String message) {
        if(message == "") return;

        message = parseMessage(message);

        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    public static void sendProtectedBroadcast(List<String> permissions, String message) {
        if(message == "") return;

        message = parseMessage(message);

        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            for(String permission : permissions) {
                if(player.hasPermission(permission)) {
                    player.sendMessage(message);
                    continue;
                }
            }
        }
    }

    public static void sendTitle(Player player, String title) {
        if(title == "") return;

        title = parseMessage(title);
        player.resetTitle();
        player.sendTitle(title, null, 10, 40, 20);
    }

    public static void sendSubtitle(Player player, String subtitle) {
        if(subtitle == "") return;

        subtitle = parseMessage(subtitle);
        player.resetTitle();
        player.sendTitle(null, subtitle, 0, 10, 0);
    }
}


