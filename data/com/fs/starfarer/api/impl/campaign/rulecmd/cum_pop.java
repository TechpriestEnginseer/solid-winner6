package com.fs.starfarer.api.impl.campaign.rulecmd;

import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Misc.Token;


public class cum_pop extends BaseCommandPlugin {

	public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Token> params, final Map<String, MemoryAPI> memoryMap) {
		if (dialog == null) return false;
                if (!Global.getSector().getCharacterData().knowsHullMod(params.get(0).getString(memoryMap))) {Global.getSector().getCharacterData().addHullMod(params.get(0).getString(memoryMap));dialog.getTextPanel().setFontSmallInsignia();dialog.getTextPanel().addPara("You have unlocked "+Global.getSettings().getHullModSpec(params.get(0).getString(memoryMap)).getDisplayName(), Misc.getHighlightColor());dialog.getTextPanel().setFontInsignia();}
		return true;
	}
}

