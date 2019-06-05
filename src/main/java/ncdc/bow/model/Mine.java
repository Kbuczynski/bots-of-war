package ncdc.bow.model;

public class Mine {
	
	private Coordinates coordinates;
	private int goldLeft;
	private String id;
	private int miningPerWorker;
	private String owner;
	private int workersNumber;
	
	public Mine() {
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getGoldLeft() {
		return goldLeft;
	}

	public void setGoldLeft(int goldLeft) {
		this.goldLeft = goldLeft;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMiningPerWorker() {
		return miningPerWorker;
	}

	public void setMiningPerWorker(int miningPerWorker) {
		this.miningPerWorker = miningPerWorker;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getWorkersNumber() {
		return workersNumber;
	}

	public void setWorkersNumber(int workersNumber) {
		this.workersNumber = workersNumber;
	}

	@Override
	public String toString() {
		return "Mine [coordinates=" + coordinates + ", goldLeft=" + goldLeft + ", id=" + id + ", miningPerWorker="
				+ miningPerWorker + ", owner=" + owner + ", workersNumber=" + workersNumber + "]";
	}

}
