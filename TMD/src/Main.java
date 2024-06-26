/*

    Saya Adrian Mulianto [2200939] mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
    Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya 
    tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

*/

/*

Programmer : Adrian Mulianto
Email      : adrianmulianto@gmail.com
Git hub    : https://github.com/adrianKiv

*/

//  @Author Adrian Mulianto

import View.Menu;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Membuat instance dari kelas Menu yang merupakan representasi dari view atau UI
        Menu window = new Menu();

        // Mengatur ukuran window menjadi 680x460 pixel
        window.setSize(680, 460);

        // Menempatkan window tepat di tengah layar
        window.setLocationRelativeTo(null);

        // Mengatur panel utama sebagai content pane dari window
        window.setContentPane(window.mainPanel);

        // Mengubah warna background dari content pane menjadi abu-abu terang
        window.getContentPane().setBackground(Color.lightGray);

        // Membuat window menjadi visible atau terlihat
        window.setVisible(true);

        // Menetapkan aksi default saat window ditutup adalah EXIT_ON_CLOSE, yang akan mengakhiri aplikasi JVM
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
