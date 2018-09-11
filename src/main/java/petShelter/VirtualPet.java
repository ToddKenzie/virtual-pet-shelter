package petShelter;

public abstract class VirtualPet {

	protected String petName;
	protected String description;
	protected int hunger;
	protected int thirst;
	protected int boredom;
	protected int illness;
	protected int playValue;
	protected String petType = null;

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
	
	public int getIllness() {
		return illness;
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
		this.illness = 0;
		this.playValue = 5;
	}

	protected void feed() {
		hunger -= 5;
	}

	protected void giveWater() {
		thirst -= 5;
	}

	protected void play() {
		boredom -= playValue;
	}

	protected void treatByVet() {
		illness -= 15;
	}

	protected void tick(int waste) {
		illness += waste;
	}

	protected void jealous() {
		boredom += 3;
	}
	
	protected void hungerFromBird() {
		hunger += 3;
		
	}
	public String toString() {
		return petName + "\t|" + petType + "\t|" + hunger + "\t|" + thirst + "\t|" + boredom + "\t|" + illness;
	}




}
