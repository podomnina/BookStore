package database.entities;

public class Book {
    private int id;
    private String name;
    private int pages;
    private int price;
    private String language;
    private int id_author;

    public Book(){}
    public Book(int id, String name,int id_author,int pages, int price, String language){
        this.id=id;
        this.id_author=id_author;
        this.language=language;
        this.name=name;
        this.pages=pages;
        this.price=price;
    }

    public void setId(int id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setAuthor(int id_author){this.id_author=id_author;}
    public void setPages(int pages){this.pages=pages;}
    public void setPrice(int price){this.price=price;}
    public void setLanguage(String language){this.language=language;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public int getAuthor(){return this.id_author;}
    public int getPages(){return this.pages;}
    public int getPrice(){return this.price;}
    public String getLanguage(){return this.language;}
}
