package br.com.ulife.spellbattle.service;

import java.util.Map;

import br.com.ulife.spellbattle.resource.BattleDuration;

public interface SpellBattleService {

	Map<String, Object> battle(BattleDuration duration);
	
}
