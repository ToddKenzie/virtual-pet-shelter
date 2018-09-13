package petShelter;

public class VirtualBird extends VirtualPet {

	protected VirtualBird(String petName, String description) {
		this(petName, description, 15, 25, 15);
		hunger.increaseValue((int)(Math.random() * 5));
		thirst.increaseValue((int)(Math.random() * 5));
		boredom.increaseValue((int)(Math.random() * 5));
	}
	
	protected VirtualBird(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness =  new Illness(10);
		this.petType = "Bird";
		this.increasedHungerFromTick = (int)(Math.random() * 3 + 2);
		this.increasedThirstFromTick = (int)(Math.random() * 3 + 2);
		this.increasedBoredomFromTick = (int)(Math.random() * 3 + 2);
	}


}
