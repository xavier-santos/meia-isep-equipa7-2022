package org.engcia.view;
import org.engcia.model.Justification;
import org.engcia.utils.Engine;
import org.engcia.utils.FiredRule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static java.lang.Thread.sleep;

public class GUI {

    //region GUI elements initialization
    private JPanel mainPanel;
    private JButton ripped_button;
    private JButton misplaced_button;
    private JButton filling_button;
    private JButton part_button;
    private JButton bubbled_button;
    private JButton collapsed_button;
    private JLabel suggestion;
    private JPanel secondarySelectionPanel;
    private JPanel selectionPanel;
    private JPanel card;
    private JPanel questionPanel;
    private JButton yesButton;
    private JButton noButton;
    private JPanel secondaryQuestionPanel;
    private JLabel questionLabel;
    private CardLayout cardLayout;
    //endregion

    public static Map<Integer, Justification> justifications = new TreeMap<>();;

    Map<String, String> buttonToRulesMap = new HashMap<String, String>(){{
        put(ripped_button.getName(), "ksession-rules-ripped");
        put(misplaced_button.getName(), "ksession-rules-misplaced");
        put(collapsed_button.getName(), "ksession-rules-collapsed");
        put(bubbled_button.getName(), "ksession-rules-bubbled");
        put(part_button.getName(), "ksession-rules-lackcomponent");
        put(filling_button.getName(), "ksession-rules-lackfilling");
    }};

    public GUI() {
        cardLayout = (CardLayout) card.getLayout();

        ActionListener selectionListener = ae -> {
            JButton myButton = (JButton)ae.getSource();
            Engine droolsEngine = new Engine(buttonToRulesMap.get(myButton.getName()));
            Thread t1 = new Thread(droolsEngine);
            t1.start();
            cardLayout.next(card);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(Objects.equals(FiredRule.question, "")){

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            questionLabel.setText(FiredRule.question);
        };

        ripped_button.addActionListener(selectionListener);
        misplaced_button.addActionListener(selectionListener);
        filling_button.addActionListener(selectionListener);
        part_button.addActionListener(selectionListener);
        bubbled_button.addActionListener(selectionListener);
        collapsed_button.addActionListener(selectionListener);

        yesButton.addActionListener(ae -> {
            FiredRule.response=true;
            FiredRule.answered=true;
            FiredRule.question="";
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(Objects.equals(FiredRule.question, "")){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            questionLabel.setText(FiredRule.question);
        });
        noButton.addActionListener(ae -> {
            FiredRule.response=false;
            FiredRule.answered=true;
            FiredRule.question="";
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(Objects.equals(FiredRule.question, "")){

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            questionLabel.setText(FiredRule.question);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Defects Diagnostic");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
