package jdraw;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuBar extends JMenuBar {
    public MenuBar(JDraw app) {
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        JMenuItem newFile = new JMenuItem("New");
        newFile.addActionListener(e -> app.showCreationDialog());
        fileMenu.add(newFile);

        JMenuItem open = new JMenuItem("Open");
        KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        open.setAccelerator(ctrlO);
        open.addActionListener(e -> {
            try {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                    app.load(fileChooser.getSelectedFile().getPath());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fileMenu.add(open);

        JMenuItem save = new JMenuItem("Save");
        KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        save.setAccelerator(ctrlS);
        save.addActionListener(e -> app.save());
        fileMenu.add(save);

        JMenuItem saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(e -> {
            try {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    app.save(fileChooser.getSelectedFile().getName());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fileMenu.add(saveAs);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> app.exit());
        fileMenu.add(exit);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> {
            String version = Main.class.getPackage().getImplementationVersion();
            if(version == null)
                version = "DEV";
            JOptionPane.showMessageDialog(null,
                    "JDraw v" + version, "About", JOptionPane.INFORMATION_MESSAGE);
        });
        helpMenu.add(about);

        add(helpMenu);
    }
}
