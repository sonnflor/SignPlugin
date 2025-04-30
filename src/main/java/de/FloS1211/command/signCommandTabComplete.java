package de.FloS1211.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class signCommandTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> vorschlaege = new ArrayList<>();
        if (args.length == 1) {
            vorschlaege.add("item");
            vorschlaege.add("help");
        }
        return vorschlaege;
    }
}
