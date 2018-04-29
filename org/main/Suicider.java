package org.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.core.Connect;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.nodes.Attack;
import org.nodes.Equipt;

@ScriptManifest(author = "Farhad", category = Category.COMBAT, name = "Suicider Man", version = 1.0)
public class Suicider extends AbstractScript {

	private final List<Connect> open = new ArrayList<>();
	private final List<Connect> cache = new ArrayList<>();
	private long timeRan;
	private long timeBegan;

	@Override
	public void onStart() {
		timeBegan = System.currentTimeMillis();
		cache.add(new Attack(this));
		cache.add(new Equipt(this));
	}

	@Override
	public int onLoop() {
		int delay = Calculations.random(300, 500);
		if (!cache.isEmpty()) {
			open.clear();
			open.addAll(cache.stream().filter((Connect) -> Connect.validate()).collect(Collectors.toList()));
			if (!open.isEmpty()) {
				delay = getAvailableConnections().execute();
			}
		}
		return delay;
	}

	public Connect getAvailableConnections() {
		Connect node = null;
		if (!open.isEmpty()) {
			node = open.get(0);
			if (open.size() > 0) {
				for (Connect active : open) {
					if (node.priority() < active.priority())
						node = active;
				}
			}
		}
		return node;
	}

	@Override
	public void onExit() {

	}

	public void onPaint(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		timeRan = System.currentTimeMillis() - timeBegan;
		g.drawString("Time: " + ft(timeRan), 10, 140);
		g.drawString("By Farhad", 10, 160);

	}

	public String ft(long duration) {
		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		if (days == 0) {
			res = (hours + ":" + minutes + ":" + seconds);
		} else {
			res = (days + ":" + hours + ":" + minutes + ":" + seconds);
		}
		return res;
	}

}
