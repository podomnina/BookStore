package database.tables;

public class Book {
    private int id_book;
    private String name;
    private Author author;
    private int pages;
    private int year;
    private String language;

    public Book(){}
    public Book(int id_book, String name,Author author,int pages, int year, String language){
        this.id_book=id_book;
        this.author=author;
        this.language=language;
        this.name=name;
        this.pages=pages;
        this.year=year;
    }

    public void setId(int id){this.id_book=id;}
    public void setName(String name){this.name=name;}
    public void setAuthor(Author author){this.author=author;}
    public void setPages(int pages){this.pages=pages;}
    public void setYear(int year){this.year=year;}
    public void setLanguage(String language){this.language=language;}

    public int getId(){return this.id_book;}
    public String getName(){return this.name;}
    public Author getAuthor(){return this.author;}
    public int getPages(){return this.pages;}
    public int getYear(){return this.year;}
    public String getLanguage(){return this.language;}
}
