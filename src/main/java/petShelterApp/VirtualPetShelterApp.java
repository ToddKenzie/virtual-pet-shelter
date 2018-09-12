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
		System.out.println("Well-being is graded on the following scale:");
		System.out.println(":D\t:)\t:|\t:/\t:(\t:`(\tX(");
		System.out.println("Emotes on the left are AWESOME!  They help drive adoptability.");
		System.out.println("Emotes on the right are BAD!  It means we're not taking care of what we have.\nThis may lead to other measures.\n");
		
		System.out.println("Here's our current pet roster:");
		System.out.println("Type\t|Hunger\t|Thirst\t|Bored\t|Health\t|Name");
		Collection<VirtualPet> currentPetsAtShelter = hhShelter.getAllPets();
		for (VirtualPet virtualPet : currentPetsAtShelter) {
			System.out.println(virtualPet);
		}
		System.out.println("\nCurrent Cleanliness of the shelter: " + hhShelter.getWaste());
	}

	
	public static void shelterStartUp() {
		hhShelter.takeInNewPet("Dog", "Buddy", "Likes to chase the ball");
		hhShelter.takeInNewPet("Cat", "Ms Kitty", "preens herself all day");
		hhShelter.takeInNewPet("Dog", "Rover", "Sleeps most of the day");
	}
	
	
}
