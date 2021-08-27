import java.util.Random;
import java.util.Scanner;

public class Game {

    // System objects
    ConsoleInputOutput in = new ConsoleInputOutput();
    Random rand = new Random();

    // Enemy objects
    Enemy[] enemies = {
            new Enemy("Skeleton", "bone", 1, 18, 3, true, false, false, false),
            new Enemy("Zombie", "arm", 1, 28, 5, true, false, false, false),
            new Enemy("Guardian", "staff", 1, 35, 8, true, false, false, true),
            new Enemy("Assassin", "dagger", 1, 21, 7, true, false, false, true),
            new Enemy("Ogre", "club", 1, 40, 10, true, true, false, false),
            new Enemy("Troll", "mace", 1, 38, 9, true, true, false, false),
            new Enemy("Phantom", "ectoplasm", 1, 23, 4, true, true, false, false),
            new Enemy("Witch", "broom", 1, 20, 6, true, false, false, false),
            new Enemy("Wizard", "staff", 1, 23, 7, true, false, false, true),
            new Enemy("Gorgon", "claws", 1, 30, 7, true, true, false, false),
            new Enemy("Bat", "fangs", 1, 15, 3, true, false, true, false),
    };

    // Level objects
    Level[] levels = {
            new Level(1, "Centaur", "hind legs", 100, 45, true, true),
            new Level(2, "Minotaur", "horns,", 200, 65, true, true),
            new Level(3, "Warlock", "wand", 300, 75, true, true),
            new Level(4, "Mechanized Warrior", "energy beam", 400, 85, true, true)
    };

    // Object initialization
    Player player = new Player("player temp",100, 100, 50, 1, 0,
            3, 30, 0, 0, 0,
            0, 0); // I want to change this (add player name from input.)
    Level level = levels[0];
    Boss boss = new Boss("Dragon", "fire breath", 500, 100);

    Game() {
        PreBOSS:
        while (level.getFloor() < 5) {

            Enemy enemy = enemies[rand.nextInt(enemies.length)];
            enemy.setEnemyHealth(enemy.getStartingHP());
            System.out.println("--------------------------------------------------");
            System.out.println("\t# " + enemy.getType() + " LVL" + enemy.getLevel() + " has appeared!");

            while (enemy.getEnemyHealth() > 0) {

                player.setNumEnemiesEncountered();
                System.out.println("--------------------------------------------------");
                System.out.println("\tYour Level: " + player.getPlayerLevel());
                System.out.printf("\tYour HP: %s of %s\n", player.getHealth(), player.getMaxPlayerHealth());
                System.out.printf("\t%s's HP: %s of %s\n", enemy.getType(), enemy.getEnemyHealth(), enemy.getStartingHP());
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run");

                String input = in.receiveInput();
                switch (input) {
                    case "1":
                        enemy.setIsBlocking();
                        enemy.setEnemyAttack();
                        enemy.setEnemyAttackDamage();
                        player.setAttackDamage();
                        if (enemy.getIsBlocking()) {
                            System.out.println("--------------------------------------------------");
                            System.out.printf("\t> The %s LVL%s blocked your attack!\n", enemy.getType(), enemy.getLevel());
                            if (enemy.getEnemyAttack()) {
                                System.out.printf("\t> The %s retaliates with %s resulting in %s damage!\n", enemy.getType(), enemy.getWeapon(), enemy.getEnemyAttackDamage());
                                player.decreaseHealth(enemy.getEnemyAttackDamage());
                                player.setTotalDamageTaken(enemy.getEnemyAttackDamage());
                                if (player.getHealth() < 1) {
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                                    new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
                                }
                            } else {
                                System.out.printf("\t> The %s LVL%s missed their attack!\n", enemy.getType(), enemy.getLevel());
                            }
                            enemy.setIsBlocking();
                        } else {
                            player.decreaseHealth(enemy.getEnemyAttackDamage());
                            enemy.decreaseEnemyHealth(player.getAttackDamage());
                            player.setTotalDamageDealt();
                            if (player.getHealth() < 1) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                                new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
                            }
                            if (player.getAttackDamage() < 1){
                                System.out.printf("\t> You swung hard at the %s LVL%s and missed!", enemy.getType(), enemy.getLevel());
                            }
                            else {
                                System.out.println("--------------------------------------------------");
                                System.out.printf("\t> You strike the %s LVL%s for %s damage!", enemy.getType(), enemy.getLevel(), player.getAttackDamage());
                            }
                            System.out.printf("\n\t> The %s retaliates with %s resulting in %s damage!\n", enemy.getType(), enemy.getWeapon(), enemy.getEnemyAttackDamage());

                            // Enemy Emotions
                            if (player.getAttackDamage() >= 25 && enemy.getEnemyHealth() > 0) {
                                for (Enemy enemyObject : enemies) enemyObject.setAngry();
                                System.out.println("--------------------------------------------------");
                                System.out.printf("\t> The %s LVL%s has become angry!\n", enemy.getType(), enemy.getLevel());
                            } else if (enemy.getEnemyHealth() <= 10 && enemy.getEnemyHealth() > 0) {
                                for (Enemy enemyObject : enemies) enemyObject.setScared();
                                System.out.println("--------------------------------------------------");
                                System.out.printf("\t> The %s LVL%s has become scared!\n", enemy.getType(), enemy.getLevel());
                            }
                        }
                        break;
                    case "2":
                        player.decreaseNumHealthPotions();
                        break;
                    case "3":
                        System.out.println("--------------------------------------------------");
                        System.out.printf("\tYou run away from from %s LVL%s!\n", enemy.getType(), enemy.getLevel());
                        player.setTotalRun();
                        continue PreBOSS;
                    default:
                        System.out.println("--------------------------------------------------");
                        System.out.println("\tInvalid command!");
                        break;
                }
            }

            player.setPlayerExp();
            System.out.println("--------------------------------------------------");
            System.out.println("\t # ****************************** # ");
            System.out.printf("\t # %s LVL%s was defeated!\n", enemy.getType(), enemy.getLevel());
            System.out.println("\t # ****************************** # ");
            player.dropPotion(enemy.getType(), enemy.getLevel());

            if (player.getPlayerExp() == (player.getPlayerLevel() * 10)) {
                player.levelUp();
                System.out.println("\n--------------------------------------------------");
                System.out.printf(" # You've reached LEVEL %s!!!\n", player.getPlayerLevel());
                System.out.println(" # You are now stronger!");
                System.out.println(" # You can now enter deeper into the dungeon!");
                System.out.println("--------------------------------------------------");
                System.out.println("You have approached a staircase, what will you do?");
                System.out.printf("1. Go to floor %s.\n", level.getFloor() + 1);
                System.out.printf("2. Stay on floor %s.\n", level.getFloor());
                if (level.getFloor() >= 2) {
                    System.out.printf("3. Return to floor %s.\n", level.getFloor() - 1);
                }
                System.out.println("0. Exit the dungeon.");

                String input = in.receiveInput();

                switch (input) {
                    case "1":
                        System.out.printf("Before you could enter floor %s, a %s has appeared!\n", level.getFloor(), level.getSubBoss());

                        // Sub Boss Encounter
                        int maxSubBossHealth = level.getSubBossHealth();
                        level.setIsBlocking();
                        while (level.getSubBossHealth() > 0) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("\tYour Level: " + player.getPlayerLevel());
                            System.out.printf("\tYour HP: %s of %s\n", player.getHealth(), player.getMaxPlayerHealth());
                            System.out.println("\t" + level.getSubBoss() + "'s HP: " + level.getSubBossHealth() + " of " + maxSubBossHealth);
                            System.out.println("\n\tWhat would you like to do?");
                            System.out.println("\t1. Attack");
                            System.out.println("\t2. Drink Health Potion");
                            System.out.println("\t3. Run");

                            input = in.receiveInput();

                            switch (input) {
                                case "1":
                                    if (player.getHealth() < 1) {
                                        System.out.println("--------------------------------------------------");
                                        System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                                        new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
                                    }
                                    if (level.getIsBlocking()) {
                                        player.decreaseHealth(level.getAttackDamage());
                                        System.out.println("--------------------------------------------------");
                                        System.out.printf("\t> The %s blocked your attack!\n", level.getSubBoss());
                                        System.out.printf("\t> The %s retaliates with %s resulting in %s damage!\n", level.getSubBoss(), level.getWeapon(), level.getAttackDamage());
                                        level.setIsBlocking();
                                    } else {
                                        player.decreaseHealth(level.getAttackDamage());
                                        level.setSubBossHealth(player.getAttackDamage());
                                        player.setTotalDamageDealt();
                                        player.setTotalDamageTaken(level.getAttackDamage());

                                        System.out.println("--------------------------------------------------");
                                        System.out.printf("\t> You strike the %s for %s damage!", level.getSubBoss(), player.getAttackDamage());
                                        System.out.printf("\n\t> The %s retaliates with %s resulting in %s damage!\n", level.getSubBoss(), level.getWeapon(), level.getAttackDamage());

                                        /* Enemy Emotions
                                        if (player.getAttackDamage >= 25 && level.getSubBossHealth() > 0) {
                                            for (Enemy enemyObject : enemies) enemyObject.setAngry();
                                            System.out.println("--------------------------------------------------");
                                            System.out.printf("\t> The %s has become angry!\n", level.getSubBoss());
                                        } else if (subBossHealth <= 10 && subBossHealth > 0) {
                                            for (Enemy enemyObject : enemies) enemyObject.setScared();
                                            System.out.println("--------------------------------------------------");
                                            System.out.printf("\t> The %s has become scared!\n", level.getSubBoss());
                                        } */
                                    }
                                    break;
                                case "2":
                                    player.decreaseNumHealthPotions();
                                    break;
                                case "3":
                                    System.out.println("--------------------------------------------------");
                                    System.out.printf("\tYou run away from from %s\n", level.getSubBoss());
                                    player.setTotalRun();
                                    continue PreBOSS;
                                default:
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("\tInvalid command!");
                                    break;
                            }
                        }

                        level.floorUp();
                        System.out.println("--------------------------------------------------");
                        System.out.println("\t # ****************************** # ");
                        System.out.printf("\t # The %s was defeated!\n", level.getSubBoss());
                        System.out.println("\t # ****************************** # ");
                        System.out.println("--------------------------------------------------");
                        System.out.printf("\t> You have entered floor %s & your health has been restored!\n", level.getFloor());

                        // Enemies get stronger
                        enemy.incrementLevel();
                        enemy.setStartingHP();
                        for (Enemy enemyObject : enemies) enemyObject.incrementLevel();

                        break;
                    case "2":
                        System.out.printf("You stay on floor %s\n", level.getFloor());
                        break;
                    case "3":
                        if (level.getFloor() >= 2) {
                            level = levels[level.getFloor() - 2];
                            System.out.printf("You've entered floor %s\n", level.getFloor());
                            for (Level levelObject : levels) levelObject.floorDown();

                            // Enemies get weaker
                            enemy.incrementLevel();
                            enemy.setStartingHP();
                            for (Enemy enemyObject : enemies) enemyObject.decrementLevel();

                        } else {
                            System.out.println("Invalid command!");
                        }
                        break;
                    case "0":
                        System.out.println("--------------------------------------------------");
                        System.out.println("You exit the dungeon, victorious in your adventure!");
                        new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
                    default:
                        System.out.println("Invalid command!");
                        break;
                }
            } else {
                System.out.printf("\t> You gained 1 EXP (%s of %s for level up)\n", player.getPlayerExp(), (player.getPlayerLevel() * 10));
            }
        }
        // Dragon Boss
        System.out.println("--------------------------------------------------");
        System.out.println("You've entered the heart of the dungeon!");
        System.out.println("The temperature of the room is densely hot...");
        System.out.println("From the darkness a fire kindles revealing a monstrous dragon!!");

        while (boss.getBossHealth() > 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("\tYour Level: " + player.getPlayerLevel());
            System.out.println("\tYour HP: " + player.getHealth() + " of " + player.getMaxPlayerHealth());
            System.out.println("\tDragon's HP: " + boss.getBossHealth() + " of 500");
            System.out.println("\n\tWhat would you like to do?");
            System.out.println("\t1. Attack");
            System.out.println("\t2. Drink Health Potion");
            System.out.println("\t3. Run");

            String input = in.receiveInput();
            switch (input) {
                case "1":
                    boss.setBossHealth(player.getAttackDamage());
                    player.decreaseHealth(boss.getAttackDamage());
                    player.setTotalDamageDealt();
                    player.setTotalDamageTaken(boss.getAttackDamage());

                    System.out.println("--------------------------------------------------");
                    System.out.printf("\t> You strike the Dragon for %s damage!", player.getAttackDamage());
                    System.out.printf("\n\t> The Dragon retaliates with fire breath resulting in %s damage!\n", boss.getAttackDamage());

                    if (player.getHealth() < 1) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("\t> You have taken too much damage, you are too weak to go on...");
                        new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
                    }
                    break;
                case "2":
                    player.decreaseNumHealthPotions();
                    break;
                case "3":
                    System.out.println("--------------------------------------------------");
                    System.out.println("\tYou run away from the Dragon, and will forever be remembered as the coward that you are!");
                    player.setTotalRun();
                    new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
                default:
                    System.out.println("--------------------------------------------------");
                    System.out.println("\tInvalid command!");
                    break;
            }
            if (boss.getBossHealth() < 1) {
                System.out.println("--------------------------------------------------");
                System.out.println("\t # ********************************************* # ");
                System.out.println("\t # Congratulations! You've defeated the Dragon!! # ");
                System.out.println("\t # ********************************************* # ");
                new Stats(player.getPlayerLevel(), player.getNumEnemiesEncountered(), player.getNumPotionsUsed(), player.getTotalRun(), player.getTotalDamageDealt(), player.getTotalDamageTaken());
            }
        }
    }
}
