package ncdc.bow.model;

import java.util.List;

public class GameState {

	private int currentStep;
	private int currentTurn;
	private int gameId;
	private List<Action> lastActions;
	private List<String> lastLogs;
	private List<Mine> mines;
	private String mapPath;
	private Player player1;
	private Player player2;
	private int tournamentId;

	public GameState() {
	}

	public Boolean isFirstTurn() {
		return currentTurn == 1;
	}
	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public List<Action> getLastActions() {
		return lastActions;
	}

	public void setLastActions(List<Action> lastActions) {
		this.lastActions = lastActions;
	}

	public List<String> getLastLogs() {
		return lastLogs;
	}

	public void setLastLogs(List<String> lastLogs) {
		this.lastLogs = lastLogs;
	}

	public List<Mine> getMines() {
		return mines;
	}

	public void setMines(List<Mine> mines) {
		this.mines = mines;
	}

	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	@Override
	public String toString() {
		return "GameState [currentStep=" + currentStep + ", currentTurn=" + currentTurn + ", gameId=" + gameId
				+ ", lastActions=" + lastActions + ", lastLogs=" + lastLogs + ", mines=" + mines + ", mapPath="
				+ mapPath + ", player1=" + player1 + ", player2=" + player2 + ", tournamentId=" + tournamentId + "]";
	}

}
