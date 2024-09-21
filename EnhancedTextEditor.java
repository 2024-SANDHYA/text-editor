import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EnhancedTextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    JLabel statusLabel;

    public EnhancedTextEditor() {
        // Frame settings
        setTitle("Enhanced Text Editor");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create toolbar
        JToolBar toolBar = new JToolBar();
        JButton newFile = new JButton("New");
        JButton openFile = new JButton("Open");
        JButton saveFile = new JButton("Save");
        JButton exit = new JButton("Exit");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);

        toolBar.add(newFile);
        toolBar.add(openFile);
        toolBar.add(saveFile);
        toolBar.add(exit);
        add(toolBar, BorderLayout.NORTH);

        // Status bar
        statusLabel = new JLabel(" ");
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("New")) {
            textArea.setText("");
            statusLabel.setText("New file created");
        } else if (command.equals("Open")) {
            openFile();
        } else if (command.equals("Save")) {
            saveFile();
        } else if (command.equals("Exit")) {
            System.exit(0);
        }
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));
        int action = fileChooser.showOpenDialog(null);
        if (action == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
                statusLabel.setText("Opened: " + file.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));
        int action = fileChooser.showSaveDialog(null);
        if (action == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
                statusLabel.setText("Saved: " + file.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new EnhancedTextEditor();
    }
}
