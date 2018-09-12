package petShelterApp;

import java.util.Collection;

import petShelter.*;

public class VirtualPetShelterApp {

	public static VirtualPetShelter hhShelter = new VirtualPetShelter();

	public static void main(String[] args) {

		shelterStartUp();
		System.out.println("Welcome to the Happy Home Shelter!\nThank you for volunteering your time.");
		System.out.println("You will be responsible for all operations here in the shelter");
		System.out.println("Try to get as many pets adopted as you can.\nKeep in mind that we do receive random pets from time to time.\n");
		System.out.println("Low numbers are AWESOME!  They help drive adoptability.");
		System.out.println("High numbers are BAD!  It means we're not taking care of what we have.\nThis may lead to other measures.\n");
		
		System.out.println("Here's our current pet roster:");
		System.out.println("Type\t|Hunger\t|Thirst\t|Bored\t|Health\t|Name");
		Collection<VirtualPet> currentPetsAtShelter = hhShelter.getAllPets();
		for (VirtualPet virtualPet : currentPetsAtShelter) {
			System.out.println(virtualPet);
		}
		System.out.println("\nCurrent Cleanliness of the shelter: " + getHealthOfShelter());
	}

	
	public static void shelterStartUp() {
		hhShelter.takeInNewPet("Dog", "Buddy", "Likes to chase the ball");
		hhShelter.takeInNewPet("Cat", "Ms Kitty", "preens herself all day");
		hhShelter.takeInNewPet("Dog", "Rover", "Sleeps most of the day");
	}
	
	public static String getHealthOfShelter() {
		String cleanliness = "";
		int wasteLevel = hhShelter.getWaste();
		if (wasteLevel == 0) {
			cleanliness = "Spotless!";
		} else if (wasteLevel <= 2) {
			cleanliness = "Clean";
		} else if (wasteLevel <= 4) {
			cleanliness = "Dirty";
		} else if (wasteLevel <= 6) {
			cleanliness = "Awful!";
		} else {
			cleanliness = "Health Hazard!!!";
		}
		return cleanliness;
				
		
	}
	
}
