package me.nedoprogrammer.murderMystery.commands;

import me.nedoprogrammer.murderMystery.MurderMystery;
import me.nedoprogrammer.murderMystery.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return false;
        var player = (Player) commandSender;
        if(strings.length != 1) {
            player.sendMessage(ChatColor.RED + "Неверное количество аргументов.\nНужно: 1 | Получено: %d".formatted(strings.length));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        var game = MurderMystery.creatingGames.stream()
                .filter(obj -> obj.name.equals(strings[0]))
                .findFirst()
                .orElse(null);
        if(game == null)
        {
            player.sendMessage(ChatColor.RED + "Игры с названием %s не существует.".formatted(strings[0]));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        var loc = player.getLocation();
        game.lobby = loc;
        player.sendMessage(ChatColor.GREEN + "Успешно задана точка лобби для игры %s в:\n%f, %f, %f!".formatted(strings[0], loc.getX(), loc.getY(), loc.getZ()));
        player.sendTitle(ChatColor.GREEN + "Успех", "", 10, 10, 10);
        return true;
    }
}