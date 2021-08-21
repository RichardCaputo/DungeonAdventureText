import java.sql.SQLOutput;
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
        int enemyAttackDamage = 5;
        int numEnemiesEncountered = 0;
        int bossHealth = 300;

        Level[] levels = {
                new Level(1, "Centaur"),
                new Level(2, "Minotaur"),
        };

        // Player Variables
        int maxPlayerHealth = 100;
        int health = 100;
        int attackDamage = 50;
        int playerLevel = 1;
        int playerExp = 0;
        int levelUp = (playerLevel * 10);
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 75; // Percentage
        int numPotionsUsed = 0;
        int totalDamageDealt = 0;
        int totalDamageTaken = 0;
        int totalRun = 0;

        System.out.println("\n\t> Welcome to the Dungeon!");

        boolean running = true;

        RUNNING:
        while(running) {
            PreBOSS:
            while (playerLevel < 5) {
                System.out.println("--------------------------------------------------");

                int enemyHealth = rand.nextInt(maxEnemyHealth);
                int startEnemyHealth = enemyHealth + 1;
                boolean isBlocking = rand.nextBoolean();
                Enemy enemy = enemies[rand.nextInt(enemies.length)];
                Level level = levels[0];
                System.out.println("\t# " + enemy.getType() + " LVL" + enemy.getLevel() + " has appeared!");
                numEnemiesEncountered++;

                while (enemyHealth > 0) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("\tYour Level: " + playerLevel);
                    System.out.printf("\tYour HP: %s of %s\n", health, maxPlayerHealth);
                    System.out.println("\t" + enemy.getType() + "'s HP: " + enemyHealth + " of " + startEnemyHealth);
                    System.out.println("\n\tWhat would you like to do?");
                    System.out.println("\t1. Attack");
                    System.out.println("\t2. Drink Health Potion");
                    System.out.println("\t3. Run");

                    String input = in.nextLine();
                    switch (input) {
                        case "1":
                            if (isBlocking) {
                                int damageTaken = rand.nextInt(enemyAttackDamage);
                                health -= damageTaken;

                                System.out.println("--------------------------------------------------");
                                System.out.printf("\t> The %s LVL%s blocked your attack!\n", enemy.getType(), enemy.getLevel());
                                System.out.printf("\t> The %s retaliates with %s resulting in %s damage!\n", enemy.getType(), enemy.getWeapon(), damageTaken);
                                isBlocking = rand.nextBoolean();
                            } else {
                                int damageDealt = rand.nextInt(attackDamage); // I want to fix this
                                int damageTaken = rand.nextInt(enemyAttackDamage);

                                health -= damageTaken;
                                enemyHealth -= damageDealt;
                                totalDamageDealt += damageDealt;
                                totalDamageTaken += damageTaken;

                                System.out.println("--------------------------------------------------");
                                System.out.printf("\t> You strike the %s LVL%s for %s damage!", enemy.getType(), enemy.getLevel(), damageDealt);
                                System.out.printf("\n\t> The %s retaliates with %s resulting in %s damage!\n", enemy.getType(), enemy.getWeapon(), damageTaken);

                                // Enemy Emotions
                                if (damageDealt >= 25 && enemyHealth > 0) {
                                    for (Enemy enemyObject : enemies) enemyObject.setAngry();
                                    System.out.println("--------------------------------------------------");
                                    System.out.printf("\t> The %s LVL%s has become angry!\n", enemy.getType(), enemy.getLevel());
                                } else if (enemyHealth <= 10 && enemyHealth > 0) {
                                    for (Enemy enemyObject : enemies) enemyObject.setScared();
                                    System.out.println("--------------------------------------------------");
                                    System.out.printf("\t> The %s LVL%s has become scared!\n", enemy.getType(), enemy.getLevel());
                                }
                            }
                            if (health < 1) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                                break RUNNING;
                            }
                            break;
                        case "2":
                            if (numHealthPotions > 0) {
                                if (health == maxPlayerHealth) {
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("\t> You have full health already!");
                                } else {
                                    health += healthPotionHealAmount;
                                    if (health > maxPlayerHealth) {
                                        health = maxPlayerHealth;
                                    }
                                    numHealthPotions--;
                                    numPotionsUsed++;
                                    System.out.println("--------------------------------------------------");
                                    System.out.printf("""
                                                    \t> You drank a health potion, healing yourself for %s .
                                                    \t> You now have %s HP.
                                                    \t> You have %s health potions left.
                                                    """,
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
                            System.out.printf("\tYou run away from from %s LVL%s!\n", enemy.getType(), enemy.getLevel());
                            totalRun++;
                            continue PreBOSS;
                        default:
                            System.out.println("--------------------------------------------------");
                            System.out.println("\tInvalid command!");
                            break;
                    }
                }

                playerExp++;
                System.out.println("--------------------------------------------------");
                System.out.println("\t # ****************************** # ");
                System.out.printf("\t # %s LVL%s was defeated!\n", enemy.getType(), enemy.getLevel());
                System.out.println("\t # ****************************** # ");
                System.out.println("--------------------------------------------------");

                if (rand.nextInt(100) < healthPotionDropChance) {
                    numHealthPotions++;
                    System.out.printf("\t>The %s LVL%s has dropped a health potion!", enemy.getType(), enemy.getLevel());
                    System.out.printf("\n\t>You now have %s health potion(s).\n", numHealthPotions);
                }

                if (playerExp == levelUp) {

                    // Level Up
                    playerLevel++;
                    levelUp = (levelUp + 10);
                    System.out.println("\n--------------------------------------------------");
                    System.out.printf(" # You've reached LEVEL %s!!!\n", playerLevel);
                    System.out.println(" # You are stronger and your health has been restored!");
                    System.out.println(" # You can now enter deeper into the dungeon!");
                    System.out.println("--------------------------------------------------");
                    System.out.println("What would you like to do now?");
                    System.out.printf("1. Go to floor %s.\n", level.getFloor() + 1);
                    System.out.printf("2. Stay on floor %s.\n", level.getFloor());
                    if (level.getFloor() >= 2) {
                        System.out.printf("3. Return to floor %s.\n", level.getFloor() - 1);
                    }
                    System.out.println("0. Exit the dungeon.");

                    String input = in.nextLine();

                    switch (input) {
                        case "1":
                            System.out.printf("You've entered floor %s\n", level.getFloor() + 1);
                            for (Level levelObject : levels) levelObject.floorUp();

                            // Enemies get stronger
                            enemyAttackDamage += (attackDamage / 10);
                            maxEnemyHealth += (maxEnemyHealth / 4);
                            for (Enemy enemyObject : enemies) enemyObject.incrementLevel();

                            break;
                        case "2":
                            System.out.printf("You stay on floor %s\n", level.getFloor());
                            break;
                        case "3":
                            if (level.getFloor() >= 2) {
                                System.out.printf("You've entered floor %s\n", level.getFloor() - 1);
                                for (Level levelObject : levels) levelObject.floorDown();

                                // Enemies get stronger
                                enemyAttackDamage -= (attackDamage / 10);
                                maxEnemyHealth -= (maxEnemyHealth / 4);
                                for (Enemy enemyObject : enemies) enemyObject.decrementLevel();

                            }
                            else {
                                System.out.println("Invalid command!");
                            }
                            break;
                        case "0":
                            System.out.println("--------------------------------------------------");
                            System.out.println("You exit the dungeon, victorious in your adventure!");
                            break RUNNING;
                        default:
                            System.out.println("Invalid command!");
                            break;
                    }

                    // Player gets stronger
                    attackDamage += (attackDamage / 2);
                    maxPlayerHealth += (maxPlayerHealth / 2);
                    health = maxPlayerHealth;

                } else {
                    System.out.printf("\t>You gained 1 EXP (%s of %s for level up)\n", playerExp, levelUp);
                }

            }

            System.out.println("--------------------------------------------------");
            System.out.println("You've entered the heart of the dungeon!");
            System.out.println("The temperature of the room is densely hot...");
            System.out.println("From the darkness a fire kindles revealing a monstrous dragon!!");

            BOSS:
            while (bossHealth > 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("\tYour Level: " + playerLevel);
                System.out.println("\tYour HP: " + health + " of " + maxPlayerHealth);
                System.out.println("\tDragon's HP: " + bossHealth + " of 300");
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run");

                String input = in.nextLine();
                switch (input) {
                    case "1":
                        int damageDealt = rand.nextInt(attackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);

                        bossHealth -= damageDealt;
                        health -= damageTaken;
                        totalDamageDealt += damageDealt;
                        totalDamageTaken += damageTaken;

                        System.out.println("--------------------------------------------------");
                        System.out.printf("\t> You strike the Dragon for %s damage!", damageDealt);
                        System.out.printf("\n\t> The Dragon retaliates with fire breath resulting in %s damage!\n", damageTaken);

                        if (health < 1) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                            break RUNNING;
                        }
                        break;
                    case "2":
                        if (numHealthPotions > 0) {
                            if (health == maxPlayerHealth) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("\t> You have full health already!");
                            } else {
                                health += healthPotionHealAmount;
                                if (health > maxPlayerHealth) {
                                    health = maxPlayerHealth;
                                }
                                numHealthPotions--;
                                numPotionsUsed++;
                                System.out.println("--------------------------------------------------");
                                System.out.printf("""
                                                \t> You drank a health potion, healing yourself for %s .
                                                \t> You now have %s HP.
                                                \t> You have %s health potions left.
                                                """,
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
                        System.out.println("\tYou run away from the Dragon, and will forever be remembered as the coward that you are!");
                        totalRun++;
                        break BOSS;
                    default:
                        System.out.println("--------------------------------------------------");
                        System.out.println("\tInvalid command!");
                        break;
                }
                if (bossHealth < 1) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("\t # ********************************************* # ");
                    System.out.println("\t # Congratulations! You've defeated the Dragon!! # ");
                    System.out.println("\t # ********************************************* # ");
                }
                else {
                    break;
                }
            }
        }

        // GAME OVER
        System.out.println("--------------------------------------------------");
        System.out.println("Game Over!");
        System.out.println("--------------------------------------------------");
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
