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
                    System.out.printf("\t> You strike the %s LVL%s for %s damage!", enemy, enemyLevel, damageDealt);
                    System.out.printf("\n\t> You receive %s in retaliation!\n", damageTaken);

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
                            System.out.printf("\n\t> You drink a health potion, healing yourself for %s .\n\t> You now have %s HP.\n\t> You have %s health potions left.\n", healthPotionHealAmount, health, numHealthPotions);
                        }
                    }
                    else {
                        System.out.println("--------------------------------------------------");
                        System.out.println("\t> You have no health potions left! Keep fighting for a chance to get one!");
                    }

                }
                else if(input.equals("3")) {
                    System.out.println("--------------------------------------------------");
                    System.out.printf("\tYou run away from from %s LVL %s!\n", enemy, enemyLevel);
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
            System.out.printf(" # %s LVL %s was defeated!\n", enemy, enemyLevel);

            if(rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions ++;
                System.out.printf("\n # The %s LVL%s has dropped a health potion!", enemy, enemyLevel);
                System.out.printf("\n # You now have %s health potion(s).", numHealthPotions);
            }

            if(playerExp == levelUp) {

                // Level Up
                playerLevel ++;
                levelUp = (levelUp * 2);
                System.out.println("--------------------------------------------------");
                System.out.printf("\n # You've reached level %s! You can now enter deeper into the dungeon! # \n", playerLevel);

                // Player gets stronger
                attackDamage = (attackDamage + (attackDamage / 2));
                maxPlayerHealth = (maxPlayerHealth + (maxPlayerHealth / 2));

                // Enemies get stronger
                enemyAttackDamage = (enemyAttackDamage + (attackDamage / 4));
                maxEnemyHealth = (maxEnemyHealth + (maxEnemyHealth / 4));
                enemyLevel ++;

            }
            else {
                System.out.printf("\n # You gained 1 EXP (%s of %s for level up)", playerExp, levelUp);
            }
            System.out.printf("\n # You have %s HP left.\n", health);
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
                System.out.println("You continue on your adventure!");
            }
            else if(input.equals("2")) {
                System.out.println("--------------------------------------------------");
                System.out.println("You exit the dungeon, victorious in your adventure!");
                break;
            }
        }
        System.out.println("STATS:");
        System.out.println("--------------------------------------------------");
        System.out.printf("You reached: Level %s.\n", playerLevel);
        System.out.printf("You encountered: %s enemies.\n", numEnemiesEncountered);
        System.out.printf("You used: %s health potions.\n", numPotionsUsed);
        System.out.printf("You ran away: %s time(s).\n", totalRun);
        System.out.printf("You dealt a total of: %s damage.\n", totalDamageDealt);
        System.out.printf("You received a total of: %s damage.\n", totalDamageTaken);
        System.out.println();
        System.out.println("########################");
        System.out.println("# THANKS FOR PLAYING!! #");
        System.out.println("########################");
    }

}
