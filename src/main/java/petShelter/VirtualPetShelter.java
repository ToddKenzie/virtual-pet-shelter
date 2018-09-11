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
	private boolean wasAPetEuthanized;
	private boolean wasAPetAdopted;
	private boolean didAPetShowUpUnexpectedly;
	private String endOfDayPetChange;

	public VirtualPetShelter() {
		waste = 0;
		daysRunningTheShelter = 0;
	}

	public String getEndOfDayPetChange() {
		return endOfDayPetChange;
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
		resetPetArrivalAndLeaveBooleans();
		waste++;
		daysRunningTheShelter++;
		Collection<VirtualPet> allPets = getAllPets();
		checkForAutoAdoptedPets(allPets);
		for (VirtualPet virtualPet : allPets) {
			virtualPet.tick(waste);
		}
		checkUnCaredForPets(allPets);
		if (this.daysRunningTheShelter % 7 == 0) {
			generateNewRandomPet();
			didAPetShowUpUnexpectedly = true;
		}
		checkPetArrivalAndLeaveBooleans();
	}

	private void checkPetArrivalAndLeaveBooleans() {
		if (wasAPetAdopted || wasAPetEuthanized || didAPetShowUpUnexpectedly) {
			endOfDayPetChange = "At the end of the day\n";
			if (wasAPetAdopted) {
				endOfDayPetChange += "a pet was adopted for its enthusiasm\n";
			}
			if (wasAPetEuthanized) {
				endOfDayPetChange += "at least one pet had to be put down for its poor condition\n";
			}
			if (didAPetShowUpUnexpectedly) {
				endOfDayPetChange += "a pet arrived at our doorstep for adoption\n";
			}
		}
	}

	private void resetPetArrivalAndLeaveBooleans() {
		wasAPetEuthanized = false;
		wasAPetAdopted = false;
		didAPetShowUpUnexpectedly = false;
		endOfDayPetChange = "";
	}

	private void generateNewRandomPet() {
		String petType = generateRandomType();
		String petName = generateRandomPetName();
		String description = generateRandomDescription();
		takeInNewPet(petType, petName, description);
	}

	private String generateRandomType() {
		int randomType = (int) (Math.random() * 3);
		if (randomType == 0) {
			return "Dog";
		} else if (randomType == 1) {
			return "Cat";
		} else {
			return "Bird";
		}
	}

	private String generateRandomDescription() {
		int randomDescription = (int) (Math.random() * 3);
		if (randomDescription == 0) {
			return "Found on the street";
		} else if (randomDescription == 1) {
			return "Rescued from a cruel home";
		} else {
			return "Original owner could not care for this animal anymore";
		}
	}
	
	private void checkForAutoAdoptedPets(Collection<VirtualPet> allPets) {
		ArrayList<String> autoAdoptablePets = new ArrayList<>();
		for (VirtualPet virtualPet : allPets) {
			if (virtualPet.getBoredom() <= 0) {
				autoAdoptablePets.add(virtualPet.getName());
				wasAPetAdopted = true;
			}
		}
		for (String petName : autoAdoptablePets) {
			adopt(petName);
		}
	}

	private void checkUnCaredForPets(Collection<VirtualPet> allPets) {
		ArrayList<String> illPets = new ArrayList<>();
		for (VirtualPet virtualPet : allPets) {
			if (virtualPet.getIllness() > 50) {
				illPets.add(virtualPet.getName());
				wasAPetEuthanized = true;
			}
		}
		for (String petName : illPets) {
			shelteredPets.remove(petName);
		}
	}

	public void cleanShelter() {
		waste = -1;
	}

	private String generateRandomPetName() {
		int randomName = (int)(Math.random() * 25);
		
		switch (randomName) {
		case 0:	return "Alvin";
		case 1:	return "Buddy";
		case 2:	return "Cisco";
		case 3:	return "Diva";
		case 4:	return "Elwood";
		case 5:	return "Felix";
		case 6:	return "Goofy";
		case 7:	return "Honey";
		case 8:	return "Iris";
		case 9: return "Jeeves";
		case 10: return "Kassie";
		case 11: return "Lexi";
		case 12: return "Muggles";
		case 13: return "Nico";
		case 14: return "Otis";
		case 15: return "Pixie";
		case 16: return "Ramona";
		case 17: return "Shadow";
		case 18: return "Tatertot";
		case 19: return "Toffee";
		case 20: return "Vegas";
		case 21: return "Wilbur";
		case 22: return "Sweetpea";
		case 23: return "Lola";
		default: return "Zeke";
		}
	}
}

