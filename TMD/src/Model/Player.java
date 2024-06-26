package Model;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private float posX; // Posisi X player
    private float posY; // Posisi Y player
    private int width; // Lebar player
    private int height; // Tinggi player
    private Image image; // Gambar player
    private float velocityY; // Kecepatan vertical player
    private float velocityX; // Kecepatan horizontal player
    private int dx, dy; // Perubahan posisi X dan Y per frame
    private String direction = "idle"; // Arah gerakan player
    private boolean isJumping = false; // Status lompat player
    private boolean isInAir = false; // Status udara player

    // Konstruktor untuk membuat objek Player
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX; // Inisialisasi posisi X
        this.posY = posY; // Inisialisasi posisi Y
        this.width = width; // Inisialisasi lebar
        this.height = height; // Inisialisasi tinggi
        this.image = image; // Inisialisasi gambar
    }

    // Setter untuk kecepatan vertical
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    // Getter dan Setter untuk posisi X
    public float getPosX() { return posX; }
    public void setPosX(float posX) { this.posX = posX; }

    // Getter dan Setter untuk posisi Y
    public float getPosY() { return posY; }
    public void setPosY(float posY) { this.posY = posY; }

    // Getter dan Setter untuk lebar dan tinggi
    public float getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public float getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    // Getter dan Setter untuk gambar
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    // Getter untuk kecepatan vertical dan horizontal
    public float getVelocityY() { return velocityY; }
    public float getVelocityX() { return velocityX; }

    // Getter dan Setter untuk perubahan posisi
    public int getDx() { return dx; }
    public void setDx(int dx) { this.dx = dx; }
    public int getDy() { return dy; }
    public void setDy(int dy) { this.dy = dy; }

    // Setter untuk arah gerakan
    public void setDirection(String direction) { this.direction = direction; }

    // Setter untuk status lompat dan udara
    public void setJumping(boolean jumping) { isJumping = jumping; }
    public void setInAir(boolean inAir) { isInAir = inAir; }

    // Metode untuk memperbarui posisi player berdasarkan perubahan posisi
    public void update() {
        posX += dx; // Perbarui posisi X
        posY += dy; // Perbarui posisi Y
    }

    // Metode untuk menangani input tombol 
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Logika untuk tombol UP
        if (key == KeyEvent.VK_UP) {
            direction = "up";
            isJumping = true;
            isInAir = false;
            dy = -16; // Gerak ke atas
        }

        // Logika untuk tombol DOWN
        if (key == KeyEvent.VK_DOWN) {
            direction = "down";
            isJumping = false;
            isInAir = true;
            dy = 6; // Gerak ke bawah
        }

        // Logika untuk tombol LEFT
        if (key == KeyEvent.VK_LEFT) {
            direction = "left";
            isJumping = false;
            isInAir = false;
            dx = -6; // Gerak ke kiri
        }

        // Logika untuk tombol RIGHT
        if (key == KeyEvent.VK_RIGHT) {
            direction = "right";
            dx = 6; // Gerak ke kanan
            isJumping = false;
            isInAir = false;
        }
    }

    // Metode untuk menangani input tombol lepas
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Logika untuk tombol UP
        if (key == KeyEvent.VK_UP) {
            isJumping = false;
            isInAir = true;
            dy = 8; // Gravitasi saat tidak melompat
        }

        // Logika untuk tombol DOWN
        if (key == KeyEvent.VK_DOWN) {
            dy = -8; // Gerak ke atas saat tombol DOWN dilepas
        }

        // Logika untuk tombol LEFT atau RIGHT
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0; // Hentikan gerakan horizontal
        }

        // Logika untuk tombol LEFT atau RIGHT agar player tetap terjatuh
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dy = 8;
        }

        direction = "idle"; // Set arah menjadi idle saat tombol dilepas
    }

    // Getter untuk arah gerakan, status lompat, dan status udara
    public String getDirection() { return direction; }
    public boolean isJumping() { return isJumping; }
    public boolean isInAir() { return isInAir; }

    // Metode untuk mendapatkan batas-batas player sebagai Rectangle
    public Rectangle getBounds() {
        return new Rectangle((int) posX, (int) posY, width, height);
    }

    // Setter untuk kecepatan horizontal
    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }
}
