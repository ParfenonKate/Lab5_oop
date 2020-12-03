package GUI;
import com.company.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Locale;

public class GUI extends JFrame {

    public static JTable tbl;
    public JScrollPane scroll;


    public JComboBox cmbStaffType;

    public JButton add;
    public JButton search;
    public JButton delete;
    public JButton menu;

    public static JPanel panel1;
    public static JPanel panel2;

    public MyTableModelStaff people = new MyTableModelStaff();
    static ArrayList<Staff> data;

    public GUI() { super("Работа с кадрами"); }

    public static void gui() {
        GUI frame = new GUI();
        frame.panelsParameters();
        frame.pack();
        frame.setResizable(false);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getInputContext().selectInputMethod(new Locale("ru","RU"));



    }

    private void panelsParameters()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));


        tbl = new JTable();
        tbl.setPreferredScrollableViewportSize(new Dimension(950, 400));
        scroll = new JScrollPane(tbl);
        tbl.setModel(people);
        panel1.add(scroll);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        cmbStaffType = new JComboBox(new String[]{
                "Все",
                "Сотрудники администрации",
                "Инженеры",
                "Рабочие"
        });

        panel2.add(new JLabel ("Поск по должности:"));

        cmbStaffType.addActionListener(this::selectStaff);
        panel2.add(cmbStaffType);


        search = new JButton("Поиск по ФИО");
        search.addActionListener(this::searchStaff);
        panel2.add(search);


        panel2.add(new JLabel ("                          "));



        delete = new JButton("Уволить сотрудника");
        delete.addActionListener(this::deleteStaff);
        panel2.add(delete);


        add = new JButton("Добавить");
        add.addActionListener(this::AddStaff);
        panel2.add(add);


        menu = new JButton("Главная");
        menu.addActionListener(this::Menu);
        panel2.add(menu);


        panel.add(panel1);
        panel.add(panel2);

        getContentPane().add(panel);

        this.selectStaff(null);
    }

    private void Menu(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    private void AddStaff(ActionEvent actionEvent) {
        AddForm a = new AddForm(people);
    }


    private void selectStaff(ActionEvent e) {
        String selectedValue = (String) this.cmbStaffType.getSelectedItem();
        people.setType((String) this.cmbStaffType.getSelectedItem());

        add.setEnabled(selectedValue.equals("Сотрудники администрации") || selectedValue.equals("Инженеры") || selectedValue.equals("Рабочие"));
    }


    private void deleteStaff(ActionEvent e) {
        String input = JOptionPane.showInputDialog(null, "Введите имя и фамилию сотрудника:",
                "Удаление",JOptionPane.QUESTION_MESSAGE);
        if (input.equals("") || !input.contains(" ")){
            JOptionPane.showMessageDialog(null,
                    "Неверный ввод.Попробуйте еще раз.Введите фамилию и имя через пробел!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
            {
                String [] search = input.split(" ");
                String searchName=search[1];
                String searchSurname=search[0];
                String s1 = firstUpperCase(searchName);
                String s2 =firstUpperCase(searchSurname);
                people.delete(s1,s2);
            }
    }
    public static String firstUpperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private void searchStaff(ActionEvent e){

        String input = JOptionPane.showInputDialog(null, "Введите имя и фамилию сотрудника:",
                "Поиск",JOptionPane.QUESTION_MESSAGE);
        if (input.equals("") || !input.contains(" ")){
            JOptionPane.showMessageDialog(null,
                    "Неверный ввод.Попробуйте еще раз.Введите фамилию и имя через пробел!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            String [] search = input.split(" ");
            String searchName=search[1];
            String searchSurname=search[0];
            String s1 = firstUpperCase(searchName);
            String s2 =firstUpperCase(searchSurname);
            people.search(s1,s2);
        }
    }
}












