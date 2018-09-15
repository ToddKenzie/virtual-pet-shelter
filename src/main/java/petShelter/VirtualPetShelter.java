package petShelter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VirtualPetShelter {

	private VirtualPetGenerator petGenerator;
	private RandomPetInfo randomPet;
	private Map<String, VirtualPet> shelteredPets = new HashMap<>();
	private Waste waste;
	private int daysRunningTheShelter;
	private int adoptionCount;
	private int euthanizedCount;
	private int nextUnexpectedArrivalDate;

	private boolean wasAPetEuthanized;
	private boolean wasAPetAdopted;
	private boolean didAPetShowUpUnexpectedly;

	public VirtualPetShelter() {
		petGenerator = new VirtualPetGenerator();
		randomPet = new RandomPetInfo();
		waste = new Waste(0);
		daysRunningTheShelter = 0;
		adoptionCount = 0;
		euthanizedCount = 0;
		nextUnexpectedArrivalDate = 7;
	}

	public Waste getWaste() {
		return waste;
	}
	
	public int getDaysRunningTheShelter() {
		return daysRunningTheShelter;
	}
	
	public int getAdoptionCount() {
		return adoptionCount;
	}

	public int getEuthanizedCount() {
		return euthanizedCount;
	}
	
	public VirtualPet retrievePetInfo(String petName) {
		return shelteredPets.get(petName);
	}

	public Collection<VirtualPet> getAllPets() {
		return shelteredPets.values();
	}
	
	public boolean checkIfPetNameExists(String petName) {
		Set<String> allPetNames = shelteredPets.keySet();
		return allPetNames.contains(petName);
	}

	private void takeInNewPet(VirtualPet newPet) {
		shelteredPets.put(newPet.getName(), newPet);
	}

	public boolean takeInNewPet(String petType, String petName, String description) {
		if (!checkIfPetNameExists(petName)) {
			VirtualPet newPet = petGenerator.createPet(petType, petName, description);
			takeInNewPet(newPet);
			return true;
		}
		return false;
	}

	public boolean takeInNewPet(String petType, String petName, String description, int hunger, int thirst, int boredom) {
		if (!checkIfPetNameExists(petName)) {
			VirtualPet newPet = petGenerator.createPet(petType, petName, description, hunger, thirst, boredom);
			takeInNewPet(newPet);
			return true;
		}
		return false;
	}

	public boolean adopt(String petName) {
		if(retrievePetInfo(petName).getIllness() < 30) {
			shelteredPets.remove(petName);
			adoptionCount++;
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

	public void cleanShelter() {
		waste.decreaseValue();
	}
	
	public String tick() {
		resetPetArrivalAndLeaveBooleans();
		waste.increaseValue(1);
		daysRunningTheShelter++;
		adjustPetPopulationAndStats();
		return checkPetArrivalAndLeaveBooleans();
	}
	
	private void resetPetArrivalAndLeaveBooleans() {
		wasAPetEuthanized = false;
		wasAPetAdopted = false;
		didAPetShowUpUnexpectedly = false;
	}
	
	private void adjustPetPopulationAndStats() {
		Collection<VirtualPet> allPets = getAllPets();
		removeAutoAdoptedPets(allPets);
		increaseIllnessDueToWasteLevel(allPets);
		removePoorCarePets(allPets);
		checkForNewArrivalPet();
	}

	private void removeAutoAdoptedPets(Collection<VirtualPet> allPets) {
		ArrayList<String> autoAdoptablePets = findAutoAdoptablePets(allPets);
		for (String petName : autoAdoptablePets) {
			adopt(petName);
			wasAPetAdopted = true;
		}
	}
	
	private ArrayList<String> findAutoAdoptablePets(Collection<VirtualPet> allPets) {
		ArrayList<String> autoAdoptablePets = new ArrayList<>();
		for (VirtualPet virtualPet : allPets) {
			if (virtualPet.getBoredom() <= 0 || (virtualPet.getHunger() <= 10 && virtualPet.getThirst() <= 10 && virtualPet.getIllness() <= 10)) {
				autoAdoptablePets.add(virtualPet.getName());
			}
		}
		return autoAdoptablePets;
	}

	private void increaseIllnessDueToWasteLevel(Collection<VirtualPet> allPets) {
		for (VirtualPet virtualPet : allPets) {
			virtualPet.tick(waste.getValue());
		}
	}
	
	private void removePoorCarePets(Collection<VirtualPet> allPets) {
		ArrayList<String> poorCarePets = findPoorCarePets(allPets);
		for (String petName : poorCarePets) {
			shelteredPets.remove(petName);
			euthanizedCount++;
			wasAPetEuthanized = true;
		}
	}
	
	private ArrayList<String> findPoorCarePets(Collection<VirtualPet> allPets) {
		ArrayList<String> poorCarePets = new ArrayList<>();
		for (VirtualPet virtualPet : allPets) {
			if (virtualPet.getIllness() >= 50 || virtualPet.getHunger() >= 50 || virtualPet.getThirst() >= 50) {
				poorCarePets.add(virtualPet.getName());
			}
		}
		return poorCarePets;
	}

	private void checkForNewArrivalPet() {
		if (daysRunningTheShelter == nextUnexpectedArrivalDate) {
			didAPetShowUpUnexpectedly = true;
			generateNewRandomPet();
			nextUnexpectedArrivalDate = generateNextArrivalDate();
		}
	}
	
	public void generateNewRandomPet() {
		Set<String> allPetNames = shelteredPets.keySet();
		String petType = randomPet.generateType();
		String petName = randomPet.generateName(allPetNames);
		String description = randomPet.generateDescription();
		takeInNewPet(petType, petName, description);
	}
	
	private int generateNextArrivalDate() {
		int additionalDays = (int)(Math.random() * 3 + 4);
		return daysRunningTheShelter + additionalDays;
	}
	
	private String checkPetArrivalAndLeaveBooleans() {
		String endOfDayPetChangeMessage = "At the end of the day\n";
		if (wasAPetAdopted || wasAPetEuthanized || didAPetShowUpUnexpectedly) {
			if (wasAPetAdopted) {
				endOfDayPetChangeMessage += "A pet was adopted for its good conditions!\n";
			}
			if (wasAPetEuthanized) {
				endOfDayPetChangeMessage += "At least one pet had to be put down for its poor condition\n";
			}
			if (didAPetShowUpUnexpectedly) {
				endOfDayPetChangeMessage += "A pet arrived at our doorstep for adoption\n";
			}
		} else {
			endOfDayPetChangeMessage += "Nothing of note occured\n";
		}
		return endOfDayPetChangeMessage;
	}

	
}

