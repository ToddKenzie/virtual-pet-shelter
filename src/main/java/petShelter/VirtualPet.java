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
	
	protected int getHunger() {
		return hunger;
	}
	
	protected int getThirst() {
		return thirst;
	}
	
	protected int getBoredom() {
		return boredom;
	}
	
	protected int getIllness() {
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
		if (hunger < 5) {
			hunger = 0;
		} else {
			hunger -= 5;
		}
	}

	protected void giveWater() {
		if (thirst < 5) {
			thirst = 0;
		} else {
			thirst -= 5;
		}
	}

	protected void play() {
		boredom -= playValue;
	}

	protected void treatByVet() {
		if (illness < 15) {
			illness = 0;
		} else {
			illness -= 15;
		}
	}

	protected void tick(int waste) {
		illness += waste;
		increaseValuesDueToTick();
	}

	protected abstract void increaseValuesDueToTick(); 

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
