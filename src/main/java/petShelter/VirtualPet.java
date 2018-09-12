package petShelter;

public abstract class VirtualPet {

	protected String petName;
	protected String description;
	protected Hunger hunger;
	protected Thirst thirst;
	protected Boredom boredom;
	
	protected String petType = null;
	
	protected Illness illness;
	protected int playValue;
	protected int increasedHungerFromTick;
	protected int increasedThirstFromTick;
	protected int increasedBoredomFromTick;

	public String getName() {
		return petName;
	}
	
	public String getDescription() {
		return description;
	}
	
	protected int getHunger() {
		return hunger.getValue();
	}
	
	protected int getThirst() {
		return thirst.getValue();
	}
	
	protected int getBoredom() {
		return boredom.getValue();
	}
	
	protected int getIllness() {
		return illness.getValue();
	}
	
	protected VirtualPet(String petName, String description) {
		this(petName, description, 15, 20, 25);
	}

	protected VirtualPet(String petName, String description, int hunger, int thirst, int boredom) {
		this.petName = petName;
		this.description = description;
		this.hunger = new Hunger(hunger);
		this.thirst = new Thirst(thirst);
		this.boredom = new Boredom(boredom);
		this.illness = new Illness(25);
		this.playValue = 5;
	}

	protected void feed() {
		if (hunger.getValue() < 5) {
			hunger.setToZero();;
		} else {
			hunger.subtract(5);;
		}
	}

	protected void giveWater() {
		if (thirst.getValue() < 5) {
			thirst.setToZero();;
		} else {
			thirst.subtract(5);
		}
	}

	protected void play() {
		boredom.subtract(playValue);
	}

	protected void treatByVet() {
		if (illness.getValue() < 15) {
			illness.setToZero();;
		} else {
			illness.subtract(15);
		}
	}

	protected void tick(int waste) {
		illness.add(waste);
		increaseValuesDueToTick();
	}

	protected void increaseValuesDueToTick() {
		hunger.add(increasedHungerFromTick);
		thirst.add(increasedThirstFromTick);
		boredom.add(increasedBoredomFromTick);
	}

	protected void jealous() {
		boredom.add(3);
	}
	
	protected void hungerFromBird() {
		hunger.add(3);
		
	}
	public String toString() {
		return petType + "\t|" + hunger.emote() + "\t|" + thirst.emote() + "\t|" + boredom.emote() + "\t|" + illness.emote() + "\t|" + petName;
	}




}
