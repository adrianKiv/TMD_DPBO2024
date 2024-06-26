package ViewModel;

import Model.Obs;
import Model.Player;
import Model.Score;
import View.Game;
import View.Menu;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import static ViewModel.Cons.Option.*;

public class UpDown extends JPanel implements ActionListener {
    // Deklarasi variabel kelas static untuk player
    static Player player;
    
    // Timer untuk loop game dan timer untuk menempatkan rintangan
    Timer gameLoop;
    Timer obstacleTimer;
    
    // Daftar rintangan dalam game
    ArrayList<Obs> obs;
    
    // Koordinat awal player
    int playerStartPostX = FRAME_WIDTH / 8;
    int playerStartPostY = FRAME_HEIGHT / 2;
    
    // Variabel untuk menyimpan skor dan hitungan rintangan
    private static int score = 0;
    private static int upCount = 0;
    private static int downCount = 0;
    
    // Username saat ini dan referensi ke objek game
    static String Username = "Unknown";
    private Game game;
    
    // Input keyboard dan view untuk menampilkan rintangan
    private KBInput kbInput;
    private ObstacleViewModel obstacleViewModel;
    
    // Klip untuk musik latar belakang
    public Clip Music;

    // Konstruktor untuk inisialisasi game
    public UpDown(String username, Game game) {
        // Menetapkan username dan referensi ke game
        Username = username;
        this.game = game;
        
        // Inisialisasi input keyboard dan daftar rintangan
        this.kbInput = new KBInput(game, this);
        obs = new ArrayList<>();
        this.obstacleViewModel = new ObstacleViewModel();
        
        // Set ukuran preferensi dan fokusabilitas panel
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setFocusable(true);
        addKeyListener(kbInput);
        
        // Inisialisasi player dengan koordinat awal
        player = new Player(playerStartPostX, playerStartPostY, PLAYER_WIDTH, PLAYER_HEIGHT, game.getPlayerImagerun());
        
        // Mulai pemutaran musik latar belakang
        MusicViewModel bgm = new MusicViewModel();
        Music = bgm.playM(this.Music, "gameaudio.wav");
        
        // Timer untuk menempatkan rintangan secara acak
        obstacleTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObstacleViewModel.placeObstacle(obs, game);
            }
        });
        obstacleTimer.start();
        
        // Loop game untuk pembaruan setiap detik
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    // Metode override untuk menggambar komponen pada panel
    @Override
    public void paintComponent(Graphics g) {
        // Memanggil metode draw untuk menggambar isi panel
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Mengubah tipe objek Graphics menjadi Graphics2D untuk mendapatkan fitur tambahan
        Graphics2D g2d = (Graphics2D) g;
    
        // Menampilkan Background Image 1 secara penuh (tidak transparan)
        if (game.getBackgroundImage1()!= null) {
            g2d.drawImage(game.getBackgroundImage1(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        } else {
            // Jika Background Image 1 tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 1 not found", 10, 20);
        }
    
        // Menampilkan Background Image 2 dengan transparansi sebesar 50%
        if (game.getBackgroundImage2()!= null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(game.getBackgroundImage2(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        } else {
            // Jika Background Image 2 tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 2 not found", 10, 40);
        }
    
        // Menampilkan Background Image 3 dengan transparansi sebesar 50%
        if (game.getBackgroundImage3()!= null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(game.getBackgroundImage3(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        } else {
            // Jika Background Image 3 tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 3 not found", 10, 60);
        }
    
        // Menampilkan Background Image 4 dengan transparansi sebesar 50%
        if (game.getBackgroundImage4()!= null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(game.getBackgroundImage4(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        } else {
            // Jika Background Image 4 tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 4 not found", 10, 80);
        }
    
        // Menampilkan Background Image 5 dengan transparansi sebesar 50%
        if (game.getBackgroundImage5()!= null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(game.getBackgroundImage5(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        } else {
            // Jika Background Image 5 tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 5 not found", 10, 80);
        }
    
        // Mengatur komposisi kembali ke normal tanpa transparansi
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    
        // Menentukan gambar pemain berdasarkan status dan arah
        Image playerImageToDraw = null;
        if (player.isJumping()) {
            playerImageToDraw = game.getPlayerImagejump();
        } else if (player.isInAir()) {
            playerImageToDraw = game.getPlayerImagemidair();
        } else {
            switch (player.getDirection()) {
                case "left":
                case "right":
                    playerImageToDraw = game.getPlayerImagerun();
                    break;
                case "up":
                    playerImageToDraw = game.getPlayerImagejump();
                    break;
                case "down":
                    playerImageToDraw = game.getPlayerImagelanding();
                    break;
                default:
                    playerImageToDraw = game.getPlayerImageidle();
                    break;
            }
        }
    
        // Menggambar gambar pemain jika ditemukan
        if (playerImageToDraw!= null) {
            g2d.drawImage(playerImageToDraw, (int) player.getPosX(), (int) player.getPosY(), null);
        } else {
            // Jika gambar pemain tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Player image not found", 10, 100);
        }
    
        // Menggambar rintangan dalam game
        for (Obs obs : this.obs) {
            obs.render(g);
            obs.draw((Graphics2D) g);
        }
    
        // Menampilkan kotak hitam semi-transparan dengan skor, "up", dan "down"
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(10, 10, 120, 70);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(10, 10, 120, 70);
        g2d.drawString("Score: " + score, 20, 30);
        g2d.drawString("Up: " + upCount, 20, 50);
        g2d.drawString("Down: " + downCount, 20, 70);
    
        // Menampilkan Background Image 6 dengan ukuran dan posisi tertentu
        if (game.getBackgroundImage6()!= null) {
            int imageWidth = 120;
            int imageHeight = 150;
            int xPosition = 10;
            int yPosition = 80;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(game.getBackgroundImage6(), xPosition, yPosition, imageWidth, imageHeight, null);
        } else {
            // Jika Background Image 6 tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 6 not found", 10, 80);
        }
    
        // Menampilkan Water Image dengan ukuran dan posisi tertentu
        if (game.getWater()!= null) {
            int imageWidth = FRAME_WIDTH;
            int imageHeight = 20;
            int xPosition = 0;
            int yPosition = FRAME_HEIGHT - imageHeight;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(game.getWater(), xPosition, yPosition, imageWidth, imageHeight, null);
        } else {
            // Jika Water Image tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 7 not found", 10, 80);
        }
    
        // Menggambar Rope dengan rotasi 45 derajat
        if (game.getRope()!= null) {
            int imageWidth = 40;
            int imageHeight = 270;
            int xPositionRight = FRAME_WIDTH;
            int yPositionTop = -40;
            AffineTransform transform = new AffineTransform();
            transform.translate(xPositionRight, yPositionTop);
            transform.rotate(Math.toRadians(45), imageWidth / 2, imageHeight / 2);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            Graphics2D g2dRotated = (Graphics2D) g2d.create();
            g2dRotated.setTransform(transform);
            g2dRotated.drawImage(game.getRope(), 0, 0, imageHeight, imageWidth, null);
            g2dRotated.dispose();
        } else {
            // Jika Rope Image tidak ditemukan, menampilkan pesan error
            g2d.setColor(Color.RED);
            g2d.drawString("Background image 7 not found", FRAME_WIDTH - 160, 80);
        }
    }

    @Override
public void actionPerformed(ActionEvent e) {
    player.update(); // Memperbarui posisi pemain

    // Mengecek apakah pemain telah keluar dari frame
    if (player.getPosX() < -30 || player.getPosX() > FRAME_WIDTH + 30 || player.getPosY() < -25 || player.getPosY() > FRAME_HEIGHT + 45) {
        MusicViewModel bgm = new MusicViewModel();
        bgm.stopM(Music); // Menghentikan musik latar belakang
        gameOver(Username); // Menampilkan layar game over
    }

    ArrayList<Obs> toRemove = new ArrayList<>(); // Daftar untuk menyimpan objek yang perlu dihapus
    for (Obs obs : this.obs) { // Iterasi melalui setiap objek dalam daftar objek
        obs.update(); // Memperbarui posisi hambatan

        // Jika hambatan bergerak keluar dari frame, tambahkan ke daftar untuk dihapus
        if (obs.getX() + obs.getWidth() < 0) {
            toRemove.add(obs);
        }

        // Mendapatkan batas pemain dan hambatan untuk deteksi tabrakan
        Rectangle playerBounds = player.getBounds();
        Rectangle obstacleBounds = obs.getCollisionBox();

        // Jika pemain bertabrakan dengan hambatan
        if (playerBounds.intersects(obstacleBounds)) {

            // Menghitung tumpukan antara pemain dan hambatan
            float overlapX = Math.min(player.getPosX() + player.getWidth() - obs.getX(), obs.getX() + obs.getWidth() - player.getPosX());
            float overlapY = Math.min(player.getPosY() + player.getHeight() - obs.getY(), obs.getY() + obs.getHeight() - player.getPosY());

            // Menentukan sisi mana dari hambatan yang disentuh oleh pemain
            boolean isLeft = player.getPosX() < obs.getX() && overlapX <= overlapY;
            boolean isRight = player.getPosX() > obs.getX() && overlapX <= overlapY;
            boolean isBottom = player.getPosY() < obs.getY() && overlapY <= overlapX;
            boolean isTop = player.getPosY() > obs.getY() && overlapY <= overlapX;

            // Menyesuaikan posisi pemain berdasarkan sisi hambatan yang disentuh
            if (isLeft) {
                player.setPosX(obs.getX() - player.getWidth() + 10);
                player.setVelocityX(obs.getVelocityX());
                player.setVelocityX(0);
            }
            if (isRight) {
                player.setPosX(obs.getX() + obs.getWidth() + 5);
                player.setVelocityX(0);
            }

            boolean isUpperObstacle = obs.isUpperobs(); // Asumsikan ada metode isUpperObstacle()
            if (isBottom && isUpperObstacle) {
                player.setPosY(obs.getY() - player.getHeight() + 3);
                player.setVelocityY(obs.getVelocityY()); // Ikuti gerakan vertikal hambatan
                if (!obs.isTouched()) {
                    upCount++; // Increment counter jika pemain menabrak bawah
                    score += obs.getScoreValue(); // Tambahkan nilai skor dari hambatan
                    obs.setTouched(true); // Marking hambatan sebagai "disentuh"
                }
            } else if (isBottom){
                player.setPosY(obs.getY() - player.getHeight() + 3);
                player.setVelocityY(obs.getVelocityY()); // Ikuti gerakan vertikal hambatan
                if (!obs.isTouched()) {
                    downCount++; // Increment counter jika pemain menabrak bawah
                    score += obs.getScoreValue(); // Tambahkan nilai skor dari hambatan
                    obs.setTouched(true); // Marking hambatan sebagai "disentuh"
                }
            }

            if (isTop) {
                player.setPosY(obs.getY() + obs.getHeight() + 3);
                player.setVelocityY(obs.getVelocityY()); // Ikuti gerakan vertikal hambatan
                if (!obs.isTouched()) {
                    upCount++; // Increment counter jika pemain menabrak atas
                    score += obs.getScoreValue(); // Tambahkan nilai skor dari hambatan
                    obs.setTouched(true); // Marking hambatan sebagai "disentuh"
                }
            }

            // Gerakkan pemain seiring dengan hambatan jika menyentuh sisi manapun
            player.setPosX(player.getPosX() + obs.getVelocityX());
            player.setPosY(player.getPosY() + obs.getVelocityY());
        }
    }
    obs.removeAll(toRemove); // Hapus objek yang sudah keluar dari frame
    repaint(); // Perbarui tampilan
}



    void gameOver(String username) {
        // Inisialisasi model untuk mengendalikan musik
        MusicViewModel bgm = new MusicViewModel();
        bgm.stopM(Music);
        // Mainkan audio efek kematian
        Music = bgm.playM(this.Music, "dieaudio.wav");
    
        // Hentikan loop game dan timer hambatan
        gameLoop.stop();
        obstacleTimer.stop();
    
        // Inisialisasi model untuk mengelola skor
        ScoreViewModel scoreViewModel = new ScoreViewModel();
    
        // Cek apakah username sudah ada di database
        Score currentdata = scoreViewModel.getByUsername(username);
    
        if (currentdata!= null) {
            // Jika username sudah ada, update data skor
            currentdata.setScore(score);
            currentdata.setUp(upCount);
            currentdata.setDown(downCount);
            // Panggil method untuk memperbarui data yang ada dengan username yang sama
            scoreViewModel.updateData(currentdata, username);
        }
    
        // Tampilkan dialog pesan game over dengan detail skor
        JOptionPane.showMessageDialog(this, "Game Over!\nUsername: " + username + "\nScore: " + score + "\nUp: " + upCount + "\nDown: " + downCount);
    
        // Tutup frame game yang selesai
        SwingUtilities.getWindowAncestor(this).dispose();
    
        // Hentikan musik yang sedang diputar
        bgm.stopM(Music);
    
        // Buka menu utama setelah game selesai
        Menu window = new Menu();
        window.setSize(680, 460);
        window.setLocationRelativeTo(null);
        window.setContentPane(window.mainPanel);
        window.getContentPane().setBackground(Color.lightGray);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Reset nilai skor, hitungan naik (upCount), dan hitungan turun (downCount)
        score = 0;
        upCount = 0;
        downCount = 0;
    }    

    public static void gameStart(String username) {
        // Inisialisasi ScoreViewModel
        ScoreViewModel scoreViewModel = new ScoreViewModel();

        // Periksa apakah username sudah ada di database
        Score currentdata = scoreViewModel.getByUsername(username);

        if (currentdata == null) {
            // Jika username belum ada, tambahkan data baru
            currentdata = new Score(username, 0, 0, 0); // Nilai awal untuk score, upCount, dan downCount
            scoreViewModel.insertData(currentdata); // Tambahkan data baru
        } else {
            // Jika username sudah ada, ambil nilai yang ada
            score = currentdata.getScore();
            upCount = currentdata.getUp();
            downCount = currentdata.getDown();
        }
    }
}
