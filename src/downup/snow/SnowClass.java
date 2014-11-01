package downup.snow;

public enum SnowClass {

	NORMAL("Normal", 20, 0),
	SCOUT("Scout", 15, 1), // Stunning key targets? Skills: Stunning/Freezing (0.5s - 6s)
	SOLDIER("Soldier", 20, -1), // Main offense. Skills: Rocket Jumping
	PYRO("Pyro", 20, 0), // Ambush.
	DEMOMAN("Demoman", 20, -1), // Defense / Area Denial. Skills: Placing stickies and detonating (Max of 6 stickies)
	HEAVY("Heavy", 30, -2), // Defense, Absorbing damage
	ENGINEER("Engineer", 15, 0), // Supplying ammo. Skills: Catching snowballs in midair
	MEDIC("Medic", 18, 0), // Healing. Skills: Healing, Ubercharge (2% per second while healing) grants immunity for 8 seconds
	SNIPER("Sniper", 10, 0), // Eliminating key targets from distance. 
	SPY("Spy", 12, 0); // Getting into enemy base. Skills: Backstab, Cloak
	
	private String name;
	double health;
	int speed;
	
	SnowClass(String name, double health, int speed) {
		this.name = name;
		this.health = health;
		this.speed = speed;
	}
	
	public String getName() {
		return name;
	}
	
	public double getHP() {
		return health;
	}
	
	public int speed() {
		return speed;
	}
}
