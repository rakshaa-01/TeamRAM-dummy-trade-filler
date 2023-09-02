# Dummy Order Fulfillment Simulator

### Outline
This is a simple spring-boot app that will simulate a system that sends orders to be executed at an exchange. This system does not actually send the reuests to an exchange, it is just a SIMULATOR for testing.

On a timed interval this service will read from a MySQL database.

This system assumes the database that it reads from will have a table called "teamram" which will have a column called "status_code".

You can change the above in the file OrderProcessingSimulator.java

Each trade request is expected to move through the following states:

* 0 : (intial state) All orders are initially marked with this state to indicate the request has just been created.
* 1 : (processing) When an order has been simulated as sent to an exchange it will be marked with this to indicate they are currently being handled.
* 2 : (sucess) An order that was successfull will be marked with this.
* 3 : (failed)An order that was unsuccessfull will be marked awith this.
