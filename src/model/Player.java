package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Player {

    private static String nickname;
    private static String imagePath;
    private int totalScore;

    public static String getImagePath() {
        return imagePath;
    }

    public static void setImagePath(String imagePath) {
        Player.imagePath = imagePath;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    private static Player instance;
    private int gamesPlayed, gamesWon, gamesLost;


    public static Player getInstance(){
        if (instance == null) instance = new Player();
        return instance;
    }

    private Player(){
        String path = "src/playerdata/" + nickname + ".txt";
        File file = new File(path);
        if (!file.exists()){
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("0 0 0 null 0");
                this.gamesPlayed = 0;
                this.gamesLost = 0;
                this.gamesWon = 0;
                this.totalScore = 0;
                fileWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                Iterator<String> iterator = Files.lines(Path.of(path)).iterator();
                while (iterator.hasNext()){
                    String[] line = iterator.next().split(" ");
                    this.gamesPlayed = Integer.parseInt(line[0]);
                    this.gamesWon = Integer.parseInt(line[1]);
                    this.gamesLost = Integer.parseInt(line[2]);
                    this.totalScore = Integer.parseInt(line[4]);
                    imagePath = line[3];
                    if (imagePath.equals("null")) imagePath = null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static  String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname){
        Player.nickname = nickname;

    }

    public void addGamePlayed(){
        gamesPlayed+=1;
    }

    public void addWonGame(){
        gamesWon+=1;
    }

    public void addLostGame(){
        gamesLost+=1;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
