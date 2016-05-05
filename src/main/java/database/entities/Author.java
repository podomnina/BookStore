package database.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {
    @JsonProperty("Number")
    private int id;
    @JsonProperty("Name")
    private String name;

    public Author(){}
    public Author(int id,String name){
        this.id=id;
        this.name=name;
    }

    public void setId(int id){this.id=id;}
    public void setName(String name){this.name=name;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
}
