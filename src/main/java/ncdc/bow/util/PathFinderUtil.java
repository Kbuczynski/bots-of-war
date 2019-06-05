package ncdc.bow.util;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.springframework.stereotype.Component;

import ncdc.bow.model.Coordinates;
import ncdc.bow.model.Map;

@Component
public class PathFinderUtil {

	private Map map;

	public List<String> getMovesList(Coordinates from, Coordinates to) {
		return getMovesListFromPath(getPath(from, to));
	}

	private List<String> getMovesListFromPath(Path path) {
		List<String> arr = new ArrayList<String>();
		if (path != null) {
			for (int i = 0; i < path.getLength() - 1; i++) {
				arr.add(getStepDirection(path, i, false));

			}
		}
		return arr;
	}

	private Path getPath(Coordinates from, Coordinates to) {
		return new AStarPathFinder(map, 1000, false).findPath(null, from.getX(), from.getY(), to.getX(), to.getY());
	}

	private String getStepDirection(Path path, int step, boolean useArrows) {
		String arrows[] = { "↓", "↑", "←", "→" };
		String letters[] = { "D", "U", "L", "R" };
		String signs[] = useArrows ? arrows : letters;
		int oldX = path.getStep(step).getX();
		int oldY = path.getStep(step).getY();
		int newX = path.getStep(step + 1).getX();
		int newY = path.getStep(step + 1).getY();
		if (oldY > newY)
			return signs[0];
		if (oldY < newY)
			return signs[1];
		if (oldX > newX)
			return signs[2];
		if (oldX < newX)
			return signs[3];
		return "";
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Integer[][] rawMap) {
		this.map = new Map(rawMap);
	}

}
