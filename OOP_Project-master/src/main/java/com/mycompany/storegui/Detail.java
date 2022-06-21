package com.mycompany.storegui;

import java.net.URL;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class Detail {
    public Detail(String productName, String imageAddress, Double cost, String quantity) {

        JFrame dmm = new JFrame(productName);
        dmm.setSize(500, 500);
        dmm.setBackground(Color.red);

        JPanel panel1 = new JPanel();
        panel1.setSize(500, 500);

        // panel1.setBackground(Color.red);
        panel1.setVisible(true);

        JLabel temJLabel;
        try {
            URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource(imageAddress);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource("close-icon.png");
            temJLabel = new JLabel(productName, new ImageIcon(url), JLabel.CENTER);
        }
        panel1.setLayout(
                new MigLayout("", "[86px][][][][][][][][][]", "[20px][20px][13px][21px][][][][][][][][][][][][]"));

        String costText = "Rổ Giá: " + cost;

        String quantityText = "per " + quantity;
        panel1.setBorder(BorderFactory.createEtchedBorder(Color.lightGray,
                Color.black));

        panel1.validate();
        panel1.repaint();
        Dimension dimension = new Dimension(5, 0);
        dmm.getContentPane().add(Box.createRigidArea(dimension));
        dmm.getContentPane().add(panel1);
        URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource(imageAddress);
        temJLabel = new JLabel(productName, new ImageIcon(url), JLabel.CENTER);

        JLabel imageNameLabel1 = temJLabel;
        imageNameLabel1.setVerticalTextPosition(SwingConstants.BOTTOM);
        imageNameLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
        imageNameLabel1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel1.add(imageNameLabel1, "cell 3 2 3 5,growx,aligny center");
        JLabel quantityLabel = new JLabel(quantityText);
        panel1.add(quantityLabel, "cell 3 8,alignx left,aligny center");
        JLabel costLabel = new JLabel(costText);
        costLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        panel1.add(costLabel, "cell 3 10,alignx left,aligny center");

        JButton btnBuy = new JButton("Mua");

        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Thêm vào giỏ hàng?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    OnlineSelectionScrollPane.CART.add(new Item(productName, imageAddress, cost));
                }
            }
        });
        panel1.add(btnBuy, "cell 4 13,alignx left,aligny center");
        dmm.setVisible(true);

    }
}
