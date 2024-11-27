package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDBByMySQL;
import dao.DAO_TaiKhoan;

public class GD_DangNhap extends JFrame{
	private static final long serialVersionUID = 1L;
    private JLabel lbTitle, lbTaiKhoan, lbMatKhau, lbQuenMatKhau;
    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    private JButton btnLogin, btnShowHide;

    public GD_DangNhap() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(Color.decode("#F5EFE0"));
        JPanel pNorth = new JPanel();
        lbTitle = new JLabel("ĐĂNG NHẬP");
        Font font = new Font("Time New Roman", Font.BOLD, 24);
        lbTitle.setFont(font);
        lbTitle.setForeground(Color.decode("#CD2653"));
        pNorth.add(lbTitle);
        pNorth.setOpaque(false);
        panel.add(pNorth, BorderLayout.NORTH);

        JPanel pCenter = new JPanel();
        pCenter.setLayout(null);
        pCenter.setBackground(new Color(0, 0, 0, 0));
        pCenter.setPreferredSize(new Dimension(640, 180));
        pCenter.add(lbTaiKhoan = new JLabel("Tài Khoản: "));
        lbTaiKhoan.setBounds(150, 10, 80, 25);
        pCenter.add(txtTaiKhoan = new JTextField());
        txtTaiKhoan.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTaiKhoan.getText().trim().equals("Tên đăng nhập")) {
                    txtTaiKhoan.setText("");
                }
                txtTaiKhoan.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTaiKhoan.getText().trim().equals("")) {
                    txtTaiKhoan.setText("Tên đăng nhập");
                    txtTaiKhoan.setForeground(Color.LIGHT_GRAY);

                }
            }
        });
        txtTaiKhoan.setBounds(240, 10, 250, 25);
        pCenter.add(lbMatKhau = new JLabel("Mật khẩu: "));
        lbMatKhau.setBounds(150, 40, 80, 25);
        pCenter.add(txtMatKhau = new JPasswordField());
        txtMatKhau.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] password = txtMatKhau.getPassword();
                if (new String(password).equals("Nhập mật khẩu")) {
                    txtMatKhau.setText("");
                    txtMatKhau.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] password = txtMatKhau.getPassword();
                if (new String(password).isEmpty()) {
                    txtMatKhau.setText("Nhập mật Khẩu");
                    txtMatKhau.setEchoChar((char) 0);
                    txtMatKhau.setForeground(Color.LIGHT_GRAY);

                }
            }
        });
        txtMatKhau.setBounds(240, 40, 250, 25);
        pCenter.add(lbQuenMatKhau = new JLabel("Quên Mật khẩu?"));
        lbQuenMatKhau.setBounds(400, 70, 100, 25);
        pCenter.add(btnLogin = new JButton("Đăng nhập"));
        btnLogin.setBounds(260, 100, 120, 25);
        btnLogin.setBackground(Color.decode("#CD2653"));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBorder(null);
        btnLogin.setFocusPainted(false);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtTaiKhoan.getText();
                char[] passwordChars = txtMatKhau.getPassword();
                String password = new String(passwordChars);
                String role = DAO_TaiKhoan.authenticateUser(username, password);

                if (role != null) {
                    System.out.println(role);
                    if ("admin".equals(role)) {
                        // Perform admin-specific actions
                    } else if ("user".equals(role)) {
                        // Perform user-specific actions
                    }
                } else {
                    System.out.println("Invalid");                }

            }
        });

//        ImageIcon showPassIcon = new ImageIcon("img/icon/Show.png");
//        ImageIcon hidePassIcon = new ImageIcon("img/icon/hide.png");
//        pCenter.add(btnShowHide = new JButton(showPassIcon));
//        btnShowHide.setBounds(350, 39, 27, 27);
//        btnShowHide.setOpaque(false);
//        btnShowHide.setBorder(null);
//        btnShowHide.setFocusPainted(false);
//        btnShowHide.addActionListener(new ActionListener() {
//            boolean passwordVisible = false;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (passwordVisible) {
//                    txtMatKhau.setEchoChar('•');
//                    btnShowHide.setIcon(showPassIcon);
//                } else {
//                    txtMatKhau.setEchoChar((char) 0);
//                    btnShowHide.setIcon(hidePassIcon);
//                }
//                passwordVisible = !passwordVisible;
//            }
//        });
//
//        panel.add(pCenter, BorderLayout.CENTER);
//
//        setTitle("Login Page");
//        setIconImage(new ImageIcon("img/Logo.png").getImage());
//        setSize(640, 360);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setLocationRelativeTo(null);
//        setResizable(false);
//    }

    

    public static void main(String[] args) {
        new GD_DangNhap();
    }
}
