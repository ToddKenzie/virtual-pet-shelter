package petShelter;

public class VirtualDog extends VirtualPet{
	
	private int thirstFromPlaying = 3;

	protected VirtualDog(String petName, String description) {
		this(petName, description, 15, 20, 25);
		hunger.increaseValue((int)(Math.random() * 5));
		thirst.increaseValue((int)(Math.random() * 5));
		boredom.increaseValue((int)(Math.random() * 5));
	}
	
	protected VirtualDog(String petName, String description, int hunger, int thirst, int boredom) {
		super(petName, description, hunger, thirst, boredom);
		this.illness = new Illness(30);
		this.petType = "Dog";
		this.increasedHungerFromTick = (int)(Math.random() * 4 + 2);
		this.increasedThirstFromTick = (int)(Math.random() * 2 + 3);
		this.increasedBoredomFromTick = (int)(Math.random() * 4 + 3);
	}
	
	protected void play() {
		boredom.decreaseValue();
		thirst.increaseValue(thirstFromPlaying); 
	}



}
