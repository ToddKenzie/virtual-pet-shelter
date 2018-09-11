package petShelter;

public class VirtualPetGenerator {

	protected VirtualPet createPet(String petType, String petName, String description) {
		VirtualPet newPet = null;
		
		if (petType.equalsIgnoreCase("dog")) {
			newPet = new VirtualDog(petName, description);
		} else if(petType.equalsIgnoreCase("cat")) {
			newPet = new VirtualCat(petName, description);
		} else if (petType.equalsIgnoreCase("bird")) {
			newPet = new VirtualBird(petName, description);
		}
		
		return newPet;
	}

	protected VirtualPet createPet(String petType, String petName, String description, int hunger, int thirst,
			int boredom) {
		VirtualPet newPet = null;
		
		if (petType.equalsIgnoreCase("dog")) {
			newPet = new VirtualDog(petName, description, hunger, thirst, boredom);
		} else if(petType.equalsIgnoreCase("cat")) {
			newPet = new VirtualCat(petName, description, hunger, thirst, boredom);
		} else if (petType.equalsIgnoreCase("bird")) {
			newPet = new VirtualBird(petName, description, hunger, thirst, boredom);
		}
		
		return newPet;
	}

}
