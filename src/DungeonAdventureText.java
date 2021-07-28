import java.util.Random;
import java.util.Scanner;

public class DungeonAdventureText {

    public static void main(String[] args) {

        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Enemy Variables
        String[] enemies = { "Skeleton", "Zombie", "Guardian", "Assassin", "Ogre", "Troll", "Phantom", "Witch", "Wizard", "Medusa", "Bat" };
        int enemyLevel = 1;
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;
        int numEnemiesEncountered = 0;

        // Player Variables
        int maxPlayerHealth = 100;
        int health = 100;
        int attackDamage = 50;
        int playerLevel = 1;
        int playerExp = 0;
        int levelUp = (playerLevel * 10);
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // Percentage
        int numPotionsUsed = 0;
        int totalDamageDealt = 0;
        int totalDamageTaken = 0;
        int totalRun = 0;

        boolean running = true;

        System.out.println("Welcome to the Dungeon!");

        GAME:
        while(running) {
            System.out.println("--------------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " LVL" + enemyLevel + " has appeared!");
            numEnemiesEncountered ++;

            while(enemyHealth > 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("\tYour Level: " + playerLevel);
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run");

                String input = in.nextLine();
                if(input.equals("1")) {
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;
                    totalDamageDealt = (totalDamageDealt + damageDealt);
                    totalDamageTaken = (totalDamageTaken + damageTaken);

                    System.out.println("--------------------------------------------------");
                    System.out.println("\t> You strike the " + enemy + " LVL" + enemyLevel + " for " + damageDealt + " damage!");
                    System.out.println("\t> You receive " + damageTaken + " in retaliation!");

                    if(health < 1) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                        break;
                    }
                }
                else if(input.equals("2")) {
                    if(numHealthPotions > 0) {
                        if(health == maxPlayerHealth) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("You have full health already!");
                        }
                        else {
                            health += healthPotionHealAmount;
                            if (health > maxPlayerHealth) {
                                health = maxPlayerHealth;
                            }
                            numHealthPotions --;
                            numPotionsUsed ++;
                            System.out.println("--------------------------------------------------");
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                    + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left.\n");
                        }
                    }
                    else {
                        System.out.println("--------------------------------------------------");
                        System.out.println("\t> You have no health potions left! Keep fighting for a chance to get one!");
                    }

                }
                else if(input.equals("3")) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("\tYou run away from from " + enemy + " LVL" + enemyLevel + "!");
                    totalRun ++;
                    continue GAME;
                }
                else {
                    System.out.println("--------------------------------------------------");
                    System.out.println("\tInvalid command!");
                }
            }
            // GAME OVER
            if (health < 1) {
                System.out.println("--------------------------------------------------");
                System.out.println("Game Over!");
                System.out.println("--------------------------------------------------");
                break;
            }

            playerExp ++;
            System.out.println("--------------------------------------------------");
            System.out.println(" # " + enemy + " LVL" + enemyLevel + " was defeated!");

            if(rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions ++;
                System.out.println(" # The " + enemy + " LVL" + enemyLevel + " has dropped a health potion!");
                System.out.println(" # You now have " + numHealthPotions + " health potion(s).");
            }

            if(playerExp == levelUp) {

                // Level Up
                playerLevel ++;
                levelUp = (levelUp * 2);
                System.out.println("--------------------------------------------------");
                System.out.println("\nYou've reached level " + playerLevel + "! You can now enter deeper into the dungeon!\n");

                // Player gets stronger
                attackDamage = (attackDamage + (attackDamage / 2));
                maxPlayerHealth = (maxPlayerHealth + (maxPlayerHealth / 2));

                // Enemies get stronger
                enemyAttackDamage = (enemyAttackDamage + (attackDamage / 4));
                maxEnemyHealth = (maxEnemyHealth + (maxEnemyHealth / 4));
                enemyLevel ++;

            }
            else {
                System.out.println(" # You gained 1 EXP (" + playerExp + " of " + levelUp + " for level up)");
            }
            System.out.println(" # You have " + health + " HP left.");
            System.out.println("--------------------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting.");
            System.out.println("2. Exit dungeon.");

            String input = in.nextLine();

            while(!input.equals("1") && !input.equals("2")) {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid command!");
                input = in.nextLine();
            }

            if(input.equals("1")) {
                System.out.println("--------------------------------------------------");
                System.out.println("\nYou continue on your adventure!");
            }
            else if(input.equals("2")) {
                System.out.println("--------------------------------------------------");
                System.out.println("\nYou exit the dungeon, victorious in your adventure!\n\n");
                break;
            }
        }
        System.out.println("STATS:");
        System.out.println("--------------------------------------------------");
        System.out.println("You reached: Level " + playerLevel + ".");
        System.out.println("You encountered: " + numEnemiesEncountered + " enemies.");
        System.out.println("You used: " + numPotionsUsed + " health potions.");
        System.out.println("You ran away: " + totalRun + " time(s).");
        System.out.println("You dealt a total of: " + totalDamageDealt + " damage.");
        System.out.println("You received a total of: " + totalDamageTaken + " damage.");
        System.out.println();
        System.out.println("########################");
        System.out.println("# THANKS FOR PLAYING!! #");
        System.out.println("########################");
    }

}
