package ncdc.bow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ncdc.bow.model.GameOrder;
import ncdc.bow.model.GameState;
import ncdc.bow.service.GameService;

@RestController
public class GameController {

	@Autowired
	GameService gameService;
	
	@PostMapping("/bot")
	public GameOrder makeOrder(@RequestBody GameState gameState) {
		return gameService.getOrder(gameState);
	}
	
}
