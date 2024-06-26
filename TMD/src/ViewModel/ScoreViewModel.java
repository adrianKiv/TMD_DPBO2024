package ViewModel;

import Model.Score;
import Model.TabelScore;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ScoreViewModel {
    // Referensi ke objek TabelScore untuk mengelola operasi database
    private TabelScore tabelScore;

    // Konstruktor untuk menginisialisasi objek ScoreViewModel
    public ScoreViewModel() {
        tabelScore = new TabelScore();
    }

    // Metode untuk memasukkan data skor ke database
    public boolean insertData(Score score) {
        return tabelScore.insertData(score);
    }

    // Metode untuk memperbarui data skor berdasarkan username lama
    public boolean updateData(Score score, String oldusername) {
        return tabelScore.updateData(oldusername, score);
    }

    // Metode untuk menghapus data skor berdasarkan username
    public boolean deleteData(String username) {
        return tabelScore.deleteData(username);
    }

    // Metode untuk mendapatkan skor berdasarkan username
    public Score getByUsername(String username) {
        return tabelScore.getByUsername(username);
    }

    // Metode untuk mengatur model tabel skor
    public DefaultTableModel setTable() {
        // Mendefinisikan nama kolom untuk tabel
        String[] columnNames = {"Username", "Score", "Up", "Down"};

        // Membuat model tabel dengan nama kolom dan tanpa baris
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Mendapatkan semua skor dari database
        List<Score> scores = tabelScore.getAllScores();
        // Iterasi melalui daftar skor
        for (Score score : scores) {
            // Membuat array objek untuk baris data
            Object[] rowData = {
                    score.getUsername(),
                    score.getScore(),
                    score.getUp(),
                    score.getDown()
            };
            // Menambahkan baris data ke model tabel
            tableModel.addRow(rowData);
        }
        // Mengembalikan model tabel yang siap ditampilkan
        return tableModel;
    }
}
