package lv.TheIronMinerLv.SideSMP.listeners;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

public class ChatListener implements Listener {
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    String format = " &8» &f%2$s";
    Player p = event.getPlayer();
    String kopiena = "";

    for (Team team : p.getScoreboard().getTeams()) {
      if ((team.hasEntry(p.getName())) && (team.getName().contains("kopiena_"))) {
        kopiena = team.getPrefix();
        break;
      }
    }

    event.setFormat(kopiena + p.getName() + ChatColor.translateAlternateColorCodes('&', format));
  }
}