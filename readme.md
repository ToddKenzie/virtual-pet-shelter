# Virtual Pet Shelter

**WeCanCodeIT Module 3 Project**

## Objective

User will be in control of a pet shelter with multiple pets.  It is the user's responsibility to take of all of the pets in the shelter.  Pets should also be able to be adopted through the shelter, and more pets can come in.

### How This Project May Differ From Other Students

* Currently 3 Pet types - Dog, Cat, and Bird (inherited from abstract VirtualPet)
* All attributes are inherted classes from abstract Attribute instead of simple ints.
* If not declared when created, starting main Attributes are random within a range based on the Pet type
* The three main Attributes that increase per tick() are randomly generated from a range per pet type on Object creation
* User must also monitor cleanliness of the facility and clean on occasion
* User must also care for the illness of critters, or they'll have to be removed
	* Same happens for poor food and water management
* If a pet shows exceptional energy (anti-boredom), they can be automatically adopted at the end of a tick()
* Every few days, a pet will arrive at the doorstep of the shelter

**Ideas to consider that are not implemented**

* Would the user be terminated for taking poor care of the shelter?

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