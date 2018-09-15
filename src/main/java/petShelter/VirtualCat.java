package petShelter;

public class VirtualCat extends VirtualPet{

	protected VirtualCat(String petName, String description) {
		this(petName, description, 20, 15, 10);
		hunger.increaseValue((int)(Math.random() * 5));
		thirst.increaseValue((int)(Math.random() * 5));
		boredom.increaseValue((int)(Math.random() * 5));
	}
	
	protected VirtualCat(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = new Illness(20);
		this.petType = "Cat";
		this.increasedHungerFromTick = (int)(Math.random() * 3 + 2);
		this.increasedThirstFromTick = (int)(Math.random() * 2 + 3);
		this.increasedBoredomFromTick = (int)(Math.random() * 2 + 2);
	}


	
}
