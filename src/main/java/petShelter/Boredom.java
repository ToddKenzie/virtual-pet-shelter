package petShelter;

public class Boredom extends Attribute{

	protected Boredom(int startingValue) {
		super(startingValue);
	}
	
	protected void increaseValue(int augend) {
		if (this.attributeValue + augend >= 50) {
			this.attributeValue = 50;
		} else {
			this.attributeValue += augend;
		}
	}
	
}
