package Model;

import java.awt.*;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class Obs extends GameObject {

    private float velocityX; // Kecepatan horizontal objek
    private int pairId; // ID pasangan objek (untuk objek atas dan bawah)
    private boolean isUpperobs; // Menandai apakah objek ini adalah bagian atas atau bawah
    private Image image1; // Gambar pertama (opsional)
    private Image image2; // Gambar kedua (digunakan untuk rendering utama)
    private float velocityY; // Kecepatan vertikal objek
    private boolean touched = false; // Status sentuhan objek
    private int scoreValue; // Nilai skor yang terkait dengan objek

    // Konstruktor untuk objek dengan satu gambar
    public Obs(float x, float y, int width, int height, Image image, int pairId, boolean isUpperobs) {
        super(x, y, width, height); // Panggil konstruktor superclass
        this.image1 = image; // Set gambar pertama
        this.pairId = pairId; // Set ID pasangan
        this.isUpperobs = isUpperobs; // Set status objek atas/bawah
        this.velocityX = -3; // Set kecepatan horizontal
        this.scoreValue = 0; // Set nilai skor awal
    }

    // Konstruktor untuk objek dengan dua gambar
    public Obs(float x, float y, int width, int height, Image image1, Image image2, int pairId, boolean isUpperobs) {
        super(x, y, width, height); // Panggil konstruktor superclass
        this.image1 = image1; // Set gambar pertama
        this.image2 = image2; // Set gambar kedua
        this.pairId = pairId; // Set ID pasangan
        this.isUpperobs = isUpperobs; // Set status objek atas/bawah
        this.velocityX = -3; // Set kecepatan horizontal
        this.scoreValue = 0; // Set nilai skor awal
    }

    // Metode untuk menggambar objek
    public void draw(Graphics2D g2d) {
        // Gambar obstacle utama
        g2d.drawImage(image2, (int) x, (int) y, width, height + 35, null);

        // Jika ini adalah lower obstacle dan ada gambar tambahan
        if (!isUpperobs && image1!= null) { // Perbaikan kondisi untuk menampilkan gambar tambahan hanya jika bukan upper obstacle
            int xPosition = (int) x;
            int yPosition = (int) (y + height);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Atur transparansi
            g2d.drawImage(image1, xPosition, yPosition, width, height, null); // Gambar gambar tambahan
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Kembalikan transparansi normal
        }
    }

    // Metode untuk memperbarui posisi objek
    public void update() {
        x += velocityX; // Perbarui posisi x
        y += velocityY; // Perbarui posisi y
        updateCollisionBox(); // Perbarui kotak deteksi tabrakan
    }

    // Metode untuk merender objek dan nilai skornya
    @Override
    public void render(Graphics g) {
        g.fillRect((int) x, (int) y, width, height); // Gambar kotak hitam sebagai background
        if (image1!= null) { // Jika ada gambar pertama, gambarkan
            g.drawImage(image1, (int) x, (int) y, width, height, null);
        }
        g.setFont(new Font("Arial", Font.BOLD, 12)); // Atur font teks
        String scoreText = String.valueOf(scoreValue); // Ubah nilai skor menjadi teks
        int textWidth = g.getFontMetrics().stringWidth(scoreText); // Hitung lebar teks
        int textX = (int) (x + (width - textWidth) / 2); // Hitung posisi x teks
        int textY = (int) (y + height + g.getFontMetrics().getHeight()); // Hitung posisi y teks
        if (isUpperobs) { // Jika ini adalah upper obstacle, atur posisi teks di bawah
            g.drawString(scoreText, textX, (int) (y + height - g.getFontMetrics().getHeight() + 50));
        } else { // Jika ini adalah lower obstacle, atur posisi teks di atas
            g.drawString(scoreText, textX, (int) (y - 2));
        }
    }

    // Getter dan Setter untuk properti-properti objek
    public int getScoreValue() { return scoreValue; }
    public float getX() { return x; }
    public float getPosY() { return y; }
    public float getVelocityX() { return velocityX; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getVelocityY() { return velocityY; }
    public boolean isTouched() { return touched; }
    public void setTouched(boolean touched) { this.touched = touched; }
    public float getY() { return y; }
    public void setVelocityX(float velocityX) { this.velocityX = velocityX; }
    public int getPairId() { return pairId; }
    public void setPairId(int pairId) { this.pairId = pairId; }
    public boolean isUpperobs() { return isUpperobs; }
    public void setUpperobs(boolean upperobs) { isUpperobs = upperobs; }
    public Image getImage1() { return image1; }
    public void setImage1(Image image1) { this.image1 = image1; }
    public Image getImage2() { return image2; }
    public void setImage2(Image image2) { this.image2 = image2; }
    public void setVelocityY(float velocityY) { this.velocityY = velocityY; }
    public void setScoreValue(int scoreValue) { this.scoreValue = scoreValue; }
}
