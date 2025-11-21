package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditIdeaDialog extends JDialog {

    private JTextField txtCategory;
    private JTextField txtTags;
    private JTextField txtDescription;

    private Idea updatedIdea = null;

    public EditIdeaDialog(JFrame parent, Idea idea) {
        super(parent, "Edit Idea", true);
        setSize(400, 300);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(parent);

        JLabel lblCategory = new JLabel("Category:");
        txtCategory = new JTextField(idea.getCategory());

        JLabel lblTags = new JLabel("Tags (comma separated):");
        txtTags = new JTextField(idea.getTags());

        JLabel lblDescription = new JLabel("Description:");
        txtDescription = new JTextField(idea.getDescription());

        JButton btnSave = new JButton("Save Changes");

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
                    JOptionPane.showMessageDialog(EditIdeaDialog.this, "All fields required!");
                    return;
                }

                updatedIdea = new Idea(category, tags, desc);
                dispose();
            }
        });
    }

    public Idea getUpdatedIdea() {
        return updatedIdea;
    }
}
