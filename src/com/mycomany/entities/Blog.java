/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author user
 */
public class Blog {
     
    private int id_blog;
    private String titre_blog, text_blog, image_blog;
    private float rating;
   

    public Blog() {
        
    }
     public Blog(int id_blog, String titre_blog, String text_blog, String image_blog, float rating) {
        this.id_blog = id_blog;
        this.titre_blog = titre_blog;
        this.text_blog = text_blog;
        this.image_blog = image_blog;
        this.rating = rating;
    }

    public Blog(String titre_blog, String text_blog, String image_blog, float rating) {
        this.titre_blog = titre_blog;
        this.text_blog = text_blog;
        this.image_blog = image_blog;
        this.rating = rating;
    }

    public Blog(String titre_blog, String text_blog, String image_blog) {
        this.titre_blog = titre_blog;
        this.text_blog = text_blog;
        this.image_blog = image_blog;
    }
    
    

    public int getId_blog() {
        return id_blog;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public String getTitre_blog() {
        return titre_blog;
    }

    public void setTitre_blog(String titre_blog) {
        this.titre_blog = titre_blog;
    }

    public String getText_blog() {
        return text_blog;
    }

    public void setText_blog(String text_blog) {
        this.text_blog = text_blog;
    }

    public String getImage_blog() {
        return image_blog;
    }

    public void setImage_blog(String image_blog) {
        this.image_blog = image_blog;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Blog{" + "id_blog=" + id_blog + ", titre_blog=" + titre_blog + ", text_blog=" + text_blog + ", image_blog=" + image_blog + ", rating=" + rating + '}';
    }

   

  
}
