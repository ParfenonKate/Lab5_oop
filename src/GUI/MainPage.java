package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPage {
    private JComponent ui = null;

    MainPage() {
        initUI();
    }

    private void initUI() {
        if (ui!=null) return;


        ui = new JPanel(new BorderLayout(4,4));


        JPanel appletPanel = new JPanel(new GridLayout());
        appletPanel.setBackground(Color.white);


        Font font = new Font("Century Gothic", Font.BOLD, 34);
        Dimension labelSize = new Dimension(380, 380);
        JLabel header = new JLabel("Отдел кадров");
        header.setVerticalAlignment(JLabel.CENTER);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(labelSize);
        header.setFont(font);


        appletPanel.add(header);
        ui.add(appletPanel);

        JPanel menuPanel = new JPanel(new BorderLayout());

        ui.add(menuPanel, BorderLayout.LINE_END);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        menuPanel.add(buttonPanel, BorderLayout.PAGE_START);


        JButton start= new JButton("Показать список");
        start.setFont(start.getFont().deriveFont(18f));
        buttonPanel.add(start);

        start.addActionListener(actionEvent -> { start(actionEvent); });

        JButton Info= new JButton("Справка");
        Info.setFont(Info.getFont().deriveFont(18f));
        buttonPanel.add(Info);
        Info.addActionListener(this::info);

        JButton close= new JButton("Выйти");
        close.setFont(close.getFont().deriveFont(18f));
        buttonPanel.add(close);
        close.addActionListener(this::exit);
    }

    private void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void info(ActionEvent actionEvent) {

        String message = "<html>Программа предназначена для работы с отделом кадров некоторой фирмы.<br/>Интерфейс позволяет просматривать как всех сотрудников,так и по группам,с учетом занимаемой должности.<br/>"+
                "Также присутсвует возможность поиска информации о конкретном сотруднике по имени и фамилии(очередность ввода значения не имеет).<br/>При возникновений ошибок в поиске"+
                " появляются соответсвующие подсказки.<br/>Возможность удаления(увольнения) сотрудника работает по аналогии с поиском.<br/>Добавление сотрудника в штат осуществляется при помощи отдельной формы.<br/>"+
                "После добавления и удаления таблица обновляется.</html>";
        JOptionPane.showMessageDialog(null , message, "Справка", JOptionPane.INFORMATION_MESSAGE);
    }

    private void start(ActionEvent actionEvent)  {
        GUI.gui();
    }


    public JComponent getUI() {
        return ui;
    }
}
