package org.nodes;

import org.core.Connect;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.wrappers.interactive.NPC;

public class Attack extends Connect {

	private int priority = 2;
	private NPC n;

	public Attack(MethodContext g) {
		super(g);
	}

	@Override
	public boolean validate() {
		n = g().getNpcs().closest("Man");
		return n != null && n.canAttack();
	}

	@Override
	public int execute() {
		if (g().getLocalPlayer().getTile().distance(n) < 10) {
			if (n.isOnScreen()) {
				if (!g().getLocalPlayer().isInCombat()) {
					if (n.canAttack()) {
						n.interact("Attack");
					}
				}
			} else {
				g().getCamera().rotateToEntity(n);
			}
		} else {
			g().getWalking().walk(n.getTile());
		}
		return 300;

	}

	@Override
	public int priority() {
		return priority;
	}

}
