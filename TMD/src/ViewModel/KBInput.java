package ViewModel;

import View.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static ViewModel.UpDown.*;

public class KBInput implements KeyListener {
    // Referensi ke objek Game dan UpDown
    private Game game;
    private UpDown updown;

    // Konstruktor untuk menginisialisasi objek KBInput dengan referensi ke Game dan UpDown
    public KBInput(Game game, UpDown updown){
        this.game = game;
        this.updown = updown;
    }

    // Implementasi method keyTyped dari interface KeyListener
    // Dibiarkan kosong karena tidak ada aksi spesifik yang perlu dilakukan ketika karakter diketik
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Implementasi method keyPressed dari interface KeyListener
    // Ketika tombol ditekan, memeriksa apakah tombol SPACE ditekan untuk memicu gameOver
    // Jika tidak, memanggil method keyPressed pada objek player
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            updown.gameOver(Username); // Memanggil method gameOver pada objek updown
        } else {
            player.keyPressed(e); // Memanggil method keyPressed pada objek player
        }
    }

    // Implementasi method keyReleased dari interface KeyListener
    // Memanggil method keyReleased pada objek player ketika tombol dilepas
    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e); // Memanggil method keyReleased pada objek player
    }

}
