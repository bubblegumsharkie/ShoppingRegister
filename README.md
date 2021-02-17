# shopkeeper app

It is designed to keep records of all customers, items and orders (with buying price, because price of the items can be changed) in a SQL database.
For now it only shows info in terminal.

Tech stack: Maven, Hibernate, PostgreSQL, Flyway.

# how to run

First of all, download the project and open it in IDE. You'll need PostgreSQL instaled as well. Go to pom.xml and change info on DB in flyway-migration dependency. Then go to hibernate.cfg.xml and change DB info there as well. Now you are good to go, but the DB is now empty, so you might want to use flyway to migrate V1__init.sql or just copy-paste those SQL lines to psql.

# what can it do

Current features are: 

1 - add client

2 - delete client

3 - add items

4 - delete item

5 - update item price

6 - show all items

7 - show all clients

8 - register a new purchase

9 - show all orders
