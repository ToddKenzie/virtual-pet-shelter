package petShelter;

public class VirtualPet {

	
	private String petName;
	private String description;
	private int hunger;
	private int thirst;
	private int boredom;

	public String getName() {
		return petName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getHunger() {
		return hunger;
	}
	
	public int getThirst() {
		return thirst;
	}
	
	public int getBoredom() {
		return boredom;
	}
	
	protected VirtualPet(String petName, String description) {
		this(petName, description, 15, 20, 25);
	}

	protected VirtualPet(String petName, String description, int hunger, int thirst, int boredom) {
		this.petName = petName;
		this.description = description;
		this.hunger = hunger;
		this.thirst = thirst;
		this.boredom = boredom;
	}

	protected void feed() {
		hunger -= 5;
	}

	protected void giveWater() {
		thirst -= 5;
	}

	protected void play() {
		boredom -= 5;
	}



}
