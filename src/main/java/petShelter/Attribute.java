package petShelter;

public abstract class Attribute {

	protected int attributeValue;
	
	protected Attribute(int startingValue) {
		this.attributeValue = startingValue;
	}
	
	protected int getValue() {
		return attributeValue;
	}
	
	protected void add(int augend) {
		this.attributeValue += augend;
	}
	
	protected void subtract(int subtrahend) {
		this.attributeValue -= subtrahend;
	}
	
	protected void setToZero() {
		this.attributeValue = 0;
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
