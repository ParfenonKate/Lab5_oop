package GUI;

import com.company.*;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    public static Connection conn;
    public static Statement statement;
    public static ResultSet data;
    // --------Соединение--------
    public static void Connecting() throws ClassNotFoundException, SQLException
    {
        conn = null;
        DriverManager.registerDriver(new JDBC());
        conn = DriverManager.getConnection("jdbc:sqlite:StaffList.db");

        System.out.println("База Подключена!");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statement.close();
        data.close();

        //System.out.println("Соединения закрыты");
    }

    // --------Чтение--------
    public static ArrayList<Staff> read() throws ClassNotFoundException, SQLException {

        ArrayList<Staff> list = new ArrayList<>();

        statement = conn.createStatement();

        data = statement.executeQuery(String.format("SELECT * FROM staff"));

        while(data.next())
        {
            int id= data.getInt("id");
            String position = data.getString("position");
            String  name = data.getString("name");
            String  surname = data.getString("surname");
            String  age = data.getString("age");
            String  years = data.getString("years");
            String  money = data.getString("money");
            String  profession = data.getString("profession");

            if (position.equals("Работник Администрации")) {
                Administration adm = new Administration(id,name, surname, age, money);
                list.add(adm);
            } else if (position.equals("Рабочий")) {

                Worker w = new Worker(id,name, surname, age, years);
                list.add(w);
            }
            else if (position.equals("Инженер")){
                Engineer e = new Engineer(id,name, surname, age, profession);
                list.add(e);
            }
        }
        return list;
    }


    public void add(Staff s,String info) throws SQLException {

        switch (s.getPosition())
        {
            case "Работник Администрации": addAdm(s, info);
                break;

            case "Рабочий" :addWor(s, info);
                break;
            case "Инженер" : addEng(s, info);
                break;
        }
    }

    public void addEng(Staff s,String info) throws SQLException {

        PreparedStatement statement = this.conn.prepareStatement(
                "INSERT INTO staff ('position', 'name', 'surname', 'age', 'profession') VALUES(?, ?, ?, ?, ?)");

        statement.setObject(1, s.getPosition());
        statement.setObject(2, s.getName());
        statement.setObject(3, s.getSurname());
        statement.setObject(4, s.getAge());
        statement.setObject(5, info);
        // Выполняем запрос
        statement.execute();
    }

    public void addAdm(Staff s,String info) throws SQLException
    {
        PreparedStatement statement = this.conn.prepareStatement(
                "INSERT INTO staff ('position', 'name', 'surname', 'age', 'money') VALUES(?, ?, ?, ?, ?)");

        statement.setObject(1, s.getPosition());
        statement.setObject(2, s.getName());
        statement.setObject(3, s.getSurname());
        statement.setObject(4, s.getAge());
        statement.setObject(5, info);
        // Выполняем запрос
        statement.execute();

    }

    public void addWor(Staff s,String info) throws SQLException {
        int years = Integer.parseInt(String.valueOf(info));
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "INSERT INTO staff ('position', 'name', 'surname', 'age', 'years') VALUES(?, ?, ?, ?, ?)");

            statement.setObject(1, s.getPosition());
            statement.setObject(2, s.getName());
            statement.setObject(3, s.getSurname());
            statement.setObject(4, s.getAge());
            statement.setObject(5, years);
            // Выполняем запрос
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
               "DELETE FROM staff WHERE id = ? "
        );
        statement.setObject(1, id);

        // Выполняем запрос
        statement.execute();
    }



}
