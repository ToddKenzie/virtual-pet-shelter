package petShelter;

public class VirtualCat extends VirtualPet{

	protected VirtualCat(String petName, String description) {
		this(petName, description, 25, 10, 10);
	}
	
	protected VirtualCat(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = new Illness(20);
		this.petType = "Cat";
		this.increasedHungerFromTick = 2;
		this.increasedThirstFromTick = 3;
		this.increasedBoredomFromTick = 2;
	}


	
}
