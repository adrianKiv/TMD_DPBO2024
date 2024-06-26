// Paket tempat kelas GameObject berada
package Model;

// Impor kelas yang dibutuhkan dari Java AWT
import java.awt.Graphics;
import java.awt.Rectangle;

// Deklarasi kelas abstrak GameObject yang tidak bisa diinstansiasi langsung
public abstract class GameObject {

    // Variabel untuk menyimpan posisi objek
    protected float x;
    protected float y;
    
    // Variabel untuk menyimpan dimensi objek
    protected int width;
    protected int height;
    
    // Variabel untuk menyimpan kotak deteksi tabrakan objek
    protected Rectangle collisionBox;

    // Konstruktor untuk membuat objek GameObject dengan posisi dan dimensi tertentu
    public GameObject(float x, float y, int width, int height) {
        this.x = x; // Inisialisasi posisi x
        this.y = y; // Inisialisasi posisi y
        this.width = width; // Inisialisasi lebar objek
        this.height = height; // Inisialisasi tinggi objek
        
        // Membuat kotak deteksi tabrakan dengan posisi dan ukuran yang sesuai
        this.collisionBox = new Rectangle((int)x, (int)y, width, height);
    }

    // Metode untuk memperbarui posisi kotak deteksi tabrakan sesuai dengan posisi objek
    protected void updateCollisionBox(){
        this.collisionBox.x = (int) x; // Memperbarui posisi x kotak deteksi tabrakan
        this.collisionBox.y = (int) y; // Memperbarui posisi y kotak deteksi tabrakan
    }

    // Getter untuk mendapatkan kotak deteksi tabrakan objek
    public Rectangle getCollisionBox() {
        return this.collisionBox; // Mengembalikan kotak deteksi tabrakan
    }

    // Metode abstrak yang harus diimplementasikan oleh kelas turunan untuk menggambar objek
    public abstract void render(Graphics g);
}
