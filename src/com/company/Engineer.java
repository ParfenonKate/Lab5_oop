package com.company;

public class Engineer extends Staff {
    private String Profession;
    public Engineer(int id,String n, String s,String a,String p) {
        super(id,n, s,a);
        this.Profession=p;
        setPosition("Инженер");
    }
    public String getProfession(){return this.Profession;}


    @Override
    public String learnMore()
    {
        return "Инженер:"  + super.getName() +" "+ super.getSurname()+
                "\n\tСпециальность: " + getProfession() + "\n\t Возраст: " + super.getAge() ;
    }

    @Override
    public String toString() {
        return this.learnMore();
    }
}
