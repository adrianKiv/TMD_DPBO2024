package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabelScore extends Database {

    // Metode untuk memasukkan data skor baru ke dalam database
    public boolean insertData(Score score) {
        if (score.getUsername().isEmpty()) { // Cek jika nama pengguna kosong
            return false; // Jika ya, hentikan operasi
        }

        // Query SQL untuk memeriksa keberadaan nama pengguna
        String checkSql = "SELECT * FROM tscore WHERE username =?;";
        try (ResultSet rs = selectQuery(checkSql, score.getUsername())) { // Jalankan query
            if (rs.next()) { // Jika pengguna sudah ada
                return false; // Hentikan operasi
            }
        } catch (SQLException e) { // Tangani kesalahan SQL
            e.printStackTrace();
            return false; // Gagal
        }

        // Query SQL untuk memasukkan data baru
        String insertSql = "INSERT INTO tscore (username, score, up, down) VALUES (?,?,?,?);";
        // Jalankan operasi insert dan cek hasilnya
        return InsertUpdateDelete(insertSql, score.getUsername(), String.valueOf(score.getScore()),
                String.valueOf(score.getUp()), String.valueOf(score.getDown())) > 0;
    }

    // Metode untuk memperbarui data skor yang ada
    public boolean updateData(String oldUsername, Score score) {
        if (score.getUsername().isEmpty()) { // Cek jika nama pengguna kosong
            return false; // Jika ya, hentikan operasi
        }

        // Query SQL untuk memperbarui data skor
        String updateSql = "UPDATE tscore SET username =?, score =?, up =?, down =? WHERE username =?;";
        // Jalankan operasi update dan cek hasilnya
        return InsertUpdateDelete(updateSql, score.getUsername(), String.valueOf(score.getScore()),
                String.valueOf(score.getUp()), String.valueOf(score.getDown()), oldUsername) > 0;
    }

    // Metode untuk menghapus data skor berdasarkan nama pengguna
    public boolean deleteData(String username) {
        if (username.isEmpty()) { // Cek jika nama pengguna kosong
            return false; // Jika ya, hentikan operasi
        }

        // Query SQL untuk menghapus data skor
        String deleteSql = "DELETE FROM tscore WHERE username =?;";
        // Jalankan operasi delete dan cek hasilnya
        return InsertUpdateDelete(deleteSql, username) > 0;
    }

    // Metode untuk mendapatkan data skor berdasarkan nama pengguna
    public Score getByUsername(String username) {
        if (username.isEmpty()) { // Cek jika nama pengguna kosong
            return null; // Jika ya, hentikan operasi
        }

        // Query SQL untuk mendapatkan data skor
        String selectSql = "SELECT * FROM tscore WHERE username =?;";
        try (ResultSet rs = selectQuery(selectSql, username)) { // Jalankan query
            if (rs.next()) { // Jika data ditemukan
                // Ambil data dari ResultSet
                String uname = rs.getString("username");
                int score = rs.getInt("score");
                int up = rs.getInt("up");
                int down = rs.getInt("down");
                return new Score(uname, score, up, down); // Buat dan kembalikan objek Score
            }
        } catch (SQLException e) { // Tangani kesalahan SQL
            e.printStackTrace();
        }
        return null; // Jika tidak ada data, kembalikan null
    }

    // Metode untuk mendapatkan semua data skor dari database
    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>(); // Daftar untuk menyimpan objek Score
        String selectSql = "SELECT * FROM tscore;"; // Query SQL untuk mendapatkan semua data skor
        try (ResultSet rs = selectQuery(selectSql)) { // Jalankan query
            while (rs.next()) { // Iterasi melalui setiap baris hasil
                // Ambil data dari ResultSet
                String username = rs.getString("username");
                int score = rs.getInt("score");
                int up = rs.getInt("up");
                int down = rs.getInt("down");
                scores.add(new Score(username, score, up, down)); // Tambahkan objek Score ke daftar
            }
        } catch (SQLException e) { // Tangani kesalahan SQL
            e.printStackTrace();
        }
        return scores; // Kembalikan daftar objek Score
    }
}
