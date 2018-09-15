package petShelter;

import java.util.ArrayList;
import java.util.Set;

public class RandomPetInfo {

	ArrayList<String> petNames;
	
	protected RandomPetInfo() {
		
		petNames = new ArrayList<>();
		petNames.add("Alvin");
		petNames.add("Buddy");
		petNames.add("Cisco");
		petNames.add("Diva");
		petNames.add("Elwood");
		petNames.add("Felix");
		petNames.add("Goofy");
		petNames.add("Honey");
		petNames.add("Iris");
		petNames.add("Jeeves");
		petNames.add("Kassie");
		petNames.add("Lexi");
		petNames.add("Muggles");
		petNames.add("Nico");
		petNames.add("Otis");
		petNames.add("Pixie");
		petNames.add("Ramona");
		petNames.add("Shadow");
		petNames.add("Tatertot");
		petNames.add("Toffee");
		petNames.add("Vegas");
		petNames.add("Wilbur");
		petNames.add("Sweetpea");
		petNames.add("Lola");
		petNames.add("Zeke");
	}
	
	protected String generateType() {
		int randomType = (int) (Math.random() * 3);
		if (randomType == 0) {
			return "Dog";
		} else if (randomType == 1) {
			return "Cat";
		} else {
			return "Bird";
		}
	}
	
	protected String generateName(Set<String> allPetNames) {
		int randomNumber;
		String petName;
		do {
			randomNumber = (int)(Math.random() * petNames.size());
			petName = petNames.get(randomNumber);
		} while (allPetNames.contains(petName));
		petNames.remove(randomNumber);
		return petName;
	}
	
	protected String generateDescription() {
		int randomDescription = (int) (Math.random() * 3);
		if (randomDescription == 0) {
			return "Found on the street";
		} else if (randomDescription == 1) {
			return "Rescued from a cruel home";
		} else {
			return "Original owner could not care for this animal anymore";
		}
	}
}
