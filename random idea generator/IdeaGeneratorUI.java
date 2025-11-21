package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class IdeaGeneratorUI extends JFrame {

    private IdeaManager manager = new IdeaManager();
    private JTextArea display;

    public IdeaGeneratorUI() {
        setTitle("Random Idea Generator");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("💡 Random Idea Generator", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        // Display area
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 16));
        display.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(display), BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton btnRandom = new JButton("Generate Random Idea");
        JButton btnAdd = new JButton("Add New Idea");
        JButton btnView = new JButton("View All Ideas");
        JButton btnSearchCategory = new JButton("Search by Category");
        JButton btnSearchTag = new JButton("Search by Tag");
        JButton btnExport = new JButton("Export to JSON");

        panel.add(btnRandom);
        panel.add(btnAdd);
        panel.add(btnView);
        panel.add(btnSearchCategory);
        panel.add(btnSearchTag);
        panel.add(btnExport);

        add(panel, BorderLayout.SOUTH);

        // Button Actions

        btnRandom.addActionListener(e -> generateRandomIdea());
        btnAdd.addActionListener(e -> addIdea());
        btnView.addActionListener(e -> viewAllIdeas());
        btnSearchCategory.addActionListener(e -> searchCategory());
        btnSearchTag.addActionListener(e -> searchTag());
        btnExport.addActionListener(e -> exportJSON());

        setVisible(true);
    }

    private void generateRandomIdea() {
        List<Idea> ideas = manager.loadIdeas();
        if (ideas.isEmpty()) {
            display.setText("No ideas available.");
            return;
        }
        Idea idea = ideas.get(new Random().nextInt(ideas.size()));
        animateText("💡 Random Idea:\n\n" + formatIdea(idea));
    }

    private void addIdea() {
        AddIdeaDialog dialog = new AddIdeaDialog(this);
        dialog.setVisible(true);
        Idea newIdea = dialog.getNewIdea();
        if (newIdea != null) {
            manager.addIdea(newIdea);
            display.setText("Idea added successfully!");
        }
    }

    private void viewAllIdeas() {
        List<Idea> ideas = manager.loadIdeas();
        if (ideas.isEmpty()) {
            display.setText("No ideas stored.");
            return;
        }

        StringBuilder sb = new StringBuilder("📘 All Ideas:\n\n");
        int index = 1;
        for (Idea i : ideas) {
            sb.append(index++).append(") ").append(formatIdea(i)).append("\n");
        }

        display.setText(sb.toString());
    }

    private void searchCategory() {
        String cat = JOptionPane.showInputDialog(this, "Enter category to search:");
        if (cat == null || cat.isEmpty()) return;

        List<Idea> ideas = manager.searchByCategory(cat, manager.loadIdeas());
        showSearchResults(ideas);
    }

    private void searchTag() {
        String tag = JOptionPane.showInputDialog(this, "Enter tag to search:");
        if (tag == null || tag.isEmpty()) return;

        List<Idea> ideas = manager.searchByTag(tag, manager.loadIdeas());
        showSearchResults(ideas);
    }

    private void showSearchResults(List<Idea> ideas) {
        if (ideas.isEmpty()) {
            display.setText("No matching ideas found.");
            return;
        }

        StringBuilder sb = new StringBuilder("🔍 Search Results:\n\n");
        for (Idea i : ideas) {
            sb.append(formatIdea(i)).append("\n");
        }

        display.setText(sb.toString());
    }

    private void exportJSON() {
        List<Idea> ideas = manager.loadIdeas();
        if (ideas.isEmpty()) {
            display.setText("No ideas to export.");
            return;
        }

        manager.exportToJSON(ideas, "ideas.json");
        display.setText("Exported to ideas.json successfully!");
    }

    private String formatIdea(Idea i) {
        return "Category: " + i.getCategory() +
               "\nTags: " + i.getTags() +
               "\nDescription: " + i.getDescription() +
               "\n";
    }

    private void animateText(String text) {
        new Thread(() -> {
            display.setText("");
            for (int i = 0; i < text.length(); i++) {
                display.append(String.valueOf(text.charAt(i)));
                try { Thread.sleep(8); } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IdeaGeneratorUI::new);
    }
}
