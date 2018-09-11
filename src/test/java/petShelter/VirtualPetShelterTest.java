package petShelter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {
	
	VirtualPetShelter underTest = new VirtualPetShelter();
	VirtualPet testDog = new VirtualPet("Woof", "happy");
	VirtualPet testCat = new VirtualPet("Meow", "cute", 20, 15, 10);
	
	@Before
	public void setUp() {
		underTest.intakeNewPet(testDog);
		underTest.intakeNewPet(testCat);
	}
	
	@Test
	public void verifyPetNameInShelterIsWoof() {
		VirtualPet recalledPet = underTest.retrievePetInfo("Woof");
		assertThat(recalledPet, is(testDog));
	}

	@Test
	public void verifyPetNameInShelterIsMeow() {
		VirtualPet recalledPet = underTest.retrievePetInfo("Meow");
		assertThat(recalledPet, is(testCat));
	}
	
	@Test
	public void verifyAPetNameInShelterIsWoof() {
		VirtualPet recalledPet = underTest.retrievePetInfo("Woof");
		assertThat(recalledPet, is(testDog));
	}
	
	@Test
	public void verifyWoofDescriptionIsHappy() {
		VirtualPet recalledPet = underTest.retrievePetInfo("Woof");
		String description = recalledPet.getDescription();
		assertThat(description, is("happy"));
	}
	
	@Test
	public void verifyAllPetsHaveBeenFedFrom15to10And20to15() {
		underTest.feedAllPets();
		int dogFed = testDog.getHunger();
		int catFed = testCat.getHunger();
		assertThat(dogFed, is(10));
		assertThat(catFed, is(15));
	}
	
	@Test
	public void verifyAllPetsHaveBeenGivenWaterFrom20to15And15to10() {
		underTest.giveWaterToAllPets();
		int dogThirst = testDog.getThirst();
		int catThirst = testCat.getThirst();
		assertThat(dogThirst, is(15));
		assertThat(catThirst, is(10));
	}
	
	@Test
	public void verifyOnlyOnePetIsPlayedWithFrom25to20And10to10() {
		underTest.playWith("Woof");
		int dogBored = testDog.getBoredom();
		int catBored = testCat.getBoredom();
		assertThat(dogBored, is(20));
		assertThat(catBored, is(10));
	}
	


}
