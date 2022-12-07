package aam18_lab3;

import java.util.List;
import javax.sql.DataSource;

public interface IWatchsDAO {
    void setDataSource(DataSource ds);                          
    void insert(Watchs customer);                                
    void append(String Brand, String Mechanism);
    void appendOnlyBrand(String Brand);
    void deleteByBrand(String Brand);    
    void deleteByMechanism(String Mechanism);
    void delete(String Brand, String Mechanism);                 
    void deleteAll();                                           
    void update(String newMechanism, String oldMechanism);
    void updateBrand(String newBrand, String oldBrand);
    List<Watchs> findByBrand(String Brand);
    List<Watchs> findByMechanism(String Mechanism);
    List<Watchs> select(String Brand, String Mechanism);
    List<Watchs> selectByBrand(String Brand);
    List<Watchs> selectByMechanism(String Mechanism);
    List<Watchs> selectAll();
}
