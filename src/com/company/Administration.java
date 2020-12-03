package com.company;

public class Administration extends Staff {

    private String Money;

    public Administration (int id,String n,String s,String a,String m)
    {
        super(id,n,s,a);
        this.Money = m;
        setPosition("Работник Администрации");
    }
    public String getMoney ()
    {
        return this.Money;
    }

    @Override
    public String learnMore()
    {
        return "Работник Администрации: "  + super.getName() + " "+super.getSurname()+
                "\n\tЗаработная плата: " + getMoney() + " р/мес."+"\n\t Возраст: " + super.getAge() ;
    }

    @Override
    public String toString() {
        return this.learnMore();
    }
}
