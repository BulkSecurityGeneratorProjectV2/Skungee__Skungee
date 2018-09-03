package me.limeglass.skungee.spigot.elements.expressions.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.ExpressionType;
import me.limeglass.skungee.objects.events.SkungeePlayerDisconnect;
import me.limeglass.skungee.objects.events.SkungeePlayerSwitchServer;
import me.limeglass.skungee.spigot.lang.SkungeeExpression;
import me.limeglass.skungee.spigot.utils.annotations.DetermineSingle;
import me.limeglass.skungee.spigot.utils.annotations.Events;
import me.limeglass.skungee.spigot.utils.annotations.ExpressionProperty;
import me.limeglass.skungee.spigot.utils.annotations.Patterns;

@Name("Bungeecord event players")
@Description("Returns the Bungeecord player(s) invloved in the event.")
@Patterns("[(all [[of] the]|the)] event (skungee|bungee[[ ]cord]) player[s]")
@ExpressionProperty(ExpressionType.SIMPLE)
@DetermineSingle("players")
@Events({SkungeePlayerDisconnect.class, SkungeePlayerSwitchServer.class})
public class ExprEventBungeePlayers extends SkungeeExpression<Object> {
	
	@Override
	protected Object[] get(Event event) {
		try {
			Method method = event.getClass().getDeclaredMethod("getConverted");
			if (method == null) return null;
			method.setAccessible(true);
			return (Object[]) method.invoke(event);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
		return null;
	}
}
