package petShelter;

public class VirtualDog extends VirtualPet{
	
	private int thirstFromPlaying = 3;

	protected VirtualDog(String petName, String description) {
		this(petName, description, 15, 20, 25);
	}
	
	protected VirtualDog(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = 30;
		this.playValue = 10;
		this.petType = "Dog";
	}
	
	protected void play() {
		boredom -= playValue;
		thirst += thirstFromPlaying;
	}


}
