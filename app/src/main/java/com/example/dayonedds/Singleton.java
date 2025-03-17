package com.example.dayonedds;

public class Singleton {



        private static Singleton instance;

        static String myName;

        private Singleton(){
            myName = "Welcome";
        }

        public static Singleton getInstance(){
            if (instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }


