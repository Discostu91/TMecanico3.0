package controlador;
import java.sql.*;
/**

 */
public class MecanicoMantencion {
  conectate con;
 
  public MecanicoMantencion(){
    con = new conectate();
  } 
  
  /*Añade un nuevo registro*/

   
   public void GuardarMecanicoMantencion(String id, String secuencia){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                    "mecanico_mantencion(descripcion) " +
                    " values(?,?)");            
            pstm.setString(1, id);
            pstm.setString(2, secuencia);
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }
   
   public void EliminarMecanicoMantencion(String id){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from mecanico_mantencion where id_mecanico = ?");            
                pstm.setString(1, id);                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }

    
 /*obtenemos todos los datos de la tabla*/
 public Object [][] getDatos(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(1) as total FROM marca ");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.out.println(e);
      }
      
    Object[][] data = new String[registros][2];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
            "id_mecanico,"+
            " secuencia" +
            " FROM mecanico_mantencion" +
            " ORDER BY id_mecanico ");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String estid = res.getString("id_mecanico");
            String estsecuencia = res.getString("secuencia");
            data[i][0] = estid;
            data[i][1] = estsecuencia;                           
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }    
}
