/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg902;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class MySearch {

    public static void main(String[] args) {
        new MySearch();
    }

    public MySearch() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JTextField findText;
        private JButton search;
        private DefaultListModel<String> model;

        public TestPane() {
            
                
            setLayout(new BorderLayout());
            JPanel searchPane = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(2, 2, 2, 2);
            searchPane.add(new JLabel("Find: "), gbc);
            gbc.gridx++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            findText = new JTextField(20);
            searchPane.add(findText, gbc);
    
            gbc.gridx++;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            search = new JButton("Search");
            searchPane.add(search, gbc);

            add(searchPane, BorderLayout.NORTH);

            model = new DefaultListModel<>();
            JList list = new JList(model);
            add(new JScrollPane(list));

            ActionHandler handler = new ActionHandler();

            search.addActionListener(handler);
            findText.addActionListener(handler);
        }

        public class ActionHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeAllElements();
//                    BufferedReader reader = null;

                String searchText = findText.getText();
               
                try (BufferedReader reader = new BufferedReader(new FileReader(new File("taxudromeia.txt")))) {

                    String text = null;
                    while ((text = reader.readLine()) != null) {

                        if (text.contains(searchText)) {

                            model.addElement(text);

                        }

                    }

                } catch (IOException exp) {

                    exp.printStackTrace();
                    JOptionPane.showMessageDialog(TestPane.this, "Could not create file", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }
    
}