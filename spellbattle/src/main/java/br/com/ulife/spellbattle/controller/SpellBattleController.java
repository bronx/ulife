package br.com.ulife.spellbattle.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.ulife.spellbattle.resource.BattleDuration;
import br.com.ulife.spellbattle.service.SpellBattleService;

@Controller
@RequestScoped
public class SpellBattleController {

	@Inject private Result result;
	@Inject private SpellBattleService service;
	
	@Post("/battle")
	@Consumes("application/json")
	public void battleFor(BattleDuration duration) {
		
		result.use(Results.json()).withoutRoot().from(service.battle(duration)).serialize();
		
	}
	
}
