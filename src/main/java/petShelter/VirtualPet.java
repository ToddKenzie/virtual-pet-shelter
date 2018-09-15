package petShelter;

public abstract class VirtualPet {

	protected String petName;
	protected String description;
	protected Hunger hunger;
	protected Thirst thirst;
	protected Boredom boredom;

	protected String petType = null;

	protected Illness illness;
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
		
	}

	protected void feed() {
		hunger.decreaseValue();
	}

	protected void giveWater() {
		thirst.decreaseValue();
	}

	protected void play() {
		boredom.decreaseValue();
	}

	protected void treatByVet() {
		illness.decreaseValue();
	}

	protected void tick(int waste) {
		illness.increaseValue(waste);
		increaseValuesDueToTick();
	}

	protected void increaseValuesDueToTick() {
		hunger.increaseValue(increasedHungerFromTick);
		thirst.increaseValue(increasedThirstFromTick);
		boredom.increaseValue(increasedBoredomFromTick);
	}

	protected void jealous() {
		boredom.increaseValue(3);
	}

	protected void hungerFromBird() {
		hunger.increaseValue(3);

	}

	public String toString() {
		return petType + "\t|" + hunger.emote() + "\t|" + thirst.emote() + "\t|" + boredom.emote() + "\t|"
				+ illness.emote() + "\t|" + petName;
	}

}
