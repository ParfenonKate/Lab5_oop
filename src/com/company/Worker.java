package com.company;

public class Worker extends Staff {
    private String Years;

    public Worker(int id,String n, String s,String a,String y) {
        super(id,n, s,a);
        this.Years=y;
        setPosition("Рабочий");
    }


    public static String convertYears(String Years)
    {
        String result;
        int y = Integer.parseInt(Years);
        if (y > 4 && y < 21) result = "\n\tСтаж работы: "+Years+" лет" ;
        else
        {
            int a = y % 10;

            if (a == 1) result = "\n\tСтаж работы: "+Years+" год"  ;

            else

            if (1 < a && a < 5) result = "\n\tСтаж работы: "+Years+" года"  ;

            else result = "\n\tСтаж работы: "+Years+"лет"  ;
        }

    return result;

    }

    public String getYears(){return this.Years;}


    @Override
    public String learnMore()
    {
        return "Рабочий:"  + super.getName() + super.getSurname()+
                convertYears(this.Years)+ "\n\t Возраст: " + super.getAge() ;
    }

    @Override
    public String toString() {
        return this.learnMore();
    }
}



