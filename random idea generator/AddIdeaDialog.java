package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddIdeaDialog extends JDialog {

    private JTextField txtCategory;
    private JTextField txtTags;
    private JTextField txtDescription;

    private Idea newIdea = null;

    public AddIdeaDialog(JFrame parent) {
        super(parent, "Add New Idea", true);
        setSize(400, 300);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(parent);

        JLabel lblCategory = new JLabel("Category:");
        txtCategory = new JTextField();

        JLabel lblTags = new JLabel("Tags (comma separated):");
        txtTags = new JTextField();

        JLabel lblDescription = new JLabel("Description:");
        txtDescription = new JTextField();

        JButton btnSave = new JButton("Save");

        add(lblCategory);
        add(txtCategory);
        add(lblTags);
        add(txtTags);
        add(lblDescription);
        add(txtDescription);
        add(new JLabel());
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = txtCategory.getText().trim();
                String tags = txtTags.getText().trim();
                String desc = txtDescription.getText().trim();

                if (category.isEmpty() || tags.isEmpty() || desc.isEmpty()) {
                    JOptionPane.showMessageDialog(AddIdeaDialog.this, "All fields required!");
                    return;
                }

                newIdea = new Idea(category, tags, desc);
                dispose();
            }
        });
    }

    public Idea getNewIdea() {
        return newIdea;
    }
}
