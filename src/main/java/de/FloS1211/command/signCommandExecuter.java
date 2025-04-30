package de.FloS1211.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.createPlayerProfile;
import static org.bukkit.Bukkit.getLogger;

public class signCommandExecuter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Kann nur von einem Spieler ausgeführt werden!");
            return true;
        }
        Player player = (Player) commandSender;
        if (args.length < 1) {
            player.sendMessage("Benutzung: /sign item <text>");
            return true;
        }
        if (args.length < 2 && !Objects.equals(args[0], "help")) {
            player.sendMessage("Benutzung: /sign item <text>");
            return true;
        }
        if (!Objects.equals(args[0], "help") && !Objects.equals(args[0], "item")) {
            player.sendMessage("Benutzung: /sign item <text>");
            return true;
        }
        if (args[0].equals("help")) {
            player.sendMessage("Color Codes:\n §4dark_red: §f&4\n §cred: §f&c\n §6gold: §f&6\n §eyellow: §f&e\n §2dark_green: §f&2\n §agreen: §f&a\n §baqua: §f&b\n §3dark_aqua: §f&3\n §1dark_blue: §f&1\n §9blue: §f&9\n §dlight_purple: §f&d\n §5dark_purple: §f&5\n white: &f\n §7gray: §f&7\n §8dark_gray: §f&8\n §0black: §f&0\n §rreset: §f&r\n §lfett: §r§f&l\n §okursiv: §r§f&o\n §nunterstrichen: §r§f&n\n §mdurchgestrichen: §r§f&m\n §kbugy§r§f: &k");
        } else if (args[0].equals("item")) {
            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                return true;
            }
            String string = "§f";
            for (int i = 0; i < args.length - 1; i++) {
                string += args[i+1];
                string += " ";
            }
            Pattern pattern = Pattern.compile("&([0-9a-frlomnk])");
            Matcher matcher = pattern.matcher(string);
            string = matcher.replaceAll("§$1");

            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = itemStack.getItemMeta();

            Date rawDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String date = sdf.format(rawDate);

            String playerName = player.getPlayerListName();

            List<String> itemLore = new ArrayList<>();
            if (itemMeta.hasLore()) {
                itemLore = itemMeta.getLore();
            }
            itemLore.add("");
            itemLore.add(string);
            itemLore.add("§7Signiert von§a " + playerName + "§7 am §a" + date + "§7. ");
            itemMeta.setLore(itemLore);
            itemStack.setItemMeta(itemMeta);
        }

        return true;
    }
}
