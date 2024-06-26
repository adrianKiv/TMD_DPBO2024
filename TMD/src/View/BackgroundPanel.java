package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// Kelas BackgroundPanel untuk menggambar background gambar
class BackgroundPanel extends JPanel {
    private BufferedImage image; // Variabel untuk menyimpan gambar yang akan digunakan sebagai background

    // Konstruktor yang menerima path gambar sebagai parameter
    public BackgroundPanel(String imagePath) {
        setOpaque(false); // Mengatur panel agar transparan sehingga gambar background dapat terlihat
        
        // Mencoba memuat gambar dari path yang diberikan
        try {
            image = ImageIO.read(getClass().getResource(imagePath)); // Menggunakan ImageIO untuk membaca file gambar
        } catch (IOException e) { // Menangkap IOException jika terjadi kesalahan saat membaca file
            e.printStackTrace(); // Mencetak stack trace kesalahan
        }
    }

    // Metode override untuk menggambar komponen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Memanggil metode paintComponent dari superclass untuk melakukan setup awal
        
        // Cek jika gambar berhasil dimuat
        if (image!= null) {
            // Menggambar gambar pada panel dengan ukuran panel
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
            // Parameter: gambar, posisi x, posisi y, lebar, tinggi, observer (this)
        }
    }
}
