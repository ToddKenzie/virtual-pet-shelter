package petShelter;

public abstract class Attribute {

	protected int attributeValue;
	
	protected Attribute(int startingValue) {
		this.attributeValue = startingValue;
	}
	
	protected int getValue() {
		return attributeValue;
	}
	
	protected void increaseValue(int augend) {
		this.attributeValue += augend;
	}
	
	protected void decreaseValue() {
		if (attributeValue < 14) {
			attributeValue = 0;
		} else {
			attributeValue -= (14 + (attributeValue % 7));
		}
	}
	
	public String emote() {
		if (attributeValue < 7) {
			return " :D";
		} else if (attributeValue < 14) {
			return " :)";
		} else if (attributeValue <= 21) {
			return " :|";
		} else if (attributeValue <= 28) {
			return " :/";
		} else if (attributeValue <= 35) {
			return " :(";
		} else if (attributeValue <= 42) {
			return " :`(";
		} else {
			return " X(";
		}
	}
	
}
