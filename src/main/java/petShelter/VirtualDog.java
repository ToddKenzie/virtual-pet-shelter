package petShelter;

public class VirtualDog extends VirtualPet{
	
	private int thirstFromPlaying = 3;

	protected VirtualDog(String petName, String description) {
		this(petName, description, 15, 20, 25);
	}
	
	protected VirtualDog(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = new Illness(30);
		this.playValue = 10;
		this.petType = "Dog";
		this.increasedHungerFromTick = 3;
		this.increasedThirstFromTick = 4;
		this.increasedBoredomFromTick = 3;
	}
	
	protected void play() {
		boredom.subtract(playValue);
		thirst.add(thirstFromPlaying); 
	}



}
