package petShelter;

public class Illness extends Attribute{

	protected Illness(int startingValue) {
		super(startingValue);
	}
	
	protected void decreaseValue() {
		if(attributeValue < 15) {
			attributeValue = 0;
		} else {
			attributeValue -= 15;
		}
	}

}
