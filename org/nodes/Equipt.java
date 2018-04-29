package org.nodes;

import org.core.Connect;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.skills.Skill;

public class Equipt extends Connect {

	public Equipt(MethodContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	boolean logic() {
		if (!g().getEquipment().contains("scimitar")) {
			if ((g().getSkills().getRealLevel(Skill.ATTACK) < 10) && g().getInventory().contains("Iron scimitar")) {
				return true;
			}
			if (((g().getSkills().getRealLevel(Skill.ATTACK) >= 10)
					&& (g().getSkills().getRealLevel(Skill.ATTACK) < 20))
					&& g().getInventory().contains("Black scimitar")) {
				return true;
			}
			if (((g().getSkills().getRealLevel(Skill.ATTACK) >= 20)
					&& (g().getSkills().getRealLevel(Skill.ATTACK) < 30))
					&& g().getInventory().contains("Mithril scimitar")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return logic();
	}

	@Override
	public int execute() {
		if ((g().getSkills().getRealLevel(Skill.ATTACK) < 10) && g().getInventory().contains("Iron scimitar")) {
			g().getInventory().get("Iron scimitar").interact();
		}
		if (((g().getSkills().getRealLevel(Skill.ATTACK) >= 10) && (g().getSkills().getRealLevel(Skill.ATTACK) < 20))
				&& g().getInventory().contains("Black scimitar")) {
			g().getInventory().get("Black scimitar").interact();
		}
		if (((g().getSkills().getRealLevel(Skill.ATTACK) >= 20) && (g().getSkills().getRealLevel(Skill.ATTACK) < 30))
				&& g().getInventory().contains("Mithril scimitar")) {
			g().getInventory().get("Mithril scimitar").interact();
		}
		return 300;
	}

}
