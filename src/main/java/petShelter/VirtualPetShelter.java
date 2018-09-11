package petShelter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {

	private int waste;
	private Map<String, VirtualPet> shelteredPets = new HashMap<>();
	private VirtualPetGenerator petGenerator;
	private int daysRunningTheShelter;

	public VirtualPetShelter() {
		waste = 0;
		daysRunningTheShelter = 0;
	}

	public int getDaysRunningTheShelter() {
		return daysRunningTheShelter;
	}

	public VirtualPet retrievePetInfo(String petName) {
		return shelteredPets.get(petName);
	}

	public Collection<VirtualPet> getAllPets() {
		return shelteredPets.values();
	}

	private void takeInNewPet(VirtualPet newPet) {
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

	public void adopt(String petName) {
		shelteredPets.remove(petName);
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
		checkAnimosity(petToPlayWith);
	}

	private void checkAnimosity(VirtualPet petToPlayWith) {
		Collection<VirtualPet> allPets = getAllPets();
		for (VirtualPet virtualPet : allPets) {
			if (petToPlayWith instanceof VirtualDog && virtualPet instanceof VirtualDog
					&& !virtualPet.equals(petToPlayWith)) {
				virtualPet.jealous();
			} else if (petToPlayWith instanceof VirtualBird && virtualPet instanceof VirtualCat) {
				virtualPet.hungerFromBird();
			}
		}

	}

	public void callVetFor(String petName) {
		VirtualPet petToTreat = retrievePetInfo(petName);
		petToTreat.treatByVet();

	}

	public void tick() {
		waste++;
		daysRunningTheShelter++;
		Collection<VirtualPet> allPets = getAllPets();
		for (VirtualPet virtualPet : allPets) {
			virtualPet.tick(waste);
		}
		checkUnCaredForPets(allPets);
	}

	private void checkUnCaredForPets(Collection<VirtualPet> allPets) {
		ArrayList<String> illPets = new ArrayList<>();
		for (VirtualPet virtualPet : allPets) {
			if (virtualPet.getIllness() > 50) {
				illPets.add(virtualPet.getName());
			}
		}
		for (String petName : illPets) {
			shelteredPets.remove(petName);
		}
	}

	public void cleanShelter() {
		waste = -1;
	}

}
