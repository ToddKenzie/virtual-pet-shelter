package petShelter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;

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
	
	@Test
	public void verifyDogStatsIncreaseOnTickH15To18T20To24B25To28() {
		underTest.tick();
		int hunger = testDog.getHunger();
		int thirst = testDog.getThirst();
		int boredom = testDog.getBoredom();
		assertThat(hunger, is(18));
		assertThat(thirst, is(24));
		assertThat(boredom, is(28));
	}
	
	@Test
	public void verifyCatStatsIncreaseOnTickH20to22T15to18B10to12() {
		underTest.tick();
		int hunger = testCat.getHunger();
		int thirst = testCat.getThirst();
		int boredom = testCat.getBoredom();
		assertThat(hunger, is(22));
		assertThat(thirst, is(18));
		assertThat(boredom, is(12));
	}
	
	@Test
	public void verifyBirdStatsIncreaseOnTickH15to17T25to27B15to18() {
		underTest.tick();
		int hunger = testBird.getHunger();
		int thirst = testBird.getThirst();
		int boredom = testBird.getBoredom();
		assertThat(hunger, is(17));
		assertThat(thirst, is(27));
		assertThat(boredom, is(18));
	}
	
	@Test
	public void verifyThatABoredom0PetWillBeAutoAdoptedOnTick() {
		underTest.playWith("Meow");
		underTest.playWith("Meow");
		underTest.tick();
		VirtualPet checkCat = underTest.retrievePetInfo("Meow");
		VirtualPet checkDog = underTest.retrievePetInfo("Woof");
		assertThat(checkCat, is(nullValue()));
		assertThat(checkDog, is(testDog));
	}
	
	@Test
	public void checkThatHungerCannotGoNegativeMin0() {
		underTest.feedAllPets();
		underTest.feedAllPets();
		underTest.feedAllPets();
		underTest.feedAllPets();
		int hunger = testDog.getHunger();
		assertThat(hunger, is(0));
	}

	@Test
	public void checkThatThirstCannotGoNegativeMin0() {
		underTest.giveWaterToAllPets();
		underTest.giveWaterToAllPets();
		underTest.giveWaterToAllPets();
		underTest.giveWaterToAllPets();
		int thirst = testCat.getThirst();
		assertThat(thirst, is(0));
	}
	
	@Test
	public void checkThatIllnessCannotGoNegativeMin0() {
		underTest.callVetFor("Meow");
		underTest.callVetFor("Meow");
		int illness = testCat.getIllness();
		assertThat(illness, is(0));
	}
	
	@Test
	public void checkThatNewPetIsAddedEvery7Ticks() {
		underTest.tick();
		underTest.tick();
		underTest.callVetFor("Woof"); //needed to prevent Illness > 50 removal
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		Collection<VirtualPet> testTotal = underTest.getAllPets();		
		int totalNumber = testTotal.size();
		assertThat(totalNumber, is(4));
	}
	
	@Test
	public void removePetWhenHungerExceeds50 () {
		underTest.takeInNewPet("Dog", "Scruff", "description", 49, 10, 10);
		underTest.tick();
		VirtualPet checkScruff = underTest.retrievePetInfo("Scruff");
		assertThat(checkScruff, is(nullValue()));
	}

	@Test
	public void removePetWhenThirstExceeds50 () {
		underTest.takeInNewPet("Dog", "Scruff", "description", 10, 49, 10);
		underTest.tick();
		VirtualPet checkScruff = underTest.retrievePetInfo("Scruff");
		assertThat(checkScruff, is(nullValue()));
	}


	
	
}
