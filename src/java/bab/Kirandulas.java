/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author besse
 */
public class Kirandulas {
    public String bicikli;
    public int szervezo;
    public int hossz;
    public String vegcel; 
    public String cim; 
    public String indulas;
    public String mikor;
    public String leiras;
    
    private final Kapcsolat kapcs;
    private String uzenet;
    private ResultSet rs;
    private Statement st;
    private Connection con;
    
       public Kirandulas() {
        this.kapcs = new Kapcsolat();
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getBicikli() {
        return bicikli;
    }

    public void setBicikli(String bicikli) {
        this.bicikli = bicikli;
    }

    public int getSzervezo() {
        return szervezo;
    }

    public void setSzervezo(int szervezo) {
        this.szervezo = szervezo;
    }

    public int getHossz() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    public String getVegcel() {
        return vegcel;
    }

    public void setVegcel(String vegcel) {
        this.vegcel = vegcel;
    }

    public String getIndulas() {
        return indulas;
    }

    public void setIndulas(String indulas) {
        this.indulas = indulas;
    }

    public String getMikor() {
        return mikor;
    }

    public void setMikor(String mikor) {
        this.mikor = mikor;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }
       
    public ResultSet listaLeker() {  
      return rs;  
    }
    
    public void kirandulasInsert(int szervezo)
    {
        try 
        { 
            if(kapcs.conOK())
            {
                 
                String query = "INSERT INTO `kirandulasok` (cim, szervezo, hossz, vegcel, indulas, mikor,leiras) "
                        + "VALUES('"+cim+"','"+szervezo+"','"+hossz+"',"+vegcel+",'"+ indulas +"','"+mikor+"''"+leiras+"')";
                
                st = kapcs.getStm();
                st.executeUpdate(query); 
                //System.out.println(query);
                uzenet = "A kirándulás rögzítve!";
            }
            
        }
        catch (SQLException e)
        {
            uzenet = "Sikertelen adatrögzítés!" + e.getMessage();
        }
    }
     public void kirandulasSelect() 
    {
        try 
        {
            if (kapcs.conOK()) 
            {
                st = kapcs.getStm();
                rs = st.executeQuery("SELECT kirandulasok.*, szemelyek.nev FROM `kirandulasok`inner join szemelyek on kirandulasok.ki = szemelyek.id");
            }
          
        }
        catch (SQLException e) {
             uzenet = "Hibás adatlekérés! " + e;
        }
    }
}
