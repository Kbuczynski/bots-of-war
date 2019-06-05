package ncdc.bow.model;

public class GameSettings {
	
	private Unit archer, horse, warrior, worker;
	private int miningPerTurn, numberOfResources;
	
	public GameSettings() {
	}

	public Unit getArcher() {
		return archer;
	}

	public void setArcher(Unit archer) {
		this.archer = archer;
	}

	public Unit getHorse() {
		return horse;
	}

	public void setHorse(Unit horse) {
		this.horse = horse;
	}

	public Unit getWarrior() {
		return warrior;
	}

	public void setWarrior(Unit warrior) {
		this.warrior = warrior;
	}

	public Unit getWorker() {
		return worker;
	}

	public void setWorker(Unit worker) {
		this.worker = worker;
	}

	public int getMiningPerTurn() {
		return miningPerTurn;
	}

	public void setMiningPerTurn(int miningPerTurn) {
		this.miningPerTurn = miningPerTurn;
	}

	public int getNumberOfResources() {
		return numberOfResources;
	}

	public void setNumberOfResources(int numberOfResources) {
		this.numberOfResources = numberOfResources;
	}

	@Override
	public String toString() {
		return "GameSettings [archer=" + archer + ", horse=" + horse + ", warrior=" + warrior + ", worker=" + worker
				+ ", miningPerTurn=" + miningPerTurn + ", numberOfResources=" + numberOfResources + "]";
	}
	
}
