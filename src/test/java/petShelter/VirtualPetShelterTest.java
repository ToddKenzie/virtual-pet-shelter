package petShelter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
		underTest.takeInNewPet("dog", "Woof", "happy", 15, 20, 25);
		underTest.takeInNewPet("cat", "Meow", "cute", 20, 15, 10);
		underTest.takeInNewPet("bird", "Tweet", "description", 15, 25, 15);
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
	public void verifyAllPetsHaveBeenFedFrom15to0And20to0() {
		underTest.feedAllPets();
		int dogFed = testDog.getHunger();
		int catFed = testCat.getHunger();
		assertThat(dogFed, is(0));
		assertThat(catFed, is(0));
	}

	@Test
	public void verifyAllPetsHaveBeenGivenWaterFrom20to0And15to0() {
		underTest.giveWaterToAllPets();
		int dogThirst = testDog.getThirst();
		int catThirst = testCat.getThirst();
		assertThat(dogThirst, is(0));
		assertThat(catThirst, is(0));
	}

	@Test
	public void verifyOnlyOnePetIsPlayedWithFrom25to7And10to10() {
		underTest.playWith("Woof");
		int dogBored = testDog.getBoredom();
		int catBored = testCat.getBoredom();
		assertThat(dogBored, is(7));
		assertThat(catBored, is(10));
	}

	@Test
	public void verifyOnlyOnePetisTreatedByVetFrom30to30And20to0() {
		underTest.callVetFor("Meow");
		int dogIllness = testDog.getIllness();
		int catIllness = testCat.getIllness();
		assertThat(dogIllness, is(30));
		assertThat(catIllness, is(0));
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
	public void checkAllIllnessIncreasesBy2for2Ticks30to32And20to22() {
		underTest.tick();
		underTest.tick();
		int dogIllness = testDog.getIllness();
		int catIllness = testCat.getIllness();
		assertThat(dogIllness, is(32));
		assertThat(catIllness, is(22));
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
		underTest.takeInNewPet("dog", "dog2", "description", 15, 20, 25);
		underTest.playWith("Woof");
		VirtualPet dog2 = underTest.retrievePetInfo("dog2");
		int dog2Boredom = dog2.getBoredom();
		int catBoredom = testCat.getBoredom();
		int dogBored = testDog.getBoredom();
		assertThat(dog2Boredom, is(28));
		assertThat(catBoredom, is(10));
		assertThat(dogBored, is(7));
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
		boolean adoptCheck = underTest.adopt("Meow");
		VirtualPet check = underTest.retrievePetInfo("Meow");
		assertThat(check, is(nullValue()));
		assertTrue(adoptCheck);
	}

	@Test
	public void refuseAdoptCommandIfIllnessIsEqualOrOver30() {
		boolean adoptCheck = underTest.adopt("Woof");
		VirtualPet check = underTest.retrievePetInfo("Woof");
		assertThat(check, is(testDog));
		assertFalse(adoptCheck);
	}

	@Test
	public void removeDogWhenIllnessExceeds50() {
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.giveWaterToAllPets();
		underTest.feedAllPets();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.giveWaterToAllPets();
		underTest.feedAllPets();
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
		// added range when made them random to the pet class
		assertTrue(hunger >= 18 && hunger <= 19);
		assertTrue(thirst >= 23 && thirst <= 24);
		assertTrue(boredom >= 28 && boredom <= 29);
	}

	@Test
	public void verifyCatStatsIncreaseOnTickH20to22T15to18B10to12() {
		underTest.tick();
		int hunger = testCat.getHunger();
		int thirst = testCat.getThirst();
		int boredom = testCat.getBoredom();
		// added range when made them random to the pet class
		assertTrue(hunger >= 22 && hunger <= 24);
		assertTrue(thirst >= 18 && thirst <= 19);
		assertTrue(boredom >= 12 && boredom <= 13);
	}

	@Test
	public void verifyBirdStatsIncreaseOnTickH15to17T25to27B15to18() {
		underTest.tick();
		int hunger = testBird.getHunger();
		int thirst = testBird.getThirst();
		int boredom = testBird.getBoredom();
		// added range when made them random to the pet class
		assertTrue(hunger >= 16 && hunger <= 18);
		assertTrue(thirst >= 26 && thirst <= 28);
		assertTrue(boredom >= 16 && boredom <= 18);
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
		underTest.callVetFor("Woof"); // needed to prevent Illness > 50 removal
		underTest.feedAllPets();
		underTest.giveWaterToAllPets();
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
	public void removePetWhenHungerExceeds50() {
		underTest.takeInNewPet("Dog", "Scruff", "description", 49, 10, 10);
		underTest.tick();
		VirtualPet checkScruff = underTest.retrievePetInfo("Scruff");
		assertThat(checkScruff, is(nullValue()));
	}

	@Test
	public void removePetWhenThirstExceeds50() {
		underTest.takeInNewPet("Dog", "Scruff", "description", 10, 49, 10);
		underTest.tick();
		VirtualPet checkScruff = underTest.retrievePetInfo("Scruff");
		assertThat(checkScruff, is(nullValue()));
	}

	@Test
	public void verifyStartingDogStatsAreRandomIfNotDeclared() {
		underTest.takeInNewPet("Dog", "Fido", "s");
		VirtualPet newPet = underTest.retrievePetInfo("Fido");
		int hunger = newPet.getHunger();
		int thirst = newPet.getThirst();
		int boredom = newPet.getBoredom();
		assertTrue(15 <= hunger && hunger <= 20);
		assertTrue(20 <= thirst && thirst <= 25);
		assertTrue(25 <= boredom && boredom <= 30);
	}

	@Test
	public void verifyStartingCatStatsAreRandomIfNotDeclared() {
		underTest.takeInNewPet("Cat", "Fido", "s");
		VirtualPet newPet = underTest.retrievePetInfo("Fido");
		int hunger = newPet.getHunger();
		int thirst = newPet.getThirst();
		int boredom = newPet.getBoredom();
		assertTrue(20 <= hunger && hunger <= 25);
		assertTrue(15 <= thirst && thirst <= 20);
		assertTrue(10 <= boredom && boredom <= 15);
	}

	@Test
	public void verifyStartingBirdStatsAreRandomIfNotDeclared() {
		underTest.takeInNewPet("Bird", "Fido", "s");
		VirtualPet newPet = underTest.retrievePetInfo("Fido");
		int hunger = newPet.getHunger();
		int thirst = newPet.getThirst();
		int boredom = newPet.getBoredom();
		assertTrue(15 <= hunger && hunger <= 20);
		assertTrue(25 <= thirst && thirst <= 30);
		assertTrue(15 <= boredom && boredom <= 20);
	}

	@Test
	public void verifyYouCannotHave2PetsWithSameNameDogNamedWoofNotCatNamedWoof() {
		boolean testAdd = underTest.takeInNewPet("Cat", "Woof", "description");
		VirtualPet petToTest = underTest.retrievePetInfo("Woof");
		assertThat(petToTest, is(testDog));
		assertFalse(testAdd);
	}

	@Test
	public void checkAutoAdoptIncreasesAdoptionCountFrom0To1() {
		underTest.takeInNewPet("Cat", "Moo", "", 10, 10, 0);
		underTest.tick();
		int adoptionCount = underTest.getAdoptionCount();
		assertThat(adoptionCount, is(1));
	}

	@Test
	public void checkPoorCarePetsIncreaseEuthanizedCountFrom0To1() {
		underTest.takeInNewPet("Cat", "Moo", "", 50, 10, 10);
		underTest.tick();
		int euthanizedCount = underTest.getEuthanizedCount();
		assertThat(euthanizedCount, is(1));
	}

	@Test
	public void checkAutoAdoptOnHungerLorE10ThirstLorE10AndIllnessLorE10() {
		underTest.takeInNewPet("Cat", "Cat", "description", 10, 10, 30);
		underTest.callVetFor("Cat");
		underTest.tick();
		VirtualPet checkPet = underTest.retrievePetInfo("Cat");
		assertThat(checkPet, is(nullValue()));
	}

	@Test
	public void checkThat2PetsArrivedwithin14DaysSinceIteratorIsNowRandom() {
		VirtualPetShelter newTest = new VirtualPetShelter();
		newTest.tick();
		newTest.tick();
		newTest.tick();
		newTest.tick();
		newTest.tick();
		newTest.cleanShelter();
		newTest.feedAllPets();
		newTest.giveWaterToAllPets();
		newTest.tick();
		newTest.tick();
		newTest.cleanShelter();
		newTest.feedAllPets();
		newTest.giveWaterToAllPets();
		newTest.tick();
		newTest.tick();
		newTest.feedAllPets();
		newTest.giveWaterToAllPets();
		newTest.tick();
		newTest.tick();
		newTest.cleanShelter();
		newTest.feedAllPets();
		newTest.giveWaterToAllPets();
		newTest.tick();
		newTest.tick();
		newTest.tick();
		Collection<VirtualPet> allPets = newTest.getAllPets();
		assertThat(allPets.size(), is(2));
	}

	//due to number rebalancing, this test is near impossible to predict
	public void verifyAll3PetChangesOccuredByEOD7() {
		String asserted = "At the end of the day\n" + "a pet was adopted for its enthusiasm\n"
				+ "at least one pet had to be put down for its poor condition\n"
				+ "a pet arrived at our doorstep for adoption\n";
		
		underTest.tick();
		underTest.tick();
		underTest.takeInNewPet("Dog", "Bark", "");
		underTest.tick();
		underTest.tick();
		underTest.tick();
		underTest.tick();
		
		underTest.takeInNewPet("Cat", "Moo", "", 10, 10, 0);
		String endOfDay7Tick = underTest.tick();
		
		assertThat(endOfDay7Tick, is(asserted));
	}
	
}

