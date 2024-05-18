package enums;

public enum Levels {

    LEVEL1("src/levels/level1.txt","src/levels/level1enemy.txt","src/levels/level1powerups.txt"),
    LEVEL2("src/levels/level2.txt","src/levels/level2enemy.txt","src/levels/level2powerups.txt");

    public String getLevelPath() {
        return levelPath;
    }

    public String getEnemyPath() {
        return enemyPath;
    }

    public String getPowerUpsPath() {
        return powerUpsPath;
    }

    private final String levelPath,enemyPath,powerUpsPath;


    Levels(String levelPath, String enemyPath, String powerUpsPath){
        this.levelPath = levelPath;
        this.enemyPath = enemyPath;
        this.powerUpsPath = powerUpsPath;
    }


}
