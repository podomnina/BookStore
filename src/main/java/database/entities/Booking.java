package database.entities;

public class Booking {
    private int id;
    private Book book;
    private Customer customer;

    public Booking(){}
    public Booking(int id, Book book, Customer customer){
        this.id=id;
        this.book=book;
        this.customer=customer;
    }

    public void setId(int id){this.id=id;}
    public void setBook(Book book){this.book=book;}
    public void setCustomer(Customer customer){this.customer=customer;}

    public int getId(){return this.id;}
    public Book getBook(){return this.book;}
    public Customer getCustomer(){return this.customer;}
}
