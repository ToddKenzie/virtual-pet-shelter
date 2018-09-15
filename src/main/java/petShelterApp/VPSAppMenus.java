package petShelterApp;

import java.util.Collection;

import petShelter.VirtualPet;
import petShelter.VirtualPetShelter;

public class VPSAppMenus {
	
	public String displayWelcomeMenu() {
		String welcomeMenu = "Welcome to the Happy Home Shelter!\nThank you for volunteering your time.\n" +
		"You will be responsible for all operations here in the shelter\n" +
		"Try to get as many pets adopted as you can.\nKeep in mind that we do receive random pets from time to time.\n" +
		"Also, keep care of the pets we have.  Without care, we may have no choice but to ";
		return welcomeMenu;
	}
	
	public String displayStatusHelpMenu() {
		String statusDisplayHelpMenu = "Well-being is graded on the following scale:\n" +
		":D\t:)\t:|\t:/\t:(\t:`(\tX(\n" +
		"Emotes on the left are AWESOME!  They help drive adoptability.\n" +
		"Emotes on the right are BAD!  It means we're not taking care of what we have.\n";
		return statusDisplayHelpMenu;
	}
	
	public String displayShelterStatusMenu(VirtualPetShelter shelter) {
		String shelterStatusMenu = "Here's our current pet roster:\n" +
		"Type\t|Hunger\t|Thirst\t|Bored\t|Health\t|Name\n";
		Collection<VirtualPet> currentPetsAtShelter = shelter.getAllPets();
		for (VirtualPet virtualPet : currentPetsAtShelter) {
			shelterStatusMenu += virtualPet + "\n";
		}
		shelterStatusMenu += "\nCurrent Cleanliness of the shelter: " + shelter.getWaste() + "\n";
		return shelterStatusMenu;
	}
	
	public String displayOptionsMenu() {
		String optionsMenu = "\'F\' to Feed all of the pets in the shelter\n" +
			"Press \'W\' to give water to all of the pets in the shelter\n" +
			"Press \'P\' to play with one pet of your choosing in the shelter\n" +
			"Press \'V\' to call the vet to treat one pet in the shelter\n" +
			"Press \'C\' to take time and clean the shelter from top to bottom\n" +
			"Press \'A\' to try and have a pet adopted from the shelter\n" +
			"Press \'B\' to bring a new pet into the shelter\n" +
			"Press \'H\' to bring back the Status Help menu\n" +
			"Press \'Q\' to quit volunteering here at the Happy Home Shelter.\n";
		return optionsMenu;
	}

	public String displayShortOptionsMenu() {
		String optionsMenu = "What would you like to do today?\n" +
			"\'F\' to FEED all \t" +
			"\'W\' give WATER to all \n" +
			"\'P\' PLAY with one \t" +
			"\'V\' call the VET\n" +
			"\'C\' CLEAN shelter \t" +
			"\'A\' attempt ADOPTion \n" +
			"\'B\' BRING animal in\t" +
			"\'H\' Status HELP menu\n" +
			"\'O\' explain all OPTIONS\t" +
			"\'Q\' to QUIT volunteering \n";
		return optionsMenu;
	}
	
	public String displayEndMessage(VirtualPetShelter shelter) {
		String gameEndMessage ="You managed to volunteer for " + shelter.getDaysRunningTheShelter() + " days.\n" +
		shelter.getAdoptionCount() + " animals were adopted as pets under your care.\n" +
		shelter.getEuthanizedCount() + " animals had to be put down under your watch.\n\n";
		if (shelter.getAdoptionCount() == 0 && shelter.getEuthanizedCount() == 0) {
			gameEndMessage += "Thanks, I guess.  We'll take it from here.\n";
		} else if (shelter.getEuthanizedCount() >= 10) {
			gameEndMessage += "You probably should reconsider working at a pet shelter.\n";
		} else if (shelter.getEuthanizedCount() > shelter.getAdoptionCount()) {
			gameEndMessage += "You could have done better.  We didn't save as many as we had to put down.\n";
		} else if (shelter.getAdoptionCount() >= 10) {
			gameEndMessage += "Great Work!  You found many animals a new home.\n";
		} else if (shelter.getEuthanizedCount() != 0){
			gameEndMessage += "You did well.  More animals were adopted than we had to put down.\n";
		} else {
			gameEndMessage += "You did good.  We didn't have to put a single animal down.\n";
		}
		return gameEndMessage;
	}

}
