package me.limeglass.skungee.spigot.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.skungee.common.packets.ServerPacket;
import me.limeglass.skungee.common.packets.ServerPacketType;
import me.limeglass.skungee.common.player.PacketPlayer;
import me.limeglass.skungee.spigot.lang.SkungeeCondition;
import me.limeglass.skungee.spigot.utils.Utils;
import me.limeglass.skungee.spigot.utils.annotations.Patterns;

@Name("Bungeecord player colours")
@Description("Check if a player has chat colors enabled or disabled.")
@Patterns("[bungee[[ ]cord]] [(player|uuid)] %string/player% (1�(has|do[es])|2�(has|do[es])(n't| not)) (have|got) [the] bungee[[ ]cord] permission[s] %strings%")
public class CondPlayerHasPermission extends SkungeeCondition {

	public boolean check(Event event) {
		if (areNull(event))
			return false;
		PacketPlayer[] players = Utils.toSkungeePlayers(expressions.get(0).getSingle(event));
		ServerPacket packet = new ServerPacket(true, ServerPacketType.PLAYERPERMISSIONS, expressions.get(1).getAll(event), players);
		return sockets.send(packet, boolean.class) ? isNegated() : !isNegated();
	}

}
