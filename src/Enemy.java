public class Enemy {

    private String type;
    private String weapon;
    private int level;
    private boolean angry;
    private boolean scared;

    Enemy(String type, String weapon, int level, boolean angry, boolean scared) {
        this.type = type;
        this.weapon = weapon;
        this.level = level;
        this.angry = angry;
        this.scared = scared;
    }

    public String getType() {
        return type;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isAngry() {
        return angry;
    }

    public boolean isScared() {
        return scared;
    }

    public void incrementLevel() {
        this.level++;
    }
}
