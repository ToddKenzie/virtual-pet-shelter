package petShelter;

public class Waste extends Attribute{

	protected Waste(int startingValue) {
		super(startingValue);
	}

	protected void decreaseValue() {
		attributeValue = 0;
	}
	
	public String toString() {
		if (attributeValue <= 1) {
			return "Spotless!";
		} else if (attributeValue <= 3) {
			 return "Clean";
		} else if (attributeValue <= 6) {
			return "Dirty";
		} else if (attributeValue <= 9) {
			return "Awful!";
		} else {
			return "Health Hazard!!!";
		}
	}
}
