package petShelter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {
	
	VirtualPetShelter underTest = new VirtualPetShelter();
	VirtualPet testDog; 
	VirtualPet testCat; 
	VirtualPet testBird;
	
	@Before
	public void setUp() {
		underTest.takeInNewPet("dog", "Woof", "happy");
		underTest.takeInNewPet("cat", "Meow", "cute", 20, 15, 10);
		underTest.takeInNewPet("bird", "Tweet", "description");
		testDog = underTest.retrievePetInfo("Woof");
		testCat = underTest.retrievePetInfo("Meow");
		testBird = underTest.retrievePetInfo("Tweet");
	}
	
	@Test
	public void verifyPetNameInShelterIsMeow() {
		VirtualPet recalledPet = underTest.retrievePetInfo("Meow");
		String name = recalledPet.getName();
		assertThat(name, is("Meow"));
	}
	
	@Test
	public void verifyAPetNameInShelterIsWoof() {
		VirtualPet recalledPet = underTest.retrievePetInfo("Woof");
		String name = recalledPet.getName();
		assertThat(name, is("Woof"));
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
	public void verifyOnlyOnePetIsPlayedWithFrom25to15And10to10() {
		underTest.playWith("Woof");
		int dogBored = testDog.getBoredom();
		int catBored = testCat.getBoredom();
		assertThat(dogBored, is(15));
		assertThat(catBored, is(10));
	}
	
	@Test
	public void verifyOnlyOnePetisTreatedByVetFrom30to30And20to5() {
		underTest.callVetFor("Meow");
		int dogIllness = testDog.getIllness();
		int catIllness = testCat.getIllness();
		assertThat(dogIllness, is(30));
		assertThat(catIllness, is(5));
	}
	
	@Test
	public void checkAllIllnessIncreasesBy1forFirstTick30to31And20to21() {
		underTest.tick();
		int dogIllness = testDog.getIllness();
		int catIllness = testCat.getIllness();
		assertThat(dogIllness, is(31));
		assertThat(catIllness, is(21));
	}
	
	@Test
	public void checkAllIllnessIncreasesBy3for2Ticks30to33And20to23() {
		underTest.tick();
		underTest.tick();
		int dogIllness = testDog.getIllness();
		int catIllness = testCat.getIllness();
		assertThat(dogIllness, is(33));
		assertThat(catIllness, is(23));
	}
	
	@Test
	public void checkAllIllnessIncreasesBy1for2Ticksand1CleanShelter30to31And20to21() {
		underTest.tick();
		underTest.cleanShelter();
		underTest.tick();
		int dogIllness = testDog.getIllness();
		int catIllness = testCat.getIllness();
		assertThat(dogIllness, is(31));
		assertThat(catIllness, is(21));
	}
	
	@Test
	public void checkOtherDogsGetJealousWhenDogIsPlayedWith25to28CatShouldStay10() {
		underTest.takeInNewPet("dog", "dog2", "description");
		underTest.playWith("Woof");
		VirtualPet dog2 = underTest.retrievePetInfo("dog2");
		int dog2Boredom = dog2.getBoredom();
		int catBoredom = testCat.getBoredom();
		int dogBored = testDog.getBoredom();
		assertThat(dog2Boredom, is(28));
		assertThat(catBoredom, is(10));
		assertThat(dogBored, is(15));
	}
	
	@Test
	public void checkDogThatGetsPlayedWithIncreasesThirstFrom20to23() {
		underTest.playWith("Woof");
		int dogThirst = testDog.getThirst();
		assertThat(dogThirst, is(23));
	}
	
	@Test
	public void checkCatsGetHungrierWhenYouPlayWithBird20to23() {
		underTest.playWith("Tweet");
		int catHunger = testCat.getHunger();
		int dogHunger = testDog.getHunger();
		assertThat(catHunger, is(23));
		assertThat(dogHunger, is(15));
	}
	
	@Test
	public void adoptPetShouldRemovePetFromShelter() {
		underTest.adopt("Woof");
		VirtualPet check = underTest.retrievePetInfo("Woof");
		assertThat(check, is(nullValue()));
	}
	
	@Test
	public void removeDogWhenIllnessExceeds50() {
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		VirtualPet checkDog = underTest.retrievePetInfo("Woof");
		VirtualPet checkCat = underTest.retrievePetInfo("Meow");
		VirtualPet checkBird = underTest.retrievePetInfo("Tweet");
		assertThat(checkDog, is(nullValue()));
		assertThat(checkCat, is(testCat));
		assertThat(checkBird, is(testBird));
	}


}
