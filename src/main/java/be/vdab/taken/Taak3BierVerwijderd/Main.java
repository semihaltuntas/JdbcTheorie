package be.vdab.taken.Taak3BierVerwijderd;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        BierRepository bierRepository= new BierRepository();
        try{
            System.out.println(bierRepository.deleteIfAlcoholNull());
            System.out.println(" null Alcohol Bieren is verwijderd.");
        }catch (SQLException ex){
            ex.printStackTrace(System.err);
        }
    }
}
