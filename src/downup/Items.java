package downup;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	public ArrayList<Item> itemList;
	
	//DownUp Snowmode items
	//Scout
	public ItemStack snowstun;
	public ArrayList<String> snowstun_lore = new ArrayList<String>();
	
	
	// Bows
	/** Item Name: The Grill<br/>
	 *  (+) On Fully Charged Shot:<br/>
	 *  	Ignites players in a small<br/>
	 *  	range when arrow touches block<br/>
	 *  (-) -35% damage<br/>
	 */
	public ItemStack the_grill;
	public ArrayList<String> the_grill_lore = new ArrayList<String>();
	/** Item Name: The Baker's Bow<br/>
	 *  (+) On Kill:<br/>
	 *  	1 bread is awarded<br/>
	 *  (-) -50% fire resistance<br/>
	 */
	public ItemStack the_bakers_bow;
	ArrayList<String> the_bakers_bow_lore = new ArrayList<String>();
	/** Item Name: The Piercer<br/>
	 *  (+) +50% damage<br/>
	 *  (+) Penetrates entites<br/>
	 *  (+) +20% damage if target is airborne<br/>
	 *  (-) -30% resistance to all damage<br/>
	 *  (-) +4 arrows used<br/>
	 */
	public ItemStack the_piercer;
	ArrayList<String> the_piercer_lore = new ArrayList<String>();
	/** Item Name: The Sulphur Shooter<br/>
	 *  Item Quality: Scientific
	 *  (+) Minicrit vs. burning players<br/>
	 *  (+) Minicrit vs. creepers<br/>
	 *  (-) -15% arrow velocity<br/>
	 *  (-) Disappears after 10 ticks <br/>
	 */
	public ItemStack the_sulph_shooter;
	ArrayList<String> the_sulph_shooter_lore = new ArrayList<String>();
	/** Item Name: The Oxygen Blaster<br/>
	 *  Item Quality: Scientific<br/>
	 *  (+) On Shoot: Blows mobs away<br/>
	 *  (+) On Hit: Increases burning duration on target<br/>
	 *  (+) On Hit: Nearby fires cluster target<br/>
	 *  (-) -20% damage<br/>
	 *  (-) Minicrits taken while burning<br/>
	 */
	public ItemStack the_oxygen_blaster;
	ArrayList<String> the_oxygen_blaster_lore = new ArrayList<String>();
	/** Item Name: The Second Jump<br/>
	 *  (+) Grants double jump that deals 1 damage when used<br/>
	 *  (-) 35% more deviation<br/>
	 *  (i) Run to fill up hype meter, Hype grants minicrits.<br/>
	 */
	public ItemStack second_jmp;
	ArrayList<String> second_jmp_lore = new ArrayList<String>();
	/** Item Name: The Medication<br/>
	 * (+) Heals teammates<br/>
	 * (-) -2 max health on wearer<br/>
	 */
	public ItemStack the_medic;
	ArrayList<String> medic_lore = new ArrayList<String>();
	/** Item Name: The Splasher<br/>
	 * (+)On Charged Shot: Wets foes<br/>
	 * (+)Extinguishes teammates<br/>
	 * (-)15% damage if not charged<br/>
	 * (-)-2 max health<br/>
	 */
	public ItemStack the_splasher;
	ArrayList<String> splasher_lore = new ArrayList<String>();
	/** Item Name: The Sharp Shooter<br/>
	 *  (+)On Kill: Gain Focus
	 *  (+)Consistent hits stack damage
	 *  (+)On Focus: No flinching, 80% knockback reduction and +15% damage bonus.
	 *  (-)-30% damage
	 */
	public ItemStack the_sharps;
	ArrayList<String> sharps_lore = new ArrayList<String>();
	//Swords
	/** Item Name: The Hungry Fighter<br/>
	 *  Sword Material: Iron<br/>
	 *  (+) On Hit: <br/>
	 *   Healing effect is applied<br/>
	 *  (-) Saturation depletes faster<br/>
	 */
	public ItemStack hungry_fighter;
	ArrayList<String> hungry_fighter_lore = new ArrayList<String>();
	/** Item Name: Sodium Slicer<br/>
	 *  Item Quality: Scientific<br/>
	 *  Sword Material: Wood<br/>
	 *  (+) Critical hit vs. wet players<br/>
	 *  (-) -25% damage vs. dry players<br/>
	 */
	public ItemStack sodium_slicer;
	ArrayList<String> sodium_slicer_lore = new ArrayList<String>();
	/** Item Name: Athletic Assaulter<br/>
	 *  Sword Material: Stone<br/>
	 *  (i)While active:
	 *  (+)+20% move speed on wearer
	 *  (+)No fall damage taken
	 *  (-)-20% damage resistance
	 */
	public ItemStack ath_assaulter;
	ArrayList<String> ath_ass_lore = new ArrayList<String>();
	/** Item Name: Return To Sender<br/>
	 *  Sword Material: Iron<br/>
	 *  (+)Can deflect projectiles<br/>
	 *  (+)+2 max health<br/>
	 *  (-)20% melee vulnerability
	 */
	public ItemStack return_sender;
	ArrayList<String> return_send_lore = new ArrayList<String>();
	// Sticks
	/** Item Name: The Blitzkrieg
	 *  Heals teammates (2.4 per second, 4.8 after 8 seconds, 7.2 after 15)
	 *  Fills blitzboost meter (2.75% per second)
	 *  On Blitzboost: Healing target gains critboost
	 */

	public ItemStack blitzkrieg;
	ArrayList<String> blitzkrieg_lore = new ArrayList<String>();
	/** Item Name: The Decision
	 *   
	 */
	public ItemStack decision;
	//Axes
	/**
	 * Item Name: The SkullCutter<br/>
	 * Axe Material: Stone<br/>
	 * (+) One-hit-break wood<br/>
	 * (+) On Hit: Pushes victim away<br/>
	 * (-) All chopped wood becomes sticks
	 */
	public ItemStack skullcutter;
	ArrayList<String> skullcutter_lore = new ArrayList<String>();
	/**
	 * Item Name: The Aerial Swing<br/>
	 * Axe Material: Iron<br/>
	 * (+)40 damage after grill jumping
	 * (+)+60% fire resistance
	 * (+)+100% fall damage resistance
	 * (-)-50% damage
	 */
	public ItemStack aerial_swing;
	ArrayList<String> aerial_swing_lore = new ArrayList<String>();
	//Others
	/** Item Name: Lunchbox Lunatic (Banner)<br/>
	 *  (i) Buff gives food regeneration<br/>
	 *    and increased max health, while<br/>
	 *    damage done provides some healing.<br/>
	 *    Rage increased through damage.<br/>
	 */
	public ItemStack lunch_luna;
	ArrayList<String> lunch_luna_lore = new ArrayList<String>();
	/** Item Name: Rocket Launcher (RPG)<br/>
	 *  Maximum Ramp Up: 115%
	 *  Minimum Fall Off: 45%
	 *  Damage: 9
	 *  Average Distance: 7
	 */
	public ItemStack rocketLauncher;
	private ArrayList<String> rpg_lore = new ArrayList<String>();
	
	public Items() {
		itemList = new ArrayList<Item>();
		//Bows
		the_grill = new ItemStack(Material.BOW);
		ItemMeta the_grill_meta = the_grill.getItemMeta();
		the_grill_meta.setDisplayName("The Grill");
		the_grill_lore.add(ChatColor.RED + "-50% damage");
		the_grill_lore.add(ChatColor.BLUE + "On Charged Shot:");
		the_grill_lore.add(ChatColor.BLUE + " Ignites players in a");
		the_grill_lore.add(ChatColor.BLUE + " small range when arrow");
		the_grill_lore.add(ChatColor.BLUE + " touches block");
		the_grill_lore.add(ChatColor.BLUE + "+40% arrow velocity");
		the_grill_meta.setLore(the_grill_lore);
		the_grill.setItemMeta(the_grill_meta);
		itemList.add(new Item("the_grill", the_grill));
		
		the_bakers_bow = new ItemStack(Material.BOW);
		ItemMeta the_bakers_bow_meta = the_bakers_bow.getItemMeta();
		the_bakers_bow_meta.setDisplayName("The Baker's Bow");
		the_bakers_bow_lore.add(ChatColor.RED + "-50% fire resistance");
		the_bakers_bow_lore.add(ChatColor.BLUE +"On Kill:");
		the_bakers_bow_lore.add(ChatColor.BLUE +" 1 bread is awarded");
		the_bakers_bow_meta.setLore(the_bakers_bow_lore);
		the_bakers_bow.setItemMeta(the_bakers_bow_meta);
		itemList.add(new Item("the_bakers_bow", the_bakers_bow));
		
		the_piercer = new ItemStack(Material.BOW);
		ItemMeta the_piercer_meta = the_piercer.getItemMeta();
		the_piercer_meta.setDisplayName("The Piercer");
		the_piercer_lore.add(ChatColor.RED + "-45% resistance to all damage");
		the_piercer_lore.add(ChatColor.RED + "+5 arrows used");
		the_piercer_lore.add(ChatColor.BLUE+ "+50% damage");
		the_piercer_lore.add(ChatColor.BLUE+ "+25% damage if target is airborne");
		the_piercer_lore.add(ChatColor.BLUE+ "Penetrates entities");
		the_piercer_meta.setLore(the_piercer_lore);
		the_piercer.setItemMeta(the_piercer_meta);
		itemList.add(new Item("the_piercer", the_piercer));
		
		the_sulph_shooter = new ItemStack(Material.BOW);
		ItemMeta sulph_meta = the_sulph_shooter.getItemMeta();
		sulph_meta.setDisplayName(ChatColor.DARK_PURPLE + "The Sulphur Shooter");
		the_sulph_shooter_lore.add(ChatColor.RED + "-15% arrow velocity");
		the_sulph_shooter_lore.add(ChatColor.RED + "Arrow disappears after");
		the_sulph_shooter_lore.add(ChatColor.RED + " 8 ticks");
		the_sulph_shooter_lore.add(ChatColor.BLUE + "+20% damage");
		the_sulph_shooter_lore.add(ChatColor.BLUE +"Minicrit vs. burning mobs");
		the_sulph_shooter_lore.add(ChatColor.BLUE +"Minicrit vs. creepers");
		sulph_meta.setLore(the_sulph_shooter_lore);
		the_sulph_shooter.setItemMeta(sulph_meta);
		itemList.add(new Item("the_sulpher_shooter", the_sulph_shooter));
		
		the_oxygen_blaster = new ItemStack(Material.BOW);
		ItemMeta oxygen_bl_meta = the_oxygen_blaster.getItemMeta();
		oxygen_bl_meta.setDisplayName(ChatColor.DARK_PURPLE + "The Oxygen Blaster");
		the_oxygen_blaster_lore.add(ChatColor.RED + "-20% damage");
		the_oxygen_blaster_lore.add(ChatColor.RED + "Minicrits taken while burning");
		the_oxygen_blaster_lore.add(ChatColor.BLUE+ "On Shoot: Blows mobs away");
		the_oxygen_blaster_lore.add(ChatColor.BLUE+ "On Hit: Burning duration is ");
		the_oxygen_blaster_lore.add(ChatColor.BLUE+ " increased");
		oxygen_bl_meta.setLore(the_oxygen_blaster_lore);
		the_oxygen_blaster.setItemMeta(oxygen_bl_meta);
		itemList.add(new Item("the_oxygen_blaster", the_oxygen_blaster));
		
		second_jmp = new ItemStack(Material.BOW);
		ItemMeta sec_jmp_meta = second_jmp.getItemMeta();
		sec_jmp_meta.setDisplayName("The Second Jump");
		second_jmp_lore.add(ChatColor.RED + "35% more deviation");
		second_jmp_lore.add(ChatColor.BLUE +"Grants double jump");
		second_jmp_lore.add(ChatColor.WHITE+"Build up hype as you run.");
		second_jmp_lore.add(ChatColor.WHITE+"When your hype is full, unleash");
		second_jmp_lore.add(ChatColor.WHITE+"minicrits!");
		sec_jmp_meta.setLore(second_jmp_lore);
		second_jmp.setItemMeta(sec_jmp_meta);
		itemList.add(new Item("the_second_jump", second_jmp));
		
		the_medic = new ItemStack(Material.BOW);
		ItemMeta medic_meta = the_medic.getItemMeta();
		medic_meta.setDisplayName("The Medic");
		medic_lore.add(ChatColor.RED + "-2 max health");
		medic_lore.add(ChatColor.BLUE + "Heals teammates");
		medic_meta.setLore(medic_lore);
		the_medic.setItemMeta(medic_meta);
		itemList.add(new Item("the_medication", the_medic));
		
		the_splasher = new ItemStack(Material.BOW);
		ItemMeta splasher_meta = the_splasher.getItemMeta();
		splasher_meta.setDisplayName("The Splasher");
		splasher_lore.add(ChatColor.RED + "-20% projectile resistance");
		splasher_lore.add(ChatColor.RED + "-15% damage if non-charged");
		splasher_lore.add(ChatColor.BLUE+ "On Charged Shot: Wets players");
		splasher_lore.add(ChatColor.BLUE+ "Extinguishes teammates");
		splasher_meta.setLore(splasher_lore);
		the_splasher.setItemMeta(splasher_meta);
		itemList.add(new Item("the_splasher", the_splasher));
		
		the_sharps = new ItemStack(Material.BOW);
		ItemMeta sharps_meta = the_sharps.getItemMeta();
		sharps_meta.setDisplayName("The Sharp Shooter");
		sharps_lore.add(ChatColor.RED + "-30% damage penalty");
		sharps_lore.add(ChatColor.RED + "-1 arrow penalty on miss");
		sharps_lore.add(ChatColor.BLUE + "Consistent hits stack damage");
		sharps_lore.add(ChatColor.BLUE + "On Kill: Gain Focus");
		sharps_lore.add(ChatColor.BLUE + "On Focus: No flinching from");
		sharps_lore.add(ChatColor.BLUE + " taking damage, 80% knockback");
		sharps_lore.add(ChatColor.BLUE + " reduction, and +15% damage.");
		sharps_lore.add(ChatColor.BLUE + "Can head shot while in focus.");
		sharps_meta.setLore(sharps_lore);
		the_sharps.setItemMeta(sharps_meta);
		itemList.add(new Item("the_sharp_shooter", the_sharps));
		
		//Swords
		hungry_fighter = new ItemStack(Material.IRON_SWORD);
		ItemMeta hungry_fighter_meta = hungry_fighter.getItemMeta();
		hungry_fighter_meta.setDisplayName("The Hungry Fighter");
		hungry_fighter_lore.add(ChatColor.RED + "Foodbar depletes faster");
		hungry_fighter_lore.add(ChatColor.BLUE +"On Hit:");
		hungry_fighter_lore.add(ChatColor.BLUE +" Healing effect is applied");
		hungry_fighter_meta.setLore(hungry_fighter_lore);
		hungry_fighter.setItemMeta(hungry_fighter_meta);
		itemList.add(new Item("the_hungry_fighter", hungry_fighter));
		
		sodium_slicer = new ItemStack(Material.WOOD_SWORD);
		ItemMeta na_slicer_meta = sodium_slicer.getItemMeta();
		na_slicer_meta.setDisplayName(ChatColor.DARK_PURPLE + "The Sodium Slicer");
		sodium_slicer_lore.add(ChatColor.RED + "-25% damage vs. dry players");
		sodium_slicer_lore.add(ChatColor.BLUE +"Critical hit vs. wet players");
		na_slicer_meta.setLore(sodium_slicer_lore);
		sodium_slicer.setItemMeta(na_slicer_meta);
		itemList.add(new Item("the_sodium_slicer", sodium_slicer));
		
		ath_assaulter = new ItemStack(Material.STONE_SWORD);
		ItemMeta ath_ass_meta = ath_assaulter.getItemMeta();
		ath_ass_meta.setDisplayName("Athletic Assaulter");
		ath_ass_lore.add(ChatColor.WHITE + "While active:");
		ath_ass_lore.add(ChatColor.RED + " 25% damage vulnerability");
		ath_ass_lore.add(ChatColor.BLUE +" +20% movement speed");
		ath_ass_lore.add(ChatColor.BLUE +" No fall dmg taken");
		ath_ass_meta.setLore(ath_ass_lore);
		ath_assaulter.setItemMeta(ath_ass_meta);
		itemList.add(new Item("athletic_assaulter", ath_assaulter));
		
		return_sender = new ItemStack(Material.IRON_SWORD);
		ItemMeta return_send_meta = return_sender.getItemMeta();
		return_send_meta.setDisplayName("Return To Sender");
		return_send_lore.add(ChatColor.RED + "20% melee vulnerability");
		return_send_lore.add(ChatColor.BLUE +"Can deflect projectiles");
		return_send_lore.add(ChatColor.BLUE +"+2 max health on wearer");
		return_send_lore.add(ChatColor.WHITE+"Address unknown.");
		return_send_meta.setLore(return_send_lore);
		return_sender.setItemMeta(return_send_meta);
		itemList.add(new Item("return_to_sender", return_sender));
		
		//Axes
		skullcutter = new ItemStack(Material.STONE_AXE);
		ItemMeta skullcutter_meta = skullcutter.getItemMeta();
		skullcutter_meta.setDisplayName("The SkullCutter");
		skullcutter_lore.add(ChatColor.RED + "All chopped wood becomes");
		skullcutter_lore.add(ChatColor.RED + " sticks");
		skullcutter_lore.add(ChatColor.BLUE +"One-hit-break wood");
		skullcutter_lore.add(ChatColor.BLUE +"On hit: Pushes victim away");
		skullcutter_meta.setLore(skullcutter_lore);
		skullcutter.setItemMeta(skullcutter_meta);
		itemList.add(new Item("the_skullcutter", skullcutter));
		
		aerial_swing = new ItemStack(Material.IRON_AXE);
		ItemMeta aerial_swing_meta = aerial_swing.getItemMeta();
		aerial_swing_meta.setDisplayName("The Aerial Swing");
		aerial_swing_lore.add(ChatColor.RED + "-50% damage");
		aerial_swing_lore.add(ChatColor.RED + "-30% explosion resistance");
		aerial_swing_lore.add(ChatColor.BLUE +"40 damage after grill jump");
		aerial_swing_lore.add(ChatColor.BLUE +"+60% fire resistance");
		aerial_swing_lore.add(ChatColor.BLUE +"+100% fall damage resistance");
		aerial_swing_meta.setLore(aerial_swing_lore);
		aerial_swing.setItemMeta(aerial_swing_meta);
		itemList.add(new Item("the_aerial_swing", aerial_swing));
	
		//Others
		lunch_luna = new ItemStack(Material.RECORD_5);
		ItemMeta lunch_meta = lunch_luna.getItemMeta();
		lunch_meta.setDisplayName("Lunchbox Lunatic");
		lunch_luna_lore.add(ChatColor.WHITE + "Buff gives increased max health");
		lunch_luna_lore.add(ChatColor.WHITE + " to nearby teammates,");
		lunch_luna_lore.add(ChatColor.WHITE + " while damage done provides healing.");
		lunch_luna_lore.add(ChatColor.WHITE + "Rage increases through damage.");
		lunch_meta.setLore(lunch_luna_lore);
		lunch_luna.setItemMeta(lunch_meta);
		itemList.add(new Item("the_lunchbox_lunatic", lunch_luna));
		
		rocketLauncher = new ItemStack(Material.FLOWER_POT);
		ItemMeta rpg_meta = rocketLauncher.getItemMeta();
		rpg_meta.setDisplayName(ChatColor.GRAY + "Rocket Launcher [4]");
		rpg_lore.add(ChatColor.WHITE + "Rocket Launcher");
		rpg_meta.setLore(rpg_lore);
		rocketLauncher.setItemMeta(rpg_meta);
		itemList.add(new Item("rocket_launcher", rocketLauncher));
		
		blitzkrieg = new ItemStack(Material.STICK);
		ItemMeta blitz_meta = blitzkrieg.getItemMeta();
		blitz_meta.setDisplayName("The Blitzkrieg");
		blitzkrieg_lore.add(ChatColor.WHITE + "Heals teammates");
		blitzkrieg_lore.add(ChatColor.WHITE + "Healing fills blitzboost");
		blitzkrieg_lore.add(ChatColor.WHITE + "On Blitzboost: 100% crits");
		blitz_meta.setLore(blitzkrieg_lore);
		blitzkrieg.setItemMeta(blitz_meta);
		itemList.add(new Item("blitzkrieg", blitzkrieg));
		
		// DownUp Snowmode items
		snowstun = new ItemStack(Material.STICK);
		ItemMeta snowstun_meta = snowstun.getItemMeta();
		snowstun_meta.setDisplayName("The Snowstun");
		snowstun_lore.add(ChatColor.BLUE + "Stuns players when projectile");
		snowstun_lore.add(ChatColor.BLUE + " is launched");
		snowstun_lore.add(ChatColor.BLUE + "Maximum stun distance freezes");
		snowstun_lore.add(ChatColor.BLUE + " players");
		snowstun_meta.setLore(snowstun_lore);
		snowstun.setItemMeta(snowstun_meta);
		itemList.add(new Item("snowstun", snowstun));
	}
}
