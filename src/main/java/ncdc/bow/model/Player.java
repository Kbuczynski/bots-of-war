package ncdc.bow.model;

import java.util.List;

public class Player {
	
	private boolean active;
	private Base base;
	private int gold;
	private int id;
	private String owner;
	private List<Unit> units;

	public Player() {
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "Player [active=" + active + ", base=" + base + ", gold=" + gold + ", id=" + id + ", owner=" + owner
				+ ", units=" + units + "]";
	}

}
