package aam18_lab3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class WatchsDAO implements IWatchsDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Watchs customer) { // Реализация вставки новой записи

        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO watchs (brand, mechanism) VALUES(?,?)",
                new Object[]{customer.getBrand(), customer.getMechanism()});
    }

    @Override
    public void append(String brand, String mechanism) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO watchs (brand, mechanism) VALUES(?,?)", new Object[]{brand, mechanism});
    }

    @Override
    public void deleteByBrand(String brand) {  // Реализация удаления записей по бренду
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM watchs WHERE brand LIKE ?", new Object[]{'%' + brand + '%'});
    }

    @Override
    public void delete(final String brand, final String mechanism) {  // Реализация удаления записей с указанными брендом и механизмом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE FROM watchs WHERE brand = ? AND mechanism = ?", new Object[]{brand, mechanism});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM watchs");
    }

    @Override
    public void update(String newMechanism, String oldMechanism) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE watchs SET mechanism = ? WHERE mechanism = ?", new Object[]{newMechanism, oldMechanism});
    }

    @Override
    public List<Watchs> findByBrand(String brand) {  // Реализация поиска записей по бренду
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM watchs WHERE brand LIKE ?";
        List<Watchs> watchs = select.query(sql, new Object[]{'%' + brand + '%'}, new WatchsRowMapper());
        return watchs;
    }

    @Override
    public List<Watchs> select(String brand, String mechanism) {  // Реализация получения записей с заданными брендом и механизмом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT  * FROM watchs WHERE brand = ? AND mechanism= ?",
                new Object[]{brand, mechanism}, new WatchsRowMapper());
    }

    @Override
    public List<Watchs> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM watchs", new WatchsRowMapper());
    }
    
    @Override
    public void appendOnlyBrand(String brand) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO watchs (brand, mechanism) VALUES(?,?)", new Object[]{brand, "????"});
    }
    
    @Override
    public void deleteByMechanism(String mechanism) {  // Реализация удаления записей по бренду
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM watchs WHERE mechanism LIKE ?", new Object[]{'%' + mechanism + '%'});
    }
    
    @Override
    public void updateBrand(String newBrand, String oldBrand) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE watchs SET brand = ? WHERE brand = ?", new Object[]{newBrand, oldBrand});
    }
    
    @Override
    public List<Watchs> findByMechanism(String mechanism) {  // Реализация поиска записей по бренду
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM watchs WHERE mechanism LIKE ?";
        List<Watchs> watchs = select.query(sql, new Object[]{'%' + mechanism + '%'}, new WatchsRowMapper());
        return watchs;
    }
    
    @Override
    public List<Watchs> selectByBrand(String brand) {  // Реализация получения записей с заданными брендом и механизмом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT  * FROM watchs WHERE brand = ?",
                new Object[]{brand}, new WatchsRowMapper());
    }
    
    @Override
    public List<Watchs> selectByMechanism(String mechanism) {  // Реализация получения записей с заданными брендом и механизмом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT  * FROM watchs WHERE mechanism = ?",
                new Object[]{mechanism}, new WatchsRowMapper());
    }
}
