package com.mycompany.app.classes.People;

import java.time.LocalDate;
import java.util.Queue;

public final class Receptionist extends Employee{
    private Queue<Client> clients;
    public Receptionist(String name, String lastName, String country, LocalDate BDay, int salary, String experience) {
        super(name, lastName, country, BDay, salary, experience);
    }

    @Override
    public String printInformation() {
        return "";
    }
    public void addClient(Client client){
        clients.add(client);
    }
    public int delClient(){
        if (!clients.isEmpty()){
            clients.remove();
            return clients.size();
        }else {
            return 0;
        }
    }
}
