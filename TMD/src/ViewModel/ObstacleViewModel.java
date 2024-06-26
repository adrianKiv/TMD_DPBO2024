package ViewModel;
import Model.Obs;
import View.Game;
import java.util.ArrayList;
import static ViewModel.Cons.Option.*;
import java.util.Random;

public class ObstacleViewModel {
    // Variabel statis untuk mengacak posisi dan jenis rintangan
    private static Random random = new Random();
    private static int upperCount = 0; // Pelacakan jumlah generasi rintangan atas
    private static int lowerCount = 0; // Pelacakan jumlah generasi rintangan bawah

    // Method untuk menempatkan rintangan di game
    public static void placeObstacle(ArrayList<Obs> obs, Game game) {
        int minObstacleHeight = 60;
        int maxObstacleHeight = FRAME_HEIGHT - GAME_GAP - 50; // Pastikan ada ruang untuk gap game

        // Generasi tinggi acak untuk rintangan atas dan bawah
        int randomHeightUpper = 15; // Tinggi tetap untuk platform atas
        int randomHeightLower = random.nextInt(maxObstacleHeight - minObstacleHeight + 1) + minObstacleHeight;

        // Perhitungan posisi untuk rintangan atas dan bawah
        int lowerMinPosY = 35 + randomHeightLower + GAME_GAP; // Posisi minimum untuk rintangan bawah (termasuk tingginya dan gap)
        int upperMaxY = FRAME_HEIGHT - randomHeightUpper - GAME_GAP - 35; // Posisi maksimum untuk rintangan atas (termasuk tingginya dan gap)

        // Pastikan rintangan bawah tetap dalam frame dan mempertahankan gap minimal
        int lowerPosY = Math.max(lowerMinPosY, random.nextInt(FRAME_HEIGHT - lowerMinPosY) + lowerMinPosY);
        lowerPosY = Math.min(lowerPosY, FRAME_HEIGHT - randomHeightLower - 35); // Pastikan rintangan bawah tidak turun di bawah frame

        // Hitung posisi untuk rintangan atas berdasarkan posisi rintangan bawah
        int upperPosY = Math.min(random.nextInt(upperMaxY - 35) + 35, lowerPosY - randomHeightUpper - GAME_GAP);
        lowerPosY = FRAME_HEIGHT - randomHeightLower; // Rintangan bawah ditempatkan di bagian bawah frame

        // Pastikan rintangan bawah tetap dalam frame
        if (lowerPosY < upperPosY + randomHeightUpper + GAME_GAP) {
            lowerPosY = upperPosY + randomHeightUpper + GAME_GAP;
        }

        // Pastikan tinggi rintangan bawah lebih besar dari 20
        if (randomHeightLower <= 20) {
            randomHeightLower = 21;
            randomHeightUpper = FRAME_HEIGHT - GAME_GAP - randomHeightLower;
        }

        // Pastikan rintangan bawah tetap dalam frame dan sesuaikan jika perlu
        if (lowerPosY + randomHeightLower > FRAME_HEIGHT) {
            lowerPosY = FRAME_HEIGHT - randomHeightLower;
        }

        int pairId = obs.size() / 2;
        String type = generateRandomType();

        System.out.println(type);

        switch (type) {
            case "upper":
                Obs upperObs = new Obs(FRAME_WIDTH, upperPosY, OBSTACLE_WIDTH, randomHeightUpper, game.getPlatform1(), game.getBackgroundImage6(), pairId, true); // Tetapkan tinggi menjadi 15 untuk platform atas
                assignScoresBasedOnLength(upperObs, null);
                obs.add(upperObs);
                upperCount++;
                lowerCount = 0; // Reset hitungan rintangan bawah
                break;
            case "lower":
                Obs lowerObs = new Obs(FRAME_WIDTH, lowerPosY, OBSTACLE_WIDTH, randomHeightLower, game.getDownImage2(), pairId, false);
                assignScoresBasedOnLength(null, lowerObs);
                obs.add(lowerObs);
                lowerCount++;
                upperCount = 0; // Reset hitungan rintangan atas
                break;
            case "both":
                upperObs = new Obs(FRAME_WIDTH, upperPosY, OBSTACLE_WIDTH, randomHeightUpper, game.getPlatform1(), game.getBackgroundImage6(), pairId, true); // Tetapkan tinggi menjadi 15 untuk platform atas
                lowerObs = new Obs(FRAME_WIDTH, lowerPosY, OBSTACLE_WIDTH, randomHeightLower, game.getDownImage3(), pairId, false);
                assignScoresBasedOnLength(upperObs, lowerObs);
                obs.add(upperObs);
                obs.add(lowerObs);
                upperCount++;
                lowerCount++;
                break;
        }

        // Tukar tinggi jika hitungan atas atau bawah mencapai 3 secara berturut-turut
        if (upperCount == 3 || lowerCount == 3) {
            int tempHeight = randomHeightUpper;
            randomHeightUpper = randomHeightLower;
            randomHeightLower = tempHeight;
        }
    }

    // Method untuk menghitung skor berdasarkan tinggi rintangan
    private static int calculateScore(int height) {
        int a = height;
        a /= 55;
        a = (10 - a) * 9;
        a /= 2;

        System.out.println(a);
        return (a);
    }

    // Method untuk menetapkan skor berdasarkan panjang rintangan
    private static void assignScoresBasedOnLength(Obs upperObs, Obs lowerObs) {
        if (upperObs!= null && lowerObs!= null) {
            int scoreUpper = calculateScore((int) upperObs.getPosY());
            int scoreLower = calculateScore((int) lowerObs.getHeight());

            upperObs.setScoreValue(scoreUpper);
            lowerObs.setScoreValue(scoreLower);

        } else if (upperObs!= null) {
            int scoreUpper = calculateScore((int) upperObs.getPosY());
            upperObs.setScoreValue(scoreUpper);
        } else if (lowerObs!= null) {
            int scoreLower = calculateScore((int) lowerObs.getHeight());
            lowerObs.setScoreValue(scoreLower);
        }
    }

    // Method untuk menghasilkan jenis rintangan acak
    private static String generateRandomType() {
        int randomValue = random.nextInt(3); // Menghasilkan angka acak antara 0 dan 2
        switch (randomValue) {
            case 0:
                return "upper";
            case 1:
                return "lower";
            default:
                return "both";
        }
    }
}
