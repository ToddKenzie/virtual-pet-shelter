package petShelter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {
	
	private int waste;
	Map<String, VirtualPet> shelteredPets= new HashMap<>();
	VirtualPetGenerator petGenerator;
	
	public VirtualPetShelter() {
		waste = 0;
	}
	
	protected void takeInNewPet(VirtualPet newPet) {
		shelteredPets.put(newPet.getName(), newPet);
	}

	public void takeInNewPet(String petType, String petName, String description) {
		 petGenerator = new VirtualPetGenerator();
		 VirtualPet newPet = petGenerator.createPet(petType, petName, description);
		 takeInNewPet(newPet);
	}

	public void takeInNewPet(String petType, String petName, String description, int hunger, int thirst, int boredom) {
		 petGenerator = new VirtualPetGenerator();
		 VirtualPet newPet = petGenerator.createPet(petType, petName, description, hunger, thirst, boredom);
		 takeInNewPet(newPet);		
	}
	
	public VirtualPet retrievePetInfo(String petName) {
		return shelteredPets.get(petName);
	}

	public Collection<VirtualPet> getAllPets() {
		return shelteredPets.values();
	}
	
	public void feedAllPets() {
		Collection<VirtualPet> allPets = getAllPets();
		for (VirtualPet virtualPet : allPets) {
			virtualPet.feed();
		}
	}

	public void giveWaterToAllPets() {
		Collection<VirtualPet> allPets = getAllPets();
		for (VirtualPet virtualPet : allPets) {
			virtualPet.giveWater();
		}
	}

	public void playWith(String petName) {
		VirtualPet petToPlayWith = retrievePetInfo(petName);
		petToPlayWith.play();
	}

	public void callVetFor(String petName) {
		VirtualPet petToTreat = retrievePetInfo(petName);
		petToTreat.treatByVet();
		
	}

	public void tick() {
		waste++;
		Collection<VirtualPet> allPets = getAllPets();
		for (VirtualPet virtualPet : allPets) {
			virtualPet.tick(waste);
		}	
	}

	public void cleanShelter() {
		waste = -1;
	}


}
