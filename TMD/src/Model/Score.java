package Model;

public class Score {
    private String username; // Nama pengguna yang dicatat
    private int score; // Skor total yang dicapai pengguna
    private int up; // Jumlah interaksi positif (misalnya suka atau upvote)
    private int down; // Jumlah interaksi negatif (misalnya tidak suka atau downvote)

    // Konstruktor untuk membuat objek Score dengan data awal
    public Score(String username, int score, int up, int down) {
        this.username = username; // Inisialisasi nama pengguna
        this.score = score; // Inisialisasi skor
        this.up = up; // Inisialisasi jumlah upvote
        this.down = down; // Inisialisasi jumlah downvote
    }

    // Getter untuk nama pengguna
    public String getUsername() {
        return username;
    }

    // Getter untuk skor
    public int getScore() {
        return score;
    }

    // Setter untuk skor
    public void setScore(int score) {
        this.score = score;
    }

    // Getter untuk jumlah upvote
    public int getUp() {
        return up;
    }

    // Setter untuk jumlah upvote
    public void setUp(int up) {
        this.up = up;
    }

    // Getter untuk jumlah downvote
    public int getDown() {
        return down;
    }

    // Setter untuk jumlah downvote
    public void setDown(int down) {
        this.down = down;
    }
}
