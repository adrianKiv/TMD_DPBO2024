package View;

import javax.swing.*;
import java.awt.*;
import ViewModel.UpDown;

public class Game extends JPanel {
    // Deklarasi variabel untuk menyimpan gambar-gambar yang akan digunakan dalam game
    Image backgroundImage1;
    Image backgroundImage2;
    Image backgroundImage3;
    Image backgroundImage4;
    Image backgroundImage5;
    Image backgroundImage6; // Tambahan untuk gambar lain
    Image downImage2;
    Image downImage3;
    Image PlayerImagerun; // Gambar animasi berlari
    Image PlayerImageidle; // Gambar idle
    Image PlayerImagejump; // Gambar saat melompat
    Image PlayerImagelanding; // Gambar saat mendarat
    Image PlayerImagemidair; // Gambar saat di udara
    Image platform1; // Gambar platform
    Image water; // Gambar air
    Image rope; // Gambar tali
    private UpDown updown; // Referensi ke objek UpDown untuk kontrol input

    // Konstruktor untuk memuat semua gambar yang dibutuhkan
    public Game() {
        try {
            // Memuat gambar-gambar dari direktori Assets/Images menggunakan ImageIcon dan mengambil Image-nya
            backgroundImage1 = new ImageIcon(getClass().getResource("../Assets/Images/plx-1.png")).getImage();
            backgroundImage2 = new ImageIcon(getClass().getResource("../Assets/Images/plx-2.png")).getImage();
            backgroundImage3 = new ImageIcon(getClass().getResource("../Assets/Images/plx-3.png")).getImage();
            backgroundImage4 = new ImageIcon(getClass().getResource("../Assets/Images/plx-4.png")).getImage();
            backgroundImage5 = new ImageIcon(getClass().getResource("../Assets/Images/plx-5.png")).getImage();
            backgroundImage6 = new ImageIcon(getClass().getResource("../Assets/Images/rope22.png")).getImage(); // Gambar tambahan
            downImage2 = new ImageIcon(getClass().getResource("../Assets/Images/blk.png")).getImage();
            downImage3 = new ImageIcon(getClass().getResource("../Assets/Images/blk.png")).getImage();
            PlayerImagerun = new ImageIcon(getClass().getResource("../Assets/Images/run.gif")).getImage();
            PlayerImageidle = new ImageIcon(getClass().getResource("../Assets/Images/idle.gif")).getImage();
            PlayerImagejump = new ImageIcon(getClass().getResource("../Assets/Images/jump.png")).getImage();
            PlayerImagelanding = new ImageIcon(getClass().getResource("../Assets/Images/landing.png")).getImage();
            PlayerImagemidair = new ImageIcon(getClass().getResource("../Assets/Images/midair.gif")).getImage();
            platform1 = new ImageIcon(getClass().getResource("../Assets/Images/plt.png")).getImage();
            water = new ImageIcon(getClass().getResource("../Assets/Images/water.png")).getImage();
            rope = new ImageIcon(getClass().getResource("../Assets/Images/rope3.png")).getImage();
        } catch (NullPointerException e) { // Menangkap NullPointerException jika ada gambar yang tidak ditemukan
            System.out.println("One or more images could not be found. Please check the file paths and try again.");
        }
    }

    // Metode setter untuk mengatur referensi objek UpDown
    public void setUpDown(UpDown updown) {
        this.updown = updown;
    }

    // Seri getter untuk mengakses gambar-gambar yang telah dimuat
    public Image getBackgroundImage1() { return backgroundImage1; }
    public Image getBackgroundImage2() { return backgroundImage2; }
    public Image getBackgroundImage3() { return backgroundImage3; }
    public Image getBackgroundImage4() { return backgroundImage4; }
    public Image getBackgroundImage5() { return backgroundImage5; }
    public Image getBackgroundImage6() { return backgroundImage6; } // Getter untuk gambar tambahan
    public Image getDownImage2() { return downImage2; }
    public Image getDownImage3() { return downImage3; }
    public Image getPlayerImagerun() { return PlayerImagerun; }
    public Image getPlayerImageidle() { return PlayerImageidle; }
    public Image getPlayerImagejump() { return PlayerImagejump; }
    public Image getPlayerImagelanding() { return PlayerImagelanding; }
    public Image getPlayerImagemidair() { return PlayerImagemidair; }
    public Image getPlatform1() { return platform1; }
    public Image getWater() { return water; }
    public Image getRope() { return rope; }
}
