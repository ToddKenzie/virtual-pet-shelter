package petShelter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VirtualPetShelterTest {
	
	
	@Test
	public void verifyPetNameInShelterIsWoof() {
		VirtualPetShelter underTest = new VirtualPetShelter();
		VirtualPet testDog = new VirtualPet("Woof", "description");
		underTest.intakeNewPet(testDog);
		String petName = underTest.retrievePetInfo(testDog);
		assertThat(petName, is("Woof"));
	}

}
