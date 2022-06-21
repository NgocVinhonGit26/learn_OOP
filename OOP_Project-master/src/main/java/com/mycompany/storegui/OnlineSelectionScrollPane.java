/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.storegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public final class OnlineSelectionScrollPane extends JScrollPane {
    // category and products panels will go on it
    private static final JPanel MAIN_PANEL = new JPanel(); // static because scroll pane "add" method not working and so
                                                           // to pass it to its constructor
    private static final HashMap<String, JPanel> CATEGORIES = new HashMap<>(); // keep record of categories
    public static final ArrayList<Item> CART = new ArrayList<>(); // keep record of items included in cart
    private static final HashMap<JPanel, JLabel> UNDER_EDITING_PANELS = new HashMap<>();
    static {

        // make 3 categories
        addCategory("DVD", "dvd.png");
        addCategory("Book", "book.png");
        addCategory("CD", "cd.png");

        // add products to first category
        JPanel panel = new JPanel();
        panel.add(addProduct("The Lion King", "lionking.png", 100.0, "dozen"));
        panel.add(addProduct("Justice League", "justice.png", 250.0, "kg"));
        panel.add(addProduct("Up", "up.png", 200.0, "kg"));
        panel.add(addProduct("The Incredibles", "increibles.png", 150.0, "kg"));

        CATEGORIES.get("DVD").add(panel);

        // add products to second category
        panel = new JPanel();
        panel.add(addProduct("The Shining", "shining.jpg", 100.0, "dozen"));
        panel.add(addProduct("It", "it.png", 250.0, "kg"));
        panel.add(addProduct("Dragon", "dragon.jpg", 200.0, "kg"));
        panel.add(addProduct("Dế mèn", "demen.jpg", 150.0, "kg"));

        CATEGORIES.get("Book").add(panel);

        // add products to third category
        panel = new JPanel();
        panel.add(addProduct("Mint Jams", "mint.jpg", 1000.0, "set"));
        panel.add(addProduct("The Beatles", "beatles.png", 3000.0, "set"));
        CATEGORIES.get("CD").add(panel);

    }

    public OnlineSelectionScrollPane() {
        super(MAIN_PANEL);

        // remove any previous component (dealing with static nonsense)
        MAIN_PANEL.removeAll();
        MAIN_PANEL.validate();
        MAIN_PANEL.repaint();

        BoxLayout layout = new BoxLayout(MAIN_PANEL, BoxLayout.Y_AXIS);
        MAIN_PANEL.setLayout(layout);

        JButton btnBack = new JButton("Trở lại");
        JButton btnShowCart = new JButton("Hiển thị giỏ hàng");
        JButton btnBuyCart = new JButton("Mua sản phẩm trong giỏ hàng");

        JPanel subPanel = new JPanel();
        subPanel.add(btnBack);
        subPanel.add(Box.createRigidArea(new Dimension(20, 0))); // horizontal space
        subPanel.add(btnShowCart);
        subPanel.add(Box.createRigidArea(new Dimension(20, 0))); // horizontal space
        subPanel.add(btnBuyCart);

        if (OnlineLoginPanel.isAdminAccess()) { // if have admin access give these extra things
            JButton btnAddCategory = new JButton("Thêm danh mục mới");
            subPanel.add(Box.createRigidArea(new Dimension(30, 0)));
            subPanel.add(btnAddCategory);

            btnAddCategory.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = JOptionPane.showInputDialog(null, "Nhập tên Danh mục");
                    String imageAddress = JOptionPane.showInputDialog(null, "Nhập địa chỉ hình ảnh");
                    addCategory(name, imageAddress);
                    MAIN_PANEL.add(CATEGORIES.get(name));
                    MAIN_PANEL.validate();
                    MAIN_PANEL.repaint();
                }

            });
        }
        // if logined give log out button
        if (OnlineRegistrationPanel.isLogin()) {
            JButton logOut = new JButton("Đăng xuất");
            subPanel.add(Box.createRigidArea(new Dimension(50, 0)));
            subPanel.add(logOut);

            logOut.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    OnlineRegistrationPanel.setLogin(false);
                    OnlineLoginPanel.setAdminAccess(false);

                    for (JLabel label : UNDER_EDITING_PANELS.values()) {
                        JPanel panel = CATEGORIES.get(label.getText());
                        panel.remove(0);
                        panel.add(label, 0);
                        panel.revalidate();
                        panel.repaint();
                    }
                    MainPanel.setSubContainer(new OnlineSelectionScrollPane());
                }

            });

        }

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setSubContainer(new MainMenuPanel());
            }
        });

        btnShowCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setSubContainer(new ShowOnlineCartPanel());
            }
        });

        btnBuyCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OnlineRegistrationPanel.isLogin())
                    MainPanel.setSubContainer(new OnlineBuyCartPanel());
                else
                    MainPanel.setSubContainer(new OnlineLoginPanel());
            }
        });

        MAIN_PANEL.add(subPanel);

        // add categories to main panel
        for (JPanel panel : CATEGORIES.values())
            MAIN_PANEL.add(panel);

    }

    public static void addCategory(String categoryName, String imageAddress) {
        if (!CATEGORIES.containsKey(categoryName)) { // if category name is not already used
            final JPanel panel = new JPanel();
            BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(layout);

            JLabel tempLabel;
            try {
                URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource(imageAddress);
                tempLabel = new JLabel(categoryName, new ImageIcon(url), SwingConstants.LEADING);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR", JOptionPane.ERROR_MESSAGE);
                URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource("close-icon.png");
                tempLabel = new JLabel(categoryName, new ImageIcon(url), JLabel.CENTER);
            }

            JLabel label = tempLabel; // will use it later in anonymous class
            label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

            panel.addMouseListener(new MouseAdapter() {
                private boolean show = true; // show extra buttons in admin access
                JPanel subPanel = new JPanel();

                @Override
                public void mouseClicked(MouseEvent e) {

                    if (OnlineLoginPanel.isAdminAccess()) {
                        if (show) {
                            show = false; // so if clicked again hide these
                            UNDER_EDITING_PANELS.put(panel, label);
                            JButton btnAddProduct = new JButton("Thêm sản phẩm mới");
                            JButton btnEditCategory = new JButton("Cập nhật Danh mục");
                            JButton btnDelete = new JButton("Xóa Danh mục");

                            subPanel.setBackground(Color.WHITE);

                            subPanel.add(label);
                            Dimension dimension = new Dimension(5, 0);
                            subPanel.add(Box.createRigidArea(dimension));
                            subPanel.add(btnAddProduct);
                            subPanel.add(Box.createRigidArea(dimension));
                            subPanel.add(btnEditCategory);
                            subPanel.add(Box.createRigidArea(dimension));
                            subPanel.add(btnDelete);

                            panel.remove(label);
                            panel.add(subPanel, 0);
                            panel.revalidate();
                            panel.repaint();

                            btnAddProduct.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        String productName = JOptionPane.showInputDialog(null, "Nhập tên sản phẩm");
                                        String imageAddress = JOptionPane.showInputDialog(null,
                                                "Nhập địa chỉ hình ảnh");
                                        double cost = Double
                                                .parseDouble((String) JOptionPane.showInputDialog(null,
                                                        "Nhập giá sản phẩm"));
                                        String quantity = JOptionPane.showInputDialog(null,
                                                "Enter quantity(it will be displayed as cost per quantity)");

                                        if (productName == null || imageAddress == null || quantity == null)
                                            throw new NullPointerException();

                                        try {
                                            JPanel subPanel = (JPanel) panel.getComponent(1);
                                            JPanel subSubPanel = addProduct(productName, imageAddress, cost, quantity);
                                            subPanel.add(subSubPanel);
                                            subPanel.validate();
                                            subPanel.repaint();
                                        } catch (ArrayIndexOutOfBoundsException ex) {
                                            JPanel subPanel = new JPanel();
                                            JPanel subSubPanel = addProduct(productName, imageAddress, cost, quantity);
                                            subPanel.add(subSubPanel);
                                            subPanel.validate();
                                            subPanel.repaint();
                                            panel.add(subPanel);
                                            panel.validate();
                                            panel.repaint();
                                        }
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Chi phí không hợp lệ", "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                                    } catch (NullPointerException ex) {
                                        JOptionPane.showMessageDialog(null, "Operation Cancelled");
                                    }

                                }
                            });

                            btnEditCategory.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int choice = JOptionPane.showConfirmDialog(null, "Cập nhật Danh mục ?");
                                    if (choice == JOptionPane.YES_OPTION) {
                                        try {
                                            String name = JOptionPane.showInputDialog(null, "Nhập tên Danh mục");
                                            if (name == null)
                                                throw new NullPointerException();

                                            String imageAddress = JOptionPane.showInputDialog(null,
                                                    "Nhập địa chỉ hình ảnh của Danh mục");
                                            if (imageAddress == null)
                                                throw new NullPointerException();

                                            CATEGORIES.remove(label.getText());
                                            label.setText(name);
                                            CATEGORIES.put(name, panel);

                                            URL url = OnlineSelectionScrollPane.class.getClassLoader()
                                                    .getResource(imageAddress);
                                            label.setIcon(new ImageIcon(ImageIO.read(url)));
                                        } catch (IOException ex) {
                                            JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR",
                                                    JOptionPane.ERROR_MESSAGE);
                                        } catch (NullPointerException ex) {
                                            JOptionPane.showMessageDialog(null, "Operation Cancelled");
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                    }

                                }
                            });

                            btnDelete.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int choice = JOptionPane.showConfirmDialog(null, "Xóa vĩnh viễn?",
                                            "Confirmation", JOptionPane.YES_NO_OPTION);
                                    if (choice == JOptionPane.YES_OPTION) {
                                        CATEGORIES.remove(label.getText(), panel);
                                        JPanel parentPanel = (JPanel) panel.getParent();
                                        parentPanel.remove(panel);
                                        parentPanel.validate();
                                        parentPanel.repaint();
                                    }
                                }
                            });

                        } else {
                            subPanel.removeAll(); // remove everything(those extra buttons in admin access) from extra
                                                  // panel
                            panel.remove(subPanel); // remove that panel too
                            panel.setBackground(Color.WHITE);
                            panel.add(label, 0);
                            panel.revalidate();
                            panel.repaint();
                            show = true; // if clicked again show those buttons
                            UNDER_EDITING_PANELS.remove(panel);
                        }

                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    if (OnlineLoginPanel.isAdminAccess())
                        panel.setToolTipText("Bấm vào để chỉnh sửa");

                    else
                        panel.setToolTipText(null);
                }

            });

            if (!OnlineLoginPanel.isAdminAccess() || !CATEGORIES.containsKey(categoryName)) {
                panel.setBackground(Color.WHITE);
                panel.add(label);
            }

            CATEGORIES.put(categoryName, panel); // put in categories(hash map)
        } else
            JOptionPane.showMessageDialog(null, "Danh mục này đã có", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    private static JPanel addProduct(String productName, String imageAddress, Double cost, String quantity) {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        JLabel tempLabel;
        try {
            URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource(imageAddress);
            tempLabel = new JLabel(productName, new ImageIcon(ImageIO.read(url)), JLabel.CENTER);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR", JOptionPane.ERROR_MESSAGE);
            URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource("close-icon.png");
            tempLabel = new JLabel(productName, new ImageIcon(url), JLabel.CENTER);
        }

        JLabel imageNameLabel = tempLabel;
        imageNameLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        imageNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        imageNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(imageNameLabel);

        String costText = "Giá: " + cost;
        JLabel costLabel = new JLabel(costText);
        costLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        String quantityText = "per " + quantity;
        JLabel quantityLabel = new JLabel(quantityText);

        panel.add(costLabel);
        panel.add(quantityLabel);
        panel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        panel.validate();
        panel.repaint();
        boolean status = false;
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineLoginPanel.isAdminAccess()) {
                    if (!e.isMetaDown()) {// if not right click
                        int choice = JOptionPane.showConfirmDialog(null, "Xóa ?", "Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            JPanel parentPanel = (JPanel) panel.getParent();
                            parentPanel.remove(panel);
                            parentPanel.validate();
                            parentPanel.repaint();
                        }
                    }

                    else { // if right click
                        int choice = JOptionPane.showConfirmDialog(null, "Cập nhật sản phẩm ?", "Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                String productName = JOptionPane.showInputDialog(null, "Nhập tên sản phẩm");
                                if (productName == null)
                                    throw new NullPointerException();

                                String imageAddress = JOptionPane.showInputDialog(null, "Nhập địa chỉ ảnh minh họa");
                                if (imageAddress == null)
                                    throw new NullPointerException();

                                double cost = Double
                                        .parseDouble((String) JOptionPane.showInputDialog(null, "Nhập giá"));
                                String quantity = JOptionPane.showInputDialog(null,
                                        "Enter quantity(it will be displayed as cost per quantity)");
                                if (quantity == null)
                                    throw new NullPointerException();

                                URL url = OnlineSelectionScrollPane.class.getClassLoader().getResource(imageAddress);
                                File file = new File(imageAddress);
                                imageNameLabel.setText(productName);
                                imageNameLabel.setIcon(new ImageIcon(ImageIO.read(url)));

                                costLabel.setText("" + cost);
                                quantityLabel.setText(quantity);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Chi phí không hợp lệ", "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                            } catch (NullPointerException ex) {
                                JOptionPane.showMessageDialog(null, "Operation Cancelled");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Không thấy ảnh", "ERROR",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        }

                    }

                } else { // if not admin access

                    Detail detail = new Detail(productName, imageAddress, cost, quantity);
//                	test detail = new test(productName, imageAddress, cost, quantity);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.pink);
                panel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.red));

                if (OnlineLoginPanel.isAdminAccess())
                    panel.setToolTipText("Nhấp chuột trái để xóa. Nhấp chuột phải để chỉnh sửa");

                else
                    panel.setToolTipText("Bấm vào để mua");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(null);
                panel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
            }

        });
        return panel;

    }
}