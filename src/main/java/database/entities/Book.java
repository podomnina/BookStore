package database.entities;

public class Book {
    private int id;
    private String name;
    private int pages;
    private int price;
    private String language;
    private Author author;

    public Book(){}
    public Book(int id, String name,int pages, int price, String language,Author author){
        this.id=id;
        this.author=author;
        this.language=language;
        this.name=name;
        this.pages=pages;
        this.price=price;
    }

    public void setId(int id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setAuthor(Author author){this.author=author;}
    public void setPages(int pages){this.pages=pages;}
    public void setPrice(int price){this.price=price;}
    public void setLanguage(String language){this.language=language;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public Author getAuthor(){return this.author;}
    public int getPages(){return this.pages;}
    public int getPrice(){return this.price;}
    public String getLanguage(){return this.language;}
}
