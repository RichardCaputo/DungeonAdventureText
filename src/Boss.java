public class Boss {

    private String boss;
    private String weapon;
    private int bossHealth;
    private int attackDamage;

    Boss(String boss, String weapon, int bossHealth, int attackDamage) {
        this.boss = boss;
        this.weapon = weapon;
        this.bossHealth = bossHealth;
        this.attackDamage = attackDamage;
    }

    public String getBoss() {
        return this.boss;
    }
    public String getWeapon() {
        return this.weapon;
    }
    public int getBossHealth() {
        return this.bossHealth;
    }
    public int setBossHealth(int playerAttackDamage) {
        return this.bossHealth -= playerAttackDamage;
    }
    public int getAttackDamage() {
        return this.attackDamage;
    }
}
