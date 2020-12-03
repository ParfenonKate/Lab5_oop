package GUI;

import com.company.Administration;
import com.company.Engineer;
import com.company.Staff;
import com.company.Worker;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModelStaff extends AbstractTableModel  {


    public ArrayList<Staff> people = new ArrayList<>();
    private DB db= new DB();
    public String Position = "";
    private String[] Positions  = {"Работник Администрации",
            "Инженер",
            "Рабочий"};

    public MyTableModelStaff() {
        super();
        try {
            db.Connecting();

            people = db.read();

            db.CloseDB();
            System.out.println("Данные считаны.Подключения закрыты!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Подключения закрыты!");
        }
    }

    public void Add(Staff s , String info) {

        try {
            db.Connecting();

            db.add(s,info);//добавить в дб
            people.add(s);//добавить в табл

            db.CloseDB();
            System.out.println("Запись добавлена.Подключение закрыто!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Подключение закрыто!");
        }
        this.fireTableDataChanged();
    }


    public void search(String name,String surname) {
        ArrayList<Staff> res = new ArrayList<>();
        for (Staff s : this.getStaff()) {
            if ((s.getSurname().equals(surname) && s.getName().equals(name) )|| (s.getSurname().equals(name) && s.getName().equals(surname)))
            {
                res.add(s);
            }
        }
        String found="<html><ul>";
        for (Staff s:res)
        {
            found += "<li>"+ s.getPosition()+" " + s.getName() +" "+s.getSurname()+"</li>";
        }
        found+= "</ul><html>";

        if (res.size() > 1 )
        {
            Object result = JOptionPane.showInputDialog(
                    null,
                    new String[] {"По вашему запросу найдено несколько сотрудников:",
                            found,
                            "Для уточнения выберете номер из списка"},
                    "Результат поиска",
                    JOptionPane.QUESTION_MESSAGE,
                    null, Positions, Positions[0]);
            for(Staff s:res)
            {
                if(s.getPosition() == result)  
                {   JOptionPane.showMessageDialog(null,
                        s.learnMore(),
                        "Сотрудник",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
            else if (res.size() == 1 ){
            for(Staff s:res)
            {
                {   JOptionPane.showMessageDialog(null,
                        s.learnMore(),
                        "Сотрудник",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
            }
            else {JOptionPane.showMessageDialog(null,
                "Сотрудник не найден.",
                "Не найдено",
                JOptionPane.ERROR_MESSAGE);}
    }


    public String getType() {
        return Position;
    }

    public void setType(String position) {
        this.Position = position;
        this.fireTableStructureChanged();
    }

    public Staff[] getStaff() {
        switch (Position) {
            case "Сотрудники администрации":
                return people.stream().filter(staff -> staff instanceof Administration).toArray(Staff[]::new);
            case "Инженеры":
                return people.stream().filter(staff -> staff instanceof Engineer).toArray(Staff[]::new);
            case "Рабочие":
                return people.stream().filter(staff -> staff instanceof Worker).toArray(Staff[]::new);
        }
        return people.toArray(new Staff[0]);
    }

    public void delete(String name,String surname) {
        ArrayList<Staff> res = new ArrayList<>();
        for (Staff s : this.getStaff()) {
            if ((s.getSurname().equals(surname) && s.getName().equals(name) )|| (s.getSurname().equals(name) && s.getName().equals(surname)))
            {
                res.add(s);
            }
        }
        String found="<html><ul>";
        for (Staff s:res)
        {
            found += "<li>"+ s.getPosition()+" " + s.getName() +" "+s.getSurname()+"</li>";
        }
        found+= "</ul><html>";

        if (res.size() > 1 )
        {
            Object result = JOptionPane.showInputDialog(
                    null,
                    new String[] {"По вашему запросу найдено несколько сотрудников:",
                            found,
                            "Для уточнения выберете номер из списка"},
                    "Результат поиска",
                    JOptionPane.QUESTION_MESSAGE,
                    null, Positions, Positions[0]);
            for(Staff s:res)
            {
                if(s.getPosition() == result)
                {   int r = JOptionPane.showConfirmDialog(null,
                        "Вы точно хотите уволить (удалить) сотрудника " + surname + " " + name + "?",
                        "Подтверждение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                    if (r == JOptionPane.YES_OPTION)
                    {
                        try {
                            db.Connecting();

                            db.delete(s.getId());
                            people.remove(s);
                            db.CloseDB();
                            System.out.println("Запись удалена.Подключение закрыто");
                        } catch (ClassNotFoundException | SQLException e) {
                            System.out.println("Подключение закрыто");
                        }
                        JOptionPane.showMessageDialog(null,
                                "Сотрудник успешно удален.",
                                "Успешно",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        else if (res.size() == 1 ){
            for(Staff s:res)
            {
                int r = JOptionPane.showConfirmDialog(null,
                        "Вы точно хотите уволить (удалить) сотрудника " + surname + " " + name + "?",
                        "Подтверждение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (r == JOptionPane.YES_OPTION)
                {
                    try {
                        db.Connecting();

                        db.delete(s.getId());
                        people.remove(s);
                        db.CloseDB();
                        System.out.println("Запись удалена.Подключение закрыто");

                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println("Подключение закрыто");
                    }
                    JOptionPane.showMessageDialog(null,
                            "Сотрудник успешно удален.",
                            "Успешно",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else {JOptionPane.showMessageDialog(null,
                "Сотрудник не найден.",
                "Не найдено",
                JOptionPane.ERROR_MESSAGE);}

        this.fireTableDataChanged();
        }

    @Override
    public int getRowCount() {
        return getStaff().length;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 1:
                return this.getStaff()[r].getName();
            case 2:
                return this.getStaff()[r].getSurname();
            case 3:
                return this.getStaff()[r].getAge();
            case 4:
                Staff s = this.getStaff()[r];
                if (s instanceof Administration)
                    return ((Administration) this.getStaff()[r]).getMoney();
                if (s instanceof Engineer)
                    return ((Engineer) this.getStaff()[r]).getProfession();
                if (s instanceof Worker)
                    return ((Worker) this.getStaff()[r]).getYears();
                break;
            case 0:
                return this.getStaff()[r].getPosition();
            default:
                return "";
        }
        return "";
    }

    @Override
    public String getColumnName(int c) {
        String сolumn = "";
        switch (c) {
            case 1:
                сolumn = "Имя";
                break;
            case 2:
                сolumn = "Фамилия";

                break;
            case 3: сolumn = "Возраст";
                break;
            case 4:
                switch (Position) {
                    case "Сотрудники администрации":
                        сolumn = "Заработная плата (р/мес) ";
                        break;
                    case "Инженеры":
                        сolumn = "Специальность";
                        break;
                    case "Рабочие":
                        сolumn = "Стаж работы";
                        break;
                    default:
                        сolumn = "Зарплата/Специальность/Стаж";
                        break;
                }
                break;
            case 0: сolumn ="Должность";
            break;
        }
        return сolumn;
    }

    public int getID(int id) {
        for (int i=0;i<people.size();i++)
        {
            if(people.get(i).getId() > id) id = people.get(i).getId();
        }
        return id+1;
    }
}
