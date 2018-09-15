package petShelter;

public class Illness extends Attribute{

	protected Illness(int startingValue) {
		super(startingValue);
	}
	
	protected void increaseValue(int augend) {
		int increaseBy = (int)Math.round(augend / 3.0);
		attributeValue += increaseBy;
	}
	
	protected void decreaseValue() {
		if(attributeValue < 20) {
			attributeValue = 0;
		} else {
			attributeValue -= 20;
		}
	}

}
