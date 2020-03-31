package com.example.amazonapp.Models;

public class ProductDetails {

        private String Title;
        // private String Category ;
        //private String Description ;
        private int Thumbnail ;


        public ProductDetails(String the_clothes, String bhjsjghukshfujdfjh, int cms) {
        }

        public ProductDetails(String title,int thumbnail) {
            Title = title;
            Thumbnail = thumbnail;
        }



        public String getTitle() {
            return Title;
        }


        public int getThumbnail() {
            return Thumbnail;
        }


        public void setTitle(String title) {
            Title = title;
        }


        public void setThumbnail(int thumbnail) {
            Thumbnail = thumbnail;
        }

    }


