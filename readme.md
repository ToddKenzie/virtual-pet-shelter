# Virtual Pet Shelter

**WeCanCodeIT Module 3 Project**

## Objective

User will be in control of a pet shelter with multiple pets.  It is the user's responsibility to take of all of the pets in the shelter.  Pets should also be able to be adopted through the shelter, and more pets can be brought in by user command.

### How This Project May Differ From Other Students

* Currently 3 Pet types - Dog, Cat, and Bird (inherited from abstract VirtualPet)
* All attributes are inherted classes from abstract Attribute Class
* If not declared when created, starting main Attributes are generated within a range of values based on the Pet type
* User must also monitor cleanliness of the facility and clean on occasion, else Illness rates in the shelter will get out of control
* Pets who have high Illness will not be adoptable, even with the Adopt command
* When tick() occurs, 
	* The three primary Attributes increase by a random generated value from a range based on pet type at Object creation
	* Pets can be auto-adopted upon tick() if: 
		* They show exceptional energy (anti-boredom) OR
		* Their hunger, thirst, and illness are all low values
	* Pets can be removed due to poor care
		* High Illness, Hunger, or Thirst
	* Every few days, a pet will arrive at the doorstep of the shelter
* Games ends in one of four ways
	* User terminates program
	* Over 10 animals are euthanized
	* There are no pets in the shelter, regardless of reason
	* User lasts 100 days (tick())

### Minimum Requirements

* Create a game loop that accepts user input and writes output to the console
* Tick() method, indicating passing of time	
* Ability to Feed *all* pets
* Ability to Water *all* pets	
* Ability to Play with *only* one pet	
* Adopt a pet	
* Receive new pets	
* Coding items
	* Instance variables
	* Methods
	* Constructors (two specific to the scope of the project)	
* Project is driven through tests	
* Style/formatting/code quality