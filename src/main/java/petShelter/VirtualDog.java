package petShelter;

public class VirtualDog extends VirtualPet{

	protected VirtualDog(String petName, String description) {
		this(petName, description, 15, 20, 25);
	}
	
	protected VirtualDog(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = 30;
	}

}
