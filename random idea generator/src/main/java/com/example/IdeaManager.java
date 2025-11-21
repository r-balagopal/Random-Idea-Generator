package com.example;

import java.io.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
 
public class IdeaManager {

    private final String FILE_NAME = "ideas.txt";

    public List<Idea> loadIdeas() {
        List<Idea> ideas = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return ideas;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null) {
                    lineNum++;
                    Idea idea = Idea.fromString(line);
                    if (idea != null) {
                        ideas.add(idea);
                    } else {
                        System.out.println("Skipping malformed line " + lineNum + ": " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading ideas.");
        }

        return ideas;
    }

    public void saveIdeas(List<Idea> ideas) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (Idea idea : ideas) {
                fw.write(idea.toString() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error saving ideas.");
        }
    }

    public void addIdea(Idea idea) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(idea.toString() + "\n");
        } catch (Exception e) {
            System.out.println("Error adding idea.");
        }
    }

    public List<Idea> searchByCategory(String category, List<Idea> list) {
        List<Idea> results = new ArrayList<>();
        for (Idea i : list) {
            if (i == null) continue;
            String cat = i.getCategory();
            if (cat != null && category != null && cat.equalsIgnoreCase(category)) {
                results.add(i);
            }
        }
        return results;
    }

    public List<Idea> searchByTag(String tag, List<Idea> list) {
        List<Idea> results = new ArrayList<>();
        for (Idea i : list) {
            if (i == null) continue;
            String tags = i.getTags();
            if (tags == null || tag == null) continue;
            String[] allTags = tags.split(",");
            for (String t : allTags) {
                if (t == null) continue;
                if (t.trim().equalsIgnoreCase(tag)) {
                    results.add(i);
                    break;
                }
            }
        }
        return results;
    }

    public void deleteIdea(int index, List<Idea> ideas) {
        if (index >= 0 && index < ideas.size()) {
            ideas.remove(index);
            saveIdeas(ideas);
        }
    }

    public void editIdea(int index, Idea updated, List<Idea> ideas) {
        if (index >= 0 && index < ideas.size()) {
            ideas.set(index, updated);
            saveIdeas(ideas);
        }
    }

    // Export to JSON file
    public void exportToJSON(List<Idea> ideas, String fileName) {
        JSONArray arr = new JSONArray();

        for (Idea idea : ideas) {
            JSONObject obj = new JSONObject();
            obj.put("category", idea.getCategory());
            obj.put("tags", idea.getTags());
            obj.put("description", idea.getDescription());
            arr.put(obj);
        }

        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(arr.toString(4));
        } catch (Exception e) {
            System.out.println("Error exporting JSON.");
        }
    }
}
