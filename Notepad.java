import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame {
    JTextArea textArea;
    JFileChooser fileChooser;
    String filename;

    public Notepad() {
        // --- Window setup ---
        setTitle("Simple Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        // --- Menu bar ---
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        open.addActionListener(e -> openFile());
        save.addActionListener(e -> saveFile());
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(exit);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        cut.addActionListener(e -> textArea.cut());
        copy.addActionListener(e -> textArea.copy());
        paste.addActionListener(e -> textArea.paste());

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Simple Notepad\nCreated by R. P. T. Sandeepa Dilhara\nID: s16625")
        );
        helpMenu.add(about);

        // Add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    // --- File handling ---
    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                textArea.read(br, null);
                filename = file.getName();
                setTitle(filename + " - Notepad");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file");
            }
        }
    }

    private void saveFile() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                textArea.write(bw);
                filename = file.getName();
                setTitle(filename + " - Notepad");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Notepad().setVisible(true);
        });
    }
}

