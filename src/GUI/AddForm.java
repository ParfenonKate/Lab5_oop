package GUI;

import com.company.Administration;
import com.company.Engineer;
import com.company.Worker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Locale;


public class AddForm extends JFrame implements ComponentListener {

    public JLabel labelName = new JLabel("Имя: ");
    public JLabel labelSurname = new JLabel("Фамилия: ");
    public JLabel labelAge = new JLabel("Возраст: ");
    public JLabel labelProfession = new JLabel("Специальность: ");
    public JLabel labelMoney = new JLabel("Зарплата (р/мес) : ");
    public JLabel labelYears = new JLabel("Стаж работы: ");
    public JLabel error1 = new JLabel("Неверный ввод!");
    public JLabel error2 = new JLabel("Неверный ввод!");
    public JLabel error3 = new JLabel("Введите число меньше 100");
    public JLabel error4 = new JLabel("Введите число");
    public JLabel error5 = new JLabel("Неверный ввод!");
    public JLabel error6 = new JLabel("Введите число");


    public JTextField txtName = new JTextField(20);
    public JTextField txtSurname = new JTextField(20);
    public JTextField txtAge = new JTextField(20);
    public JTextField txtMoney = new JTextField(20);
    public JTextField txtProfession = new JTextField(20);
    public JTextField txtYears = new JTextField(20);


    private final JButton btnAdd = new JButton("Добавить");
    private final JButton btnCancel = new JButton("Отмена");

    public MyTableModelStaff model ;

    public AddForm(MyTableModelStaff model) {
        super("Добавить сотрудника");
        this.model = model;
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        panelsParameters();
        getInputContext().selectInputMethod(new Locale("ru","RU"));
    }



    private void panelsParameters() {
        btnAdd.setEnabled(false);
        error1.setForeground(this.getBackground());
        error2.setForeground(this.getBackground());
        error3.setForeground(this.getBackground());
        error4.setForeground(this.getBackground());
        error5.setForeground(this.getBackground());
        error6.setForeground(this.getBackground());


        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);


        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelName, constraints);

        constraints.gridx = 1;
        newPanel.add(txtName, constraints);

        constraints.gridx = 2;
        newPanel.add(error1, constraints);

        ValidationText(txtName,error1);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelSurname, constraints);

        constraints.gridx = 1;
        newPanel.add(txtSurname, constraints);

        constraints.gridx = 2;
        newPanel.add(error2, constraints);

        ValidationText(txtSurname,error2);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelAge, constraints);

        constraints.gridx = 1;
        newPanel.add(txtAge, constraints);

        constraints.gridx = 2;
        newPanel.add(error3, constraints);

        ValidationAge(txtAge,error3);

               switch (this.model.getType()) {
            case "Сотрудники администрации":

                constraints.gridx = 0;
                constraints.gridy = 3;
                newPanel.add(labelMoney, constraints);

                constraints.gridx = 1;
                newPanel.add(txtMoney, constraints);

                constraints.gridx = 2;
                newPanel.add(error4, constraints);
                ValidationNumber(txtMoney,error4);


                break;

            case "Инженеры":
                constraints.gridx = 0;
                constraints.gridy = 3;
                newPanel.add(labelProfession, constraints);

                constraints.gridx = 1;
                newPanel.add(txtProfession, constraints);

                constraints.gridx = 2;
                newPanel.add(error5, constraints);
                ValidationText(txtProfession,error5);


                break;
            case "Рабочие":
                constraints.gridx = 0;
                constraints.gridy = 3;
                newPanel.add(labelYears, constraints);

                constraints.gridx = 1;
                newPanel.add(txtYears, constraints);

                constraints.gridx = 2;
                newPanel.add(error6, constraints);
                ValidationNumber(txtYears,error6);

                break;
        }
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(btnAdd, constraints);

        btnAdd.addActionListener(this::save);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        newPanel.add(btnCancel, constraints);
        btnCancel.addActionListener(this::cancel);

        add(newPanel);

        error1.addComponentListener(this);
        error2.addComponentListener(this);
        error3.addComponentListener(this);
        error4.addComponentListener(this);
        error5.addComponentListener(this);
        error6.addComponentListener(this);

        pack();
        setLocationRelativeTo(null);

    }

    public void save(ActionEvent actionEvent) {

        int ID = model.getID(0);
        switch (this.model.getType()) {
            case "Сотрудники администрации":
                    model.Add(new Administration(ID,getInfo(txtName), getInfo(txtSurname), getInfo(txtAge), getInfo(txtMoney)),getInfo(txtMoney));
                break;
            case "Инженеры":
                    model.Add(new Engineer(ID,getInfo(txtName), getInfo(txtSurname), getInfo(txtAge), getInfo(txtProfession)),getInfo(txtProfession));
                break;
            case "Рабочие":
                    model.Add(new Worker(ID,getInfo(txtName), getInfo(txtSurname), getInfo(txtAge), getInfo(txtYears) ), getInfo(txtYears));
                break;
        }
        this.setVisible(false);
    }


    public String getInfo(JTextField txt)
    {
        String str = txt.getText();
        return GUI.firstUpperCase(str);
    }

    public void cancel(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    public void ValidationText(JTextField txt,JLabel lbl){
        txt.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(text.matches(".*\\d+.*")||text.isEmpty());
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(text.matches(".*\\d+.*")||text.isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
    public void ValidationNumber(JTextField txt,JLabel lbl){
        txt.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$"));
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    public void ValidationAge(JTextField txt,JLabel lbl){
        txt.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {

                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$")|| Integer.parseInt(text)>100);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                String text = txt.getText();

                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$")|| Integer.parseInt(text)>100);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }


    @Override
    public void componentResized(ComponentEvent componentEvent) {

    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {

    }

    @Override
    public void componentShown(ComponentEvent componentEvent) {
        btnAdd.setEnabled(false);
    }

    @Override
    public void componentHidden(ComponentEvent componentEvent) {
        btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error4.isVisible());


        switch (this.model.getType()) {
            case "Сотрудники администрации":
                btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error4.isVisible());
                break;
            case "Инженеры":
                btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error5.isVisible());
                break;
            case "Рабочие":
                btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error6.isVisible());
                break;
        }
    }
}


