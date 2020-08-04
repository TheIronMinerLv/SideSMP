package lv.TheIronMinerLv.SideSMP.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;
import org.bukkit.Location;

import lv.TheIronMinerLv.SideSMP.SideSMPplugin;
import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player p = event.getPlayer();
    String kopiena = "";
    Plugin plugin = SideSMPplugin.getInstance();

    for (Team team : p.getScoreboard().getTeams()) {
      if ((team.hasEntry(p.getName())) && (team.getName().contains("kopiena_"))) {
        kopiena = team.getPrefix();
        break;
      }
    }

    p.setPlayerListName(kopiena + p.getName());

    PermissionAttachment attachment = p.addAttachment(plugin);
    attachment.setPermission("bukkit.command.plugins", false);
    attachment.setPermission("bukkit.command.version", false);
    attachment.setPermission("bukkit.command.help", false);
    attachment.setPermission("bukkit.command.kill", false);
    attachment.setPermission("minecraft.command.me", false);
    attachment.setPermission("minecraft.command.msg", false);

    new BukkitRunnable() {
      int afkTimer = 0;
      Location savedLoc = p.getLocation();
      String listName = p.getPlayerListName();

      @Override
      public void run() {
        if (!p.isOnline()) {
          this.cancel();
        }

        if (p.getWorld() != savedLoc.getWorld()) {
          savedLoc = p.getLocation();
        }

        if (p.getLocation().distanceSquared(savedLoc) > 1) {
          afkTimer = 0;
          savedLoc = p.getLocation();
          p.setPlayerListName(listName);
        } else {
          afkTimer++;
          if (afkTimer == plugin.getConfig().getInt("AFKtimer")) {
            p.setPlayerListName(ChatColor.GRAY + p.getName());
          }
        }
      }
    }.runTaskTimerAsynchronously((Plugin)SideSMPplugin.getInstance(), 0, 20);
  }
}