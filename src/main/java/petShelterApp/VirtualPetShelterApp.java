package petShelterApp;

import java.util.Collection;
import java.util.Scanner;

import petShelter.*;

public class VirtualPetShelterApp {

	public static VirtualPetShelter hhShelter = new VirtualPetShelter();
	public static VPSAppMenus appMenus = new VPSAppMenus();
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {

		shelterStartUp();
		System.out.print(appMenus.displayWelcomeMenu());
		Thread.sleep(500);
		for (int i = 0; i < 4; i++) {
			Thread.sleep(500);
			System.out.print(".");
		}
		System.out.println("\n");
		Thread.sleep(2000);

		System.out.print(appMenus.displayStatusHelpMenu());
		System.out.print("This may lead to ");
		Thread.sleep(500);
		for (int i = 0; i < 4; i++) {
			Thread.sleep(500);
			System.out.print(".");
		}
		System.out.println("\n");
		Thread.sleep(2000);

		System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
		Thread.sleep(2000);

		System.out.println(appMenus.displayShortOptionsMenu());

		do {
			String userInputOptionsMenu = input.nextLine();

			boolean willExecuteTickAfterMenuInput = true;

			if (userInputOptionsMenu.equalsIgnoreCase("F") || userInputOptionsMenu.equalsIgnoreCase("FEED")) {
				hhShelter.feedAllPets();
				System.out.println("You feed all of the animals.  The sounds of animals munching fill the shelter.\n");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("W") || userInputOptionsMenu.equalsIgnoreCase("WATER")) {
				hhShelter.giveWaterToAllPets();
				System.out.println("You fill all of the water bowls.  The animals become content for a while.\n");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("P") || userInputOptionsMenu.equalsIgnoreCase("PLAY")) {
				System.out
						.println("Please state which pet you would like to play with\nPet names are Case-Sensitive\n");
				Collection<VirtualPet> allPets = hhShelter.getAllPets();
				for (VirtualPet virtualPet : allPets) {
					System.out.println(virtualPet.getName() + ": " + virtualPet.getDescription());
				}
				String petNameToPlayWith = verifyPetNameIsValid();
				hhShelter.playWith(petNameToPlayWith);
				VirtualPet petPlayedWith = hhShelter.retrievePetInfo(petNameToPlayWith);

				if (petPlayedWith instanceof VirtualDog) {
					boolean areThereOtherDogs = false;
					for (VirtualPet virtualPet : allPets) {
						if (virtualPet instanceof VirtualDog && !virtualPet.getName().equals(petNameToPlayWith)) {
							areThereOtherDogs = true;
						}
					}
					System.out.println(petNameToPlayWith + " runs around from the attention, chasing balls.");
					if (areThereOtherDogs) {
						System.out.println("While " + petNameToPlayWith
								+ " is happy, other dogs in the shelter seem to be dejected.\n");
					}
				} else if (petPlayedWith instanceof VirtualBird) {
					boolean isThereAtLeastACat = false;
					for (VirtualPet virtualPet : allPets) {
						if (virtualPet instanceof VirtualCat) {
							isThereAtLeastACat = true;
						}
					}
					System.out.println(petNameToPlayWith + " tweets loudly from the attention.");
					if (isThereAtLeastACat) {
						System.out.println("The cats begin to pace about their cages.\n");
					}
				} else {
					System.out.println(petNameToPlayWith + " purrs from the scritches.\n");
				}
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("V") || userInputOptionsMenu.equalsIgnoreCase("VET")) {
				System.out.println("Who needs the attention of the vet?\nWe can only get one treated\n");
				String petToGetVetTreatment = verifyPetNameIsValid();
				hhShelter.callVetFor(petToGetVetTreatment);
				System.out.println(petToGetVetTreatment + " seems to be doing much better.");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("C") || userInputOptionsMenu.equalsIgnoreCase("CLEAN")) {
				hhShelter.cleanShelter();
				System.out.println(
						"Cleaning the shelter is probably a good idea.  You don't want to let the waste pile up.\n");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("A") || userInputOptionsMenu.equalsIgnoreCase("ADOPT")) {
				System.out.println("Which pet will you try to get adopted today?\nPet names are Case-Sensitive\n");
				String petNameToAdopt = verifyPetNameIsValid();

				boolean wasPetAdopted = hhShelter.adopt(petNameToAdopt);
				if (wasPetAdopted) {
					System.out.println("Congratulations! " + petNameToAdopt + " was successfully adopted!");
				} else {
					System.out.println(
							"Unfortunately, it doesn't seem like anyone wanted to adopt " + petNameToAdopt + " today.");
				}

			} else if (userInputOptionsMenu.equalsIgnoreCase("B") || userInputOptionsMenu.equalsIgnoreCase("BRING")) {
				willExecuteTickAfterMenuInput = false;
				System.out.println("What type of pet do you want to bring in?  Dog, Cat, or Bird?");
				String petType = verifyPetTypeIsValid();

				System.out.println("What should the pet name be?");
				String petName = verifyPetNameDoesNotAlreadyExist();

				System.out.println("Please enter a short description for the new pet");
				String petDescription = input.nextLine();

				System.out.println("Would you like to enter starting atrributes for your new pet (Y/N)?");
				boolean userChoiceToEnterAtrributes = verifyUserChoiceToEnterAttributes();

				if (userChoiceToEnterAtrributes) {
					int hunger = collectAttributeChoice("hunger");
					int thirst = collectAttributeChoice("thirst");
					int boredom = collectAttributeChoice("boredom");
					hhShelter.takeInNewPet(petType, petName, petDescription, hunger, thirst, boredom);
				} else {
					hhShelter.takeInNewPet(petType, petName, petDescription);
				}
				System.out.println(petName + " has been brought into the shelter.\n");
				Thread.sleep(1000);
				System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("H") || userInputOptionsMenu.equalsIgnoreCase("HELP")) {
				willExecuteTickAfterMenuInput = false;
				System.out.println(appMenus.displayStatusHelpMenu());
				Thread.sleep(1000);
				System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("O") || userInputOptionsMenu.equalsIgnoreCase("OPTIONS")) {
				willExecuteTickAfterMenuInput = false;
				System.out.println(appMenus.displayOptionsMenu());
				continue;

			} else if (userInputOptionsMenu.equalsIgnoreCase("Q") || userInputOptionsMenu.equalsIgnoreCase("QUIT")) {
				break;

			} else {
				willExecuteTickAfterMenuInput = false;
				System.out.println("Unknown command.  Please review options and try again.\n");
				Thread.sleep(2000);
			}

			if (willExecuteTickAfterMenuInput) {
				String resultsOfTick = hhShelter.tick();
				System.out.println(resultsOfTick);
				Thread.sleep(2000);

				System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
				Thread.sleep(1000);
			}
			
			if (checkForGameEndingCondition()) {
				break;
			}

			System.out.println(appMenus.displayShortOptionsMenu());

		} while (true);

		System.out.println("You managed to volunteer for " + hhShelter.getDaysRunningTheShelter() + " days.");
		System.out.println(hhShelter.getAdoptionCount() + " animals were adopted as pets under your care.");
		System.out.println(hhShelter.getEuthanizedCount() + " animals had to be put down under your watch.\n");
		Thread.sleep(1000);
		if (hhShelter.getAdoptionCount() == 0 && hhShelter.getEuthanizedCount() == 0) {
			System.out.println("Thanks, I guess.  We'll take it from here.");
		} else if (hhShelter.getEuthanizedCount() >= 20) {
			System.out.println("You probably should reconsider working at a pet shelter.\n");
		} else if (hhShelter.getEuthanizedCount() > hhShelter.getAdoptionCount()) {
			System.out.println("You could have done better.  We didn't save as many as we had to put down.\n");
		} else if (hhShelter.getAdoptionCount() >= 20) {
			System.out.println("Great Work!  You found many animals a new home.\n");
		} else {
			System.out.println("You did well.  More animals were adopted than we had to put down.\n");
		}
		
		System.out.println("Thanks for your participation.  Have a good day.");

		input.close();
	}

	public static void shelterStartUp() {
		hhShelter.generateNewRandomPet();
		hhShelter.generateNewRandomPet();
		hhShelter.generateNewRandomPet();
	}

	public static String verifyPetNameIsValid() {
		String userEnteredPetName;
		boolean petNameInvalid;

		do {
			userEnteredPetName = input.nextLine();
			petNameInvalid = !hhShelter.checkIfPetNameExists(userEnteredPetName);
			if (petNameInvalid) {
				System.out.println("Pet Name does not exist - double-check spelling and capitalization.");
			}
		} while (petNameInvalid);

		return userEnteredPetName;
	}

	public static String verifyPetNameDoesNotAlreadyExist() {
		String userEnteredPetName;
		boolean petNameInvalid;

		do {
			userEnteredPetName = input.nextLine();
			petNameInvalid = hhShelter.checkIfPetNameExists(userEnteredPetName);
			if (petNameInvalid) {
				System.out.println("Pet Name already exists.  Please choose another.");
			}
		} while (petNameInvalid);

		return userEnteredPetName;
	}

	public static String verifyPetTypeIsValid() {
		String userEnteredPetType;
		boolean isPetTypeInvalid;
		do {
			userEnteredPetType = input.nextLine();
			isPetTypeInvalid = !(userEnteredPetType.equalsIgnoreCase("dog")
					|| userEnteredPetType.equalsIgnoreCase("cat") || userEnteredPetType.equalsIgnoreCase("bird"));
			if (isPetTypeInvalid) {
				System.out.println("Please choose dog, cat, or bird.  We really can't take care of other pets here.");
			}
		} while (isPetTypeInvalid);

		return userEnteredPetType;
	}

	public static boolean verifyUserChoiceToEnterAttributes() {
		String userChoiceToEnterAtrributes;
		boolean isChoiceInvalid = true;
		do {
			userChoiceToEnterAtrributes = input.nextLine();
			if (!(userChoiceToEnterAtrributes.equalsIgnoreCase("y")
					|| userChoiceToEnterAtrributes.equalsIgnoreCase("n"))) {
				System.out.println("Invalid choice.  Press \'Y\' or \'N\'\n");
			} else {
				isChoiceInvalid = false;
			}
		} while (isChoiceInvalid);
		if (userChoiceToEnterAtrributes.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int collectAttributeChoice(String attribute) {
		System.out.println("Which emote would you like for the " + attribute + " stat?");
		int userChoiceConvertedToInt = 0;
		boolean isChoiceInvalid; 
		do {
			isChoiceInvalid = false;
			System.out.println(":)\t:|\t:/\t:(\t:`(\n");
			String userChoiceForAttribute = input.nextLine();
			if (userChoiceForAttribute.equals(":)")) {
				userChoiceConvertedToInt = 8;
			} else if (userChoiceForAttribute.equals(":|")) {
				userChoiceConvertedToInt = 15;
			} else if (userChoiceForAttribute.equals(":/")) {
				userChoiceConvertedToInt = 22;
			} else if (userChoiceForAttribute.equals(":(")) {
				userChoiceConvertedToInt = 29;
			} else if (userChoiceForAttribute.equals(":`(")) {
				userChoiceConvertedToInt = 36;
			} else {
				System.out.println("Invalid choice - please review options for " + attribute + " stat.");
				isChoiceInvalid = true;
			}
		} while (isChoiceInvalid);
		return userChoiceConvertedToInt;
	}
	
	public static boolean checkForGameEndingCondition() {
		if (hhShelter.getDaysRunningTheShelter() == 100) {
			System.out.println("You've volunteered here for 100 days!\nNice work!  We have to give you a break now.");
			return true;
		}
		if (hhShelter.getEuthanizedCount() >= 20) {
			System.out.println("We're being shut down.  We've had to kill too many animals.");
			return true;
		}
		Collection<VirtualPet> allPets = hhShelter.getAllPets();
		if (allPets.isEmpty()) {
			System.out.println("There are no more pets in the Shelter.");
			return true;
		}
		return false;
	}

}
