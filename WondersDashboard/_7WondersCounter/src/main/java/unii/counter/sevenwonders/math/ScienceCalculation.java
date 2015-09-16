package unii.counter.sevenwonders.math;

public class ScienceCalculation {
	/**
	 * Points for creating a line:<br>
	 * -line contains plate, circle, caliper
	 */
	private static final int LINE_POINTS = 7;

	ScienceCalculation() {
	}

	public static int calculateSciencePoints(int plate, int circle,
			int caliper, int plateOrCircleOrCaliper) {

		// no choice for calculate
		if (plateOrCircleOrCaliper == 0) {
			return calculateSciencePoints(plate, circle, caliper);
		} else {
			int maxPoints = 0;
			for (int i = 0; i <= plateOrCircleOrCaliper; i++) {
				for (int j = 0; i + j <= plateOrCircleOrCaliper; j++) {
					int x = plateOrCircleOrCaliper - i - j;
					int sciencePoints = calculateSciencePoints(plate + x,
							circle + i, caliper + j);
					if (sciencePoints > maxPoints) {
						maxPoints = sciencePoints;
					}
				}
			}

			return maxPoints;
		}
	}

	/**
	 * Simple calculation of point in 7 wonders game
	 * 
	 * @param plate
	 * @param circle
	 * @param caliper
	 * @return
	 */
	private static int calculateSciencePoints(int plate, int circle, int caliper) {
		return (int) (Math.pow(plate, 2) + Math.pow(circle, 2)
				+ Math.pow(caliper, 2) + Math.min(Math.min(plate, circle),
				caliper) * LINE_POINTS);
	}

}
