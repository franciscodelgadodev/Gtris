package gtris;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * @date Jul 10, 2012
 * @author Fco. Delgado
 */
public class Frame extends JFrame implements ActionListener {

    private JPanel container;
    private JButton start;
    private JButton instructions;
    private JButton exit;
    private JButton easy;
    private JButton medium;
    private JButton expert;
    private PanelBoard panelBoard;

    /**
     * Define Frame Propiertes like size, Layout.
     * @param nameFrame
     */
    public Frame(String nameFrame) {
        super(nameFrame);
        setResizable(false);
        setLayout(null);
        setSize(482, 630);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = new JPanel();
        start = new JButton("Start");
        instructions = new JButton("Instructions");
        exit = new JButton("Exit");
        easy = new JButton("Easy");
        medium = new JButton("Medium");
        expert = new JButton("Expert");
        container.setSize(194, 600);
        container.setLayout(null);
        add(getSubmenu());
    }

    /**
     * Define buttons propierties for menu .
     */
    private void defineMenuPropierties() {
        start.setBounds(9, 15, 182, 182);
        instructions.setBounds(9, 197, 182, 182);
        exit.setBounds(9, 379, 182, 182);
        start.setBackground(Color.green);
        instructions.setBackground(Color.blue);
        exit.setBackground(Color.red);
        start.addActionListener(this);
        instructions.addActionListener(this);
        exit.addActionListener(this);
    }

    /**
     * Define buttons propierties for submenu.
     */
    private void defineSubmenuPropierties() {
        easy.setBounds(5, 15, 182, 182);
        medium.setBounds(5, 197, 182, 182);
        expert.setBounds(5, 379, 182, 182);
        easy.setBackground(Color.magenta);
        medium.setBackground(Color.yellow);
        expert.setBackground(Color.blue);
        easy.addActionListener(this);
        medium.addActionListener(this);
        expert.addActionListener(this);
    }

    /**
     * Define buttons propierties for Instructions.
     */
    private void defineInstructionsPropierties() {
        instructions.setBounds(5, 5, 192, 590);
        instructions.setBorder(new EtchedBorder());
    }

    /**
     * Add menu buttos to some container.
     * @return container with buttons.
     */
    private JPanel getMenu() {
        defineMenuPropierties();
        container.add(start);
        container.add(instructions);
        container.add(exit);
        return container;
    }

    /**
     * Add submenu buttos to some container.
     * @return container with buttons.
     */
    private JPanel getSubmenu() {
        defineSubmenuPropierties();
        container.add(easy);
        container.add(medium);
        container.add(expert);
        return container;
    }

    /**
     * Add instructions buttos to some container.
     * @return container with buttons.
     */
    private JPanel getInstructions() {
        defineInstructionsPropierties();
        container.add(instructions);
        return container;
    }

    /**
     * Method to use after click event.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            container.removeAll();
            add(getSubmenu());
            repaint();
        }
        if (e.getActionCommand().equals("Instructions")) {
            container.removeAll();
            add(getInstructions());
            repaint();
        }
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("Easy")) {
            initializeBoardGame(1);
        }
        if (e.getActionCommand().equals("Medium")) {
            initializeBoardGame(2);
        }
        if (e.getActionCommand().equals("Expert")) {
            initializeBoardGame(3);
        }
    }

    /**
     * Set board game in Frame
     * @param level
     */
    private void initializeBoardGame(int level){
        container.removeAll();
        panelBoard = new PanelBoard(level);
        panelBoard.setFocusable(true);
        panelBoard.setRequestFocusEnabled(true);
        add(panelBoard);
        panelBoard.grabFocus();
        repaint();
    }
    /**
     * Main method.
     * @param args
     */
    public static void main(String args[]) {
        Frame frame = new Frame("Gtris");
    }
}
