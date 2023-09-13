package com.ti.example;

public class ConditionalStatements {
    public static void main(String[] args) {

        String hero = "Hulk";//"Iron-Man";
        //String loki = "";


            //Ejemplo If
        /*if(hero == "Iron-Man"){
            loki = "Mandarín";
            System.out.println(loki);
        }*/

            //Ejemplo If-Else
        /*if(hero == "Iron-Man"){
            loki = "Mandarín";
        }else {
            loki = "Loki";
        }

        System.out.println(loki);
        */

            //Ternary operator
            //System.out.println((hero == "Iron-Man")?"Mandarín":"Loki");


            //Switch
            /*switch (hero){
                case "Iron-Man" -> {
                    loki = "Mandarín";
                }
                case "Thor" ->{
                    loki = "Hela";
                }
                case "Spider-Man" ->{
                    loki = "Venom";
                }
                case "Hulk" ->{
                    loki = "Abomination";
                }
                case "DareDevil" ->{
                    loki = "KingPin";
                }
                default -> {
                    loki="Loki";
                }
            }*/

        //Switch better
        String loki = switch (hero) {
            case "Iron-Man" -> "Mandarín";
            case "Thor" -> "Hela";
            case "Spider-Man" -> "Venom";
            case "Hulk" -> "Abomination";
            case "DareDevil" -> "KingPin";
            default -> "";
        };

        System.out.println(loki);
    }
}
