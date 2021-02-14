package com.shoppingcart;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class MainClass {

    public static void orderIt(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        System.out.println("Please input client id:");
        nextLine = reader.readLine();
        Integer clientID = Integer.parseInt(nextLine);
        System.out.println("Please input item id:");
        nextLine = reader.readLine();
        Integer itemID = Integer.parseInt(nextLine);
        session = factory.getCurrentSession();
        session.beginTransaction();
        Client client = session.get(Client.class, clientID);
        Item item = session.get(Item.class, itemID);
        Order order = new Order();
        order.setClient(client);
        order.setItem(item);
        order.setPrice(item.getPrice());
        session.save(order);
        session.getTransaction().commit();
        System.out.println("---------------------------");
    }
    public static void showAllOrders(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
//        System.out.println("Please input client id:");
//        nextLine = reader.readLine();
//        Integer clientID = Integer.parseInt(nextLine);
//        System.out.println("Please input item id:");
//        nextLine = reader.readLine();
//        Integer itemID = Integer.parseInt(nextLine);
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Order> allOrders = session.createQuery("from Order").getResultList();
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        for (Order o : allOrders) System.out.println(o);
//        Client client = session.get(Client.class, clientID);
//        Item item = session.get(Item.class, itemID);
//        Order order = new Order();
//        order.setClient(client);
//        order.setItem(item);
//        session.save(order);
        session.getTransaction().commit();
        System.out.println("---------------------------");
    }

    public static void showAllItems(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Item> allItems = session.createQuery("from Item").getResultList();
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        for (Item i : allItems) System.out.println(i);
        System.out.println("---------------------------");
        session.getTransaction().commit();
    }

    public static void showAllClients(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Client> allClients = session.createQuery("from Client").getResultList();
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        for (Client c : allClients) System.out.println(c);
        System.out.println("---------------------------");
        session.getTransaction().commit();
    }

    public static void addItem(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        System.out.println("Please input name of the item:");
        nextLine = reader.readLine();
        if (nextLine.equals("q")) return;
        session = factory.getCurrentSession();
        Item item = new Item();
        item.setName(nextLine);
        System.out.println("ECHO :: " + item.getName());
        System.out.println("Please input price of the item (in 9.99 format):"); //make the line transform , to . later
        nextLine = reader.readLine();
        System.out.println("ECHO :: " + nextLine);
        if (nextLine.equals("q")) return;
        item.setPrice(Double.parseDouble(nextLine));
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        System.out.println("---------------------------");
    }

        public static void removeItem(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        System.out.println("Please input ID of the item:");
        nextLine = reader.readLine();
        if (nextLine.equals("q")) return;
        Integer itemID = Integer.parseInt(nextLine);
        session = factory.getCurrentSession();
        session.beginTransaction();
        Item item = session.get(Item.class, itemID);
        session.delete(item);
        session.getTransaction().commit();
        System.out.println("---------------------------");
    }
        public static void updateItem(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        System.out.println("Please input ID of the item:");
        nextLine = reader.readLine();
        if (nextLine.equals("q")) return;
        Integer itemID = Integer.parseInt(nextLine);
        System.out.println("Please input ID new price of the item: ");
        nextLine = reader.readLine();
        if (nextLine.equals("q")) return;
        Double newPrice = Double.parseDouble(nextLine);
        session = factory.getCurrentSession();
        session.beginTransaction();
        Item item = session.get(Item.class, itemID);
        item.setPrice(newPrice);
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println(item);
        System.out.println("---------------------------");
        session.getTransaction().commit();
    }

    public static void removeClients(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        System.out.println("Please input clients ID you want to delete: ");
        nextLine = reader.readLine();
        System.out.println("ECHO :: " + nextLine);
        if (nextLine.equals("q")) return;
        session = factory.getCurrentSession();
        session.beginTransaction();
        Integer clientID = Integer.parseInt(nextLine);
        Client client = session.get(Client.class, clientID);
        session.delete(client);
        session.getTransaction().commit();
        System.out.println("---------------------------");
    }
    public static void addClients(String nextLine, BufferedReader reader, Session session, SessionFactory factory) throws IOException {
        System.out.println("Please input clients name:");
        nextLine = reader.readLine();
        System.out.println("ECHO :: " + nextLine);
        if (nextLine.equals("q")) return;
        session = factory.getCurrentSession();
        Client client = new Client();
        client.setName(nextLine);
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        System.out.println("---------------------------");
    }

    public static void main(String[] args) throws IOException {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        // CRUD
        Session session = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = "";
        session = factory.getCurrentSession();


        try {
            while (true) {
                System.out.println("What shall we do now?");
                System.out.println("1 - add client");
                System.out.println("2 - delete client");
                System.out.println("3 - add items");
                System.out.println("4 - delete item");
                System.out.println("5 - update item price");
                System.out.println("6 - show all items");
                System.out.println("7 - show all clients");
                System.out.println("8 - register a new purchase");
                System.out.println("9 - show all orders");
                System.out.println("q - quit");
                System.out.println("---------------------------");
                nextLine = reader.readLine();
                if (nextLine.equals("q")) break;
                if (nextLine.equals("1")) {
                    addClients(nextLine, reader, session, factory);
                }
                if (nextLine.equals("2")) {
                    removeClients(nextLine, reader, session, factory);
                }
                if (nextLine.equals("3")) {
                    addItem(nextLine, reader, session, factory);
                }
                if (nextLine.equals("4")) {
                    removeItem(nextLine, reader, session, factory);
                }
                if (nextLine.equals("5")) {
                    updateItem(nextLine, reader, session, factory);
                }
                if (nextLine.equals("6")) {
                    showAllItems(nextLine, reader, session, factory);
                }
                if (nextLine.equals("7")) {
                    showAllClients(nextLine, reader, session, factory);
                }
                if (nextLine.equals("8")) {
                    orderIt(nextLine, reader, session, factory);
                }
                if (nextLine.equals("9")) {
                    showAllOrders(nextLine, reader, session, factory);
                }

            }
        } finally {
            factory.close();
            session.close();
        }


    }
}
