package petShelter;

public class VirtualBird extends VirtualPet {

	protected VirtualBird(String petName, String description) {
		this(petName, description, 15, 25, 15);
	}
	
	protected VirtualBird(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = 10;
	}

}