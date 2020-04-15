import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditData extends JFrame {
    JLabel lnim, lnama, lalamat, ljudul;
    JTextField tfnim, tfnama, tfalamat;
    JButton btnUpdate, btnKembali;
    Statement statement;
    ResultSet resultSet;

    public EditData() {
        ljudul  = new JLabel("Masukkan NIM yang akan Diupdate");
        lnim    = new JLabel("NIM");
        lnama   = new JLabel("Nama");
        lalamat = new JLabel("Alamat");

        tfnim   = new JTextField();
        tfnama  = new JTextField();
        tfalamat= new JTextField();

        btnUpdate   = new JButton("Update");
        btnKembali  = new JButton("Kembali");

        setTitle("Update Data Mahasiswa");
        setSize(300, 370);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(ljudul);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(tfnim);
        add(tfnama);
        add(tfalamat);
        add(btnUpdate);
        add(btnKembali);

        ljudul.setBounds(0, 10, 300, 25);
        lnim.setBounds(50, 50, 100, 25);
        tfnim.setBounds(100, 50, 100, 25);
        lnama.setBounds(50, 90, 100, 25);
        tfnama.setBounds(100, 90, 100, 25);
        lalamat.setBounds(50, 210, 100, 25);
        tfalamat.setBounds(100, 210, 100, 25);
        btnKembali.setBounds(30, 290, 100, 25);
        btnUpdate.setBounds(140, 290, 100, 25);

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new formmhs();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdateactionListener();
            }
        });
    }

    private void btnUpdateactionListener() {
        KoneksiDB koneksi = new KoneksiDB();
        try {
            statement = koneksi.getKoneksi().createStatement();
            statement.executeUpdate("UPDATE data_mhs SET Nama='" + tfnama.getText() + "'," + "Alamat='"
                    + tfalamat.getText() + "' WHERE NIM='" + tfnim.getText() + "'");
            JOptionPane.showMessageDialog(null, "Data berhasil di Update!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal di Update!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
