
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class LihatData extends JFrame{
    String[][] data = new String[480][3];
    String[] kolom = {"NIM", "Nama", "Alamat"};
    JTable tabel;
    JButton btnBack;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;

    public LihatData() {
        setTitle("Data Mahasiswa");

        btnBack = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);

        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnBack);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new formmhs();
            }
        });

        try{
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();

            String sql = "SELECT * FROM data_mhs";
            resultSet = statement.executeQuery(sql);

            int p = 0;
            while (resultSet.next()){
                data[p][0] = resultSet.getString("NIM");
                data[p][1] = resultSet.getString("Nama");
                data[p][2] = resultSet.getString("Alamat");
                p++;
            }
            statement.close();
            koneksi.getKoneksi().close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan" + sqle);
        }catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(rootPane, "Class Tidak Ditemukan" + cnfe);
        }

    }
}
