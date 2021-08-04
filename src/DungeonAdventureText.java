import java.util.Random;
import java.util.Scanner;

public class DungeonAdventureText {

    public static void main(String[] args) {

        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Enemy objects
        Enemy[] enemies = {
                new Enemy("Skeleton", "bone", 1, false, false),
                new Enemy("Zombie", "arm", 1, false, false),
                new Enemy("Guardian", "staff", 1, false, false),
                new Enemy("Assassin", "dagger", 1, false, false),
                new Enemy("Ogre", "club", 1, true, false),
                new Enemy("Troll", "mace", 1, true, false),
                new Enemy("Phantom", "ectoplasm", 1, true, false),
                new Enemy("Witch", "broom", 1, false, false),
                new Enemy("Wizard", "staff", 1, false, false),
                new Enemy("Gorgon", "claws", 1, true, false),
                new Enemy("Bat", "fangs", 1, false, true),
        };
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
            Enemy enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy.getType() + " LVL" + enemy.getLevel() + " has appeared!");
            numEnemiesEncountered ++;

            label:
            while(enemyHealth > 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("\tYour Level: " + playerLevel);
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy.getType() + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run");

                String input = in.nextLine();
                switch (input) {
                    case "1":
                        int damageDealt = rand.nextInt(attackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);

                        enemyHealth -= damageDealt;
                        health -= damageTaken;
                        totalDamageDealt = (totalDamageDealt + damageDealt);
                        totalDamageTaken = (totalDamageTaken + damageTaken);

                        System.out.println("--------------------------------------------------");
                        System.out.printf("\t> You strike the %s LVL%s for %s damage!", enemy.getType(), enemy.getLevel(), damageDealt);
                        System.out.printf("\n\t> The %s retaliates with %s resulting in %s damage!\n", enemy.getType(), enemy.getWeapon(), damageTaken);

                        if (health < 1) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                            break label;
                        }
                        break;
                    case "2":
                        if (numHealthPotions > 0) {
                            if (health == maxPlayerHealth) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("You have full health already!");
                            } else {
                                health += healthPotionHealAmount;
                                if (health > maxPlayerHealth) {
                                    health = maxPlayerHealth;
                                }
                                numHealthPotions--;
                                numPotionsUsed++;
                                System.out.println("--------------------------------------------------");
                                System.out.printf("\t> You drank a health potion, healing yourself for %s ." +
                                                "\n\t> You now have %s HP.\n\t> You have %s health potions left.\n",
                                        healthPotionHealAmount, health, numHealthPotions);
                            }
                        } else {
                            System.out.println("--------------------------------------------------");
                            System.out.println("\t> You have no health potions left!" +
                                    "Keep fighting for a chance to get one!");
                        }

                        break;
                    case "3":
                        System.out.println("--------------------------------------------------");
                        System.out.printf("\tYou run away from from %s LVL %s!\n", enemy.getType(), enemy.getLevel());
                        totalRun++;
                        continue GAME;
                    default:
                        System.out.println("--------------------------------------------------");
                        System.out.println("\tInvalid command!");
                        break;
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
            System.out.println(" # ****************************** # ");
            System.out.printf(" # %s LVL %s was defeated!\n", enemy.getType(), enemy.getLevel());
            System.out.println(" # ****************************** # ");

            if(rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions ++;
                System.out.printf("\t>The %s LVL%s has dropped a health potion!", enemy.getType(), enemy.getLevel());
                System.out.printf("\n\t>You now have %s health potion(s).", numHealthPotions);
            }

            if(playerExp == levelUp) {

                // Level Up
                playerLevel ++;
                levelUp = (levelUp * 2);
                System.out.println("\n--------------------------------------------------");
                System.out.printf(" # You've reached LEVEL %s!!!\n", playerLevel);
                System.out.println(" # You can now enter deeper into the dungeon!");
                System.out.println("--------------------------------------------------");

                // Player gets stronger
                attackDamage = (attackDamage + (attackDamage / 2));
                maxPlayerHealth = (maxPlayerHealth + (maxPlayerHealth / 2));

                // Enemies get stronger
                enemyAttackDamage = (enemyAttackDamage + (attackDamage / 4));
                maxEnemyHealth = (maxEnemyHealth + (maxEnemyHealth / 4));
                for(Enemy enemyObject : enemies) enemyObject.incrementLevel();

            }
            else {
                System.out.printf("\n\t>You gained 1 EXP (%s of %s for level up)", playerExp, levelUp);
            }
            System.out.printf("\n\t>You have %s HP left.\n", health);
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
        System.out.printf("You reached: LEVEL %s.\n", playerLevel);
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
