package lv.TheIronMinerLv.SideSMP.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandSendListener implements Listener {
  @EventHandler
  public void onCommandSend(PlayerCommandSendEvent event) {
    if (!event.getPlayer().hasPermission("command.completions")) {
      event.getCommands().clear();
    }
  }
}