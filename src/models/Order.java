package models;

import java.util.Date;

public class Order
{
    private int orderID;
    private Date dateCreated;
    private Reader reader;
    private double total;

    public Order(int orderID, Date dateCreated, Reader reader, double total)
    {
        this.orderID = orderID;
        this.dateCreated = dateCreated;
        this.reader = reader;
        this.total = total;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Reader getReader()
    {
        return reader;
    }

    public void setReader(Reader reader)
    {
        this.reader = reader;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }
}
