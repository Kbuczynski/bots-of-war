package ncdc.bow.model;

import java.util.List;

import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Map implements TileBasedMap {

	private Integer[][] originalMap;
	private Integer[][] mapWithUnits;

	public Map(Integer[][] map) {
		this.originalMap = getRotatedMap(map);
		resetMapWithUnits();
	}

	private Integer[][] getRotatedMap(Integer[][] map) {
		Integer[][] rotatedMap = new Integer[map.length][map[0].length];

		for (int y = 0; y < map.length; y++) {
			rotatedMap[y] = map[map.length - y - 1];
		}

		return rotatedMap;
	}

	public void resetMapWithUnits() {
		mapWithUnits = new Integer[originalMap.length][originalMap[0].length];

		for (int y = 0; y < mapWithUnits.length; y++) {
			for (int x = 0; x < mapWithUnits[y].length; x++) {
				mapWithUnits[y][x] = originalMap[y][x];
			}
		}
	}
	
	public void updateMapWithUnits(List<Unit> ownUnits, List<Unit> enemyUnits) {
		resetMapWithUnits();
		addOwnUnitsToMap(ownUnits);
		addEnemyUnitsToMap(enemyUnits);
	}
	
	private void addOwnUnitsToMap(List<Unit> ownUnits) {
		addUnitsToMap(ownUnits, 6);
	}
	
	private void addEnemyUnitsToMap(List<Unit> enemyUnits) {
		addUnitsToMap(enemyUnits, 7);
	}

	private void addUnitsToMap(List<Unit> units, int number) {
		for (Unit unit : units) {
			Coordinates coordinates = unit.getCoordinates();
			mapWithUnits[coordinates.getY()][coordinates.getX()] = number;
		}
	}

	public void printMap() {
		Integer[][] rotatedMap = getRotatedMap(originalMap);

		for (int y = 0; y < rotatedMap.length; y++) {
			for (int x = 0; x < rotatedMap[y].length; x++) {
				System.out.print(rotatedMap[y][x] + " ");
			}
			System.out.println();
		}
	}

	public void printMapWithUnits() {
		Integer[][] rotatedMap = getRotatedMap(mapWithUnits);

		for (int y = 0; y < rotatedMap.length; y++) {
			for (int x = 0; x < rotatedMap[y].length; x++) {
				System.out.print(rotatedMap[y][x] + " ");
			}
			System.out.println();
		}
	}

	public void printMapWithPath(Path path) {
		Integer[][] rotatedMap = getRotatedMap(originalMap);

		for (int y = 0; y < rotatedMap.length; y++) {
			for (int x = 0; x < rotatedMap[y].length; x++) {
				System.out.print((path.contains(x, rotatedMap.length - y - 1) ? "." : rotatedMap[y][x]) + " ");
			}
			System.out.println();
		}
	}

	@Override
	public int getWidthInTiles() {
		return this.originalMap[0].length;
	}

	@Override
	public int getHeightInTiles() {
		return this.originalMap.length;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		Integer tile = originalMap[ty][tx];
		return !(tile == 0 || tile == 1 || tile == 5);
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 0;
	}

}
