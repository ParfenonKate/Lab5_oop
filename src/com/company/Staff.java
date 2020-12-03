package com.company;

public abstract class Staff {

    private int id;
    private String Name; //Имя
    private String Surname;//Фамилия
    private String age;//Возраст
    private String position;//Должность


    //Конструктор
    public Staff(int id,String n,String s,String a)
    {
        this.id = id;
        this.Name=n;
        this.Surname=s;
        this.age = a;
    }

    public String getName ()
    {
        return this.Name;
    }


    public String getSurname ()
    {
        return this.Surname;
    }



    public String getAge ()
    {
        return age;
    }


    public String getPosition () {return this.position; }

    public void setPosition(String p )
    {
        this.position = p;
    }


    public String learnMore()
    {
        return "Сотрудник: " + getName()+ " "+getSurname()+"\n\t Возраст: " + getAge();
    }

    public int getId(){return this.id;}

 }
