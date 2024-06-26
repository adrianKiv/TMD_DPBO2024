package ViewModel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicViewModel {
    // Method untuk memainkan audio
    public Clip playM(Clip clip, String filename){
        // Konstruktor
        AudioInputStream audioIn = null;
        try {
            // Mengambil audio input dari path pertama
            audioIn = AudioSystem.getAudioInputStream(new File("Assets/Audio/" + filename).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            try {
                // Jika gagal, coba mengambil audio input dari path kedua
                audioIn = AudioSystem.getAudioInputStream(new File("TMDV/src/Assets/Audio/" + filename).getAbsoluteFile());
            } catch (UnsupportedAudioFileException | IOException ex) {
                // Menangani exception jika file audio tidak didukung di kedua path
                ex.printStackTrace();
                return null;
            }
        }

        try {
            // Mendapatkan sumber audio
            clip = AudioSystem.getClip();

            // Membuka audio clip dan memuat sampel dari stream audio
            clip.open(audioIn);

            // Memulai audio
            clip.start();

            // Mengatur audio untuk berloop secara terus-menerus
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (LineUnavailableException | IOException e) {
            // Menangani exception lainnya
            e.printStackTrace();
        }
        return clip;
    }

    // Method untuk menghentikan audio
    public void stopM(Clip clip){
        clip.stop(); // Menghentikan audio
    }
}
