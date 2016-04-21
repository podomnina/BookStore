package database.tables;

public class Author {
    private int id_author;
    private String name;

    public Author(){}
    public Author(int id_author,String name){
        this.id_author=id_author;
        this.name=name;
    }

    public void setId(int id){this.id_author=id;}
    public void setName(String name){this.name=name;}

    public int getId(){return this.id_author;}
    public String getName(){return this.name;}
}
