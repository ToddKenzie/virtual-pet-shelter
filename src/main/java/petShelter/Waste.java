package petShelter;

public class Waste extends Attribute{

	protected Waste(int startingValue) {
		super(startingValue);
	}

	protected void setToZero() {
		attributeValue = -1;
	}
	
	public String toString() {
		if (attributeValue == 0) {
			return "Spotless!";
		} else if (attributeValue <= 2) {
			 return "Clean";
		} else if (attributeValue <= 4) {
			return "Dirty";
		} else if (attributeValue <= 6) {
			return "Awful!";
		} else {
			return "Health Hazard!!!";
		}
	}
}
