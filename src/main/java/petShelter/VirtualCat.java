package petShelter;

public class VirtualCat extends VirtualPet{

	protected VirtualCat(String petName, String description) {
		this(petName, description, 25, 10, 10);
	}
	
	protected VirtualCat(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = 20;
		this.petType = "Cat";
	}

	@Override
	protected void increaseValuesDueToTick() {
		hunger += 2;
		thirst += 3;
		boredom += 2;
		
	}

	
}
