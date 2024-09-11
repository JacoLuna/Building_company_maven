package com.mycompany.app.enums;

public enum Experience {
    TRAINEE("Trainee",1),
    JUNIOR("Junior",3),
    SENIOR("Senior",6),
    MANAGER("Manager",8);

    public final String tittle;
    public final int modifier;
    private final float minimumWage = 4000;

    Experience(String tittle, int modifier){
        this.tittle = tittle;
        this.modifier = modifier;
    }

    public float getSalary(){
        return minimumWage * modifier;
    }

    public void description(){
        StringBuilder sb = new StringBuilder();
        switch (tittle){
            case "Trainee" -> sb.append("0-2");
            case "Junior" -> sb.append("2-4");
            case "Senior" -> sb.append("4-8");
            case "Manager" -> sb.append("8-n");
        }
        sb.append("of experience");
        System.out.println(sb.toString());
    }
}
