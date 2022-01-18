package Repository;

import Entity.Ticket;

import java.util.HashMap;

public class TicketRepository {
    private HashMap<String , Ticket> ticketList;

    public TicketRepository(){
        ticketList = new HashMap<>();
    }

    public HashMap<String, Ticket> getTicketRepository() {
        return ticketList;
    }

    public void setTicketRepository(HashMap<String, Ticket> ticketRepository) {
        this.ticketList = ticketRepository;
    }

    public void generateTicket(String name, int source_x,int source_y,int destination_x,int destination_y) {
        Ticket ticket = new Ticket();
        ticket.setSource(source_x,source_y);
        ticket.setDestination(destination_x,destination_y);
        ticketList.put(name,ticket);
    }

    public Ticket getTicket(String user) {
        if(!ticketList.containsKey(user)){
            //error - no ticket for user, find ride before choosing
        }
        return ticketList.get(user);
    }

    public void changeTicketStatus(String user, boolean status) {
        Ticket ticket = ticketList.get(user);
        ticket.setStatus(status);
    }
}
