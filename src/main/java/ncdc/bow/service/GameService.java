package ncdc.bow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncdc.bow.model.Action;
import ncdc.bow.model.Coordinates;
import ncdc.bow.model.GameOrder;
import ncdc.bow.model.GameSettings;
import ncdc.bow.model.GameState;
import ncdc.bow.model.Player;
import ncdc.bow.model.Unit;
import ncdc.bow.util.ApiUtil;
import ncdc.bow.util.PathFinderUtil;

@Service
public class GameService {

	Player active;
	Player enemy;
	GameState gameState;
	GameSettings gameSettings;
	List<Action> actions = new ArrayList<>();
	List<Unit> myWarriors;

	@Autowired
	PathFinderUtil pathFinder;

	public GameOrder getOrder(GameState gameState) {
		GameOrder gameOrder = new GameOrder();

		setGameState(gameState);
		setActivePlayer();
		setMapAndGameSettings();
		clearActions();
		sortUnits();
		executeOrders();
		setActions(gameOrder);

		return gameOrder;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setActivePlayer() {
		if (gameState.getPlayer1().isActive()) {
			active = gameState.getPlayer1();
			enemy = gameState.getPlayer2();
		} else {
			active = gameState.getPlayer2();
			enemy = gameState.getPlayer1();
		}
	}
	
	public void sortUnits() {
		myWarriors = new ArrayList<>();

		for (Unit unit : active.getUnits()) {
			switch (unit.getName()) {
			case "WARRIOR": {
				myWarriors.add(unit);
				break;
			}
			case "ARCHER": {
				myWarriors.add(unit);
				break;
			}
			case "HORSE": {
				myWarriors.add(unit);
				break;
			}
			}
		}
	}

	public void setMapAndGameSettings() {
		if (gameState.isFirstTurn()) {
			pathFinder.setMap(ApiUtil.getMap());
			gameSettings = ApiUtil.getGameSettings();
		}
	}

	public void clearActions() {
		actions.clear();
	}

	public void setActions(GameOrder gameOrder) {
		gameOrder.setActions(actions);
	}
	
	public void executeOrders() {	
		
		for (Unit warrior : myWarriors) {
			goToEnemyBase(warrior);
			checkRange(warrior);			
		}
		
		if (canRecruit())
			recruitUnit("A");
		
		if (hasUnit("WORKER")) {
			goToClosestMine();			
		} else {
			recruitUnit("W");
		}
		
		if (canRecruit())
			recruitUnit("A");
	}

	public boolean hasUnit(String unitName) {
		return active.getUnits().stream().filter(unit -> unit.getName().equals(unitName)).count() > 0;
	}

	public Unit getUnit(String unitName) {
		return active.getUnits().stream().filter(unit -> unit.getName().equals(unitName)).findFirst().get();
	}
	
	private boolean canRecruit() {
		Coordinates baseCords = active.getBase().getCoordinates();

		for (Action action : actions) {
			if (action.getActionType().equals("RECRUIT")) {
				return false;
			}
		}

		for (Unit unit : active.getUnits()) {
			if (hasSameCords(unit.getCoordinates(), baseCords)) {
				return false;
			}
		}

		return true;
	}
	
	public void moveUnit(Unit unit, List <String> movesList) {
		while (unit.getActionPoints() > 0) {
			if (movesList.size() > 0) {
				actions.add(new Action(unit.getId(), "MOVE", movesList.get(0)));
				movesList.remove(0);
			}
			unit.setActionPoints(unit.getActionPoints() - 1);
		}
	}
	
	public void goToClosestMine() {
		Unit worker = getUnit("WORKER");
		int tab[] = new int [gameState.getMines().size()];
		int min = 0;
		int m = 0;
		
		for (int i = 0; i < gameState.getMines().size(); i++) {
			Coordinates randomMine = gameState.getMines().get(i).getCoordinates();
			List <String> movesList = pathFinder.getMovesList(worker.getCoordinates(), randomMine);
			
			tab[i] = movesList.size();
		}
		
		min = tab[0];
		
		for (int j = 0; j < tab.length; j++) {
			if (tab[j] < min) {
				tab[j] = min;
				m = j;
			}
		}
		
		Coordinates closestMine = gameState.getMines().get(m).getCoordinates();
		List <String> movesList = pathFinder.getMovesList(worker.getCoordinates(), closestMine);
		
		moveUnit(worker, movesList);
	}
	
	public void goToEnemyBase(Unit unit) {
		Coordinates enemyBase = new Coordinates(enemy.getBase().getCoordinates().getX(), 
				enemy.getBase().getCoordinates().getY());
		List <String> movesList = null;
		
		
		
		if (unit.getCost() == gameSettings.getWarrior().getCost()) {
			if (active.getBase().getCoordinates().getX() < 10) {
				enemyBase.setX(enemyBase.getX() - 1);
				movesList = pathFinder.getMovesList(unit.getCoordinates(), enemyBase);
			} else {
				enemyBase.setX(enemyBase.getX() + 1);
				movesList = pathFinder.getMovesList(unit.getCoordinates(), enemyBase);
			}
		}
		if (unit.getCost() == gameSettings.getArcher().getCost()) {
			if (active.getBase().getCoordinates().getX() < 10) {
				enemyBase.setX(enemyBase.getX() - 2);
				movesList = pathFinder.getMovesList(unit.getCoordinates(), enemyBase);
			} else {
				enemyBase.setX(enemyBase.getX() + 2);
				movesList = pathFinder.getMovesList(unit.getCoordinates(), enemyBase);
			}
		}
		
		moveUnit(unit, movesList);
	}
	
	public void checkRange(Unit unit) {
		Coordinates enemyBasePosition = new Coordinates(enemy.getBase().getCoordinates().getX(), enemy.getBase().getCoordinates().getY());
		int range = 0;
		
		if (unit.getCost() == gameSettings.getWarrior().getCost()) range = unit.getRangeOfAttack();
		if (unit.getCost() == gameSettings.getArcher().getCost()) range = unit.getRangeOfAttack() - 1;
		
		Coordinates a = new Coordinates(unit.getCoordinates().getX() + range, unit.getCoordinates().getY());
		Coordinates b = new Coordinates(unit.getCoordinates().getX() - range, unit.getCoordinates().getY());
		Coordinates c = new Coordinates(unit.getCoordinates().getX(), unit.getCoordinates().getY() + range);
		Coordinates d = new Coordinates(unit.getCoordinates().getX(), unit.getCoordinates().getY() - range);
		
		if (unit.getCost() == gameSettings.getWarrior().getCost()) {
			if (hasSameCords(enemyBasePosition, a)) {
				attackEnemyBase(unit);
			} else if (hasSameCords(enemyBasePosition, b)) {
				attackEnemyBase(unit);
			} else if (hasSameCords(enemyBasePosition, c)) {
				attackEnemyBase(unit);
			} else if (hasSameCords(enemyBasePosition, d)) {
				attackEnemyBase(unit);
			}
		}
		if (unit.getCost() == gameSettings.getArcher().getCost()) {
			if (hasSameCords(enemyBasePosition, a)) {
				attackEnemyBase(unit);
			} else if (hasSameCords(enemyBasePosition, b)) {
				attackEnemyBase(unit);
			} else if (hasSameCords(enemyBasePosition, c)) {
				attackEnemyBase(unit);
			} else if (hasSameCords(enemyBasePosition, d)) {
				attackEnemyBase(unit);
			}
		}
			
	}
	
	private boolean hasSameCords(Coordinates source, Coordinates target) {
		return source.getX() == target.getX() && source.getY() == target.getY();
	}
	
	public void attackEnemyBase(Unit unit) {
		actions.add(new Action(unit.getId(), "ATTACK", enemy.getBase().getId()));		
	}

	public void recruitUnit(String unitType) {
		actions.add(new Action(active.getBase().getId(), "RECRUIT", unitType));
	}

}
