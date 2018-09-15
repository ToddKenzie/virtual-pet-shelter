package petShelter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VirtualPetShelter {

	private Waste waste;
	private Map<String, VirtualPet> shelteredPets = new HashMap<>();
	private VirtualPetGenerator petGenerator;
	private int daysRunningTheShelter;
	private RandomPetInfo randomPet;

	private boolean wasAPetEuthanized;
	private boolean wasAPetAdopted;
	private boolean didAPetShowUpUnexpectedly;
	private String endOfDayPetChange;

	public VirtualPetShelter() {
		this.waste = new Waste(0);
		daysRunningTheShelter = 0;
		randomPet = new RandomPetInfo();
	}

	public String getEndOfDayPetChange() {
		return endOfDayPetChange;
	}
	
	public int getDaysRunningTheShelter() {
		return daysRunningTheShelter;
	}
	
	public Waste getWaste() {
		return waste;
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

	public boolean takeInNewPet(String petType, String petName, String description) {
		Set<String> allPetNames = shelteredPets.keySet();
		if (!allPetNames.contains(petName)) {
			petGenerator = new VirtualPetGenerator();
			VirtualPet newPet = petGenerator.createPet(petType, petName, description);
			takeInNewPet(newPet);
			return true;
		}
		return false;
	}

	public boolean takeInNewPet(String petType, String petName, String description, int hunger, int thirst, int boredom) {
		Set<String> allPetNames = shelteredPets.keySet();
		if (!allPetNames.contains(petName)) {
			petGenerator = new VirtualPetGenerator();
			VirtualPet newPet = petGenerator.createPet(petType, petName, description, hunger, thirst, boredom);
			takeInNewPet(newPet);
			return true;
		}
		return false;
	}

	public boolean adopt(String petName) {
		if(retrievePetInfo(petName).getIllness() < 30) {
			shelteredPets.remove(petName);
			return true;
		}
		return false;
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
		waste.increaseValue(1);
		daysRunningTheShelter++;
		adjustPetPopulationAndStats();
		checkPetArrivalAndLeaveBooleans();
	}
	
	private void resetPetArrivalAndLeaveBooleans() {
		wasAPetEuthanized = false;
		wasAPetAdopted = false;
		didAPetShowUpUnexpectedly = false;
		endOfDayPetChange = "";
	}
	
	private void adjustPetPopulationAndStats() {
		Collection<VirtualPet> allPets = getAllPets();
		checkForAutoAdoptedPets(allPets);
		for (VirtualPet virtualPet : allPets) {
			virtualPet.tick(waste.getValue());
		}
		checkUnCaredForPets(allPets);
		generateNewRandomPet();
	}

	private void checkPetArrivalAndLeaveBooleans() {
		endOfDayPetChange = "At the end of the day\n";
		if (wasAPetAdopted || wasAPetEuthanized || didAPetShowUpUnexpectedly) {
			if (wasAPetAdopted) {
				endOfDayPetChange += "a pet was adopted for its enthusiasm\n";
			}
			if (wasAPetEuthanized) {
				endOfDayPetChange += "at least one pet had to be put down for its poor condition\n";
			}
			if (didAPetShowUpUnexpectedly) {
				endOfDayPetChange += "a pet arrived at our doorstep for adoption\n";
			}
		} else {
			endOfDayPetChange += "nothing of note occured\n";
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
		ArrayList<String> illCaredForPets = new ArrayList<>();
		for (VirtualPet virtualPet : allPets) {
			if (virtualPet.getIllness() >= 50 || virtualPet.getHunger() >= 50 || virtualPet.getThirst() >= 50) {
				illCaredForPets.add(virtualPet.getName());
				wasAPetEuthanized = true;
			}
		}
		for (String petName : illCaredForPets) {
			shelteredPets.remove(petName);
		}
	}

	private void generateNewRandomPet() {
		if (this.daysRunningTheShelter % 7 == 0) {
			didAPetShowUpUnexpectedly = true;
			Set<String> allPetNames = shelteredPets.keySet();
			String petType = randomPet.generateType();
			String petName = randomPet.generateName(allPetNames);
			String description = randomPet.generateDescription();
			takeInNewPet(petType, petName, description);
		}
	}
	
	public void cleanShelter() {
		waste.setToZero();
	}

}

