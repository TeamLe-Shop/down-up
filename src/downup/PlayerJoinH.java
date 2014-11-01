package downup;

import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinH {

	public static void handle(DownUp downUp, PlayerJoinEvent pje) {
		downUp.omclient.send(pje.getPlayer().getName() + " [" + pje.getPlayer().getAddress().getHostString() + "] has joined the server.");
	}
}
