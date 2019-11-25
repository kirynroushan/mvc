/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import mvc.model.Maulana;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author maulana
 */
public interface interMaulana {
     Maulana insert(Maulana o) throws SQLException;

    void update(Maulana o) throws SQLException;

    void delete(int ids) throws SQLException;
    
    Maulana find(int ids) throws SQLException;

    List<Maulana> getAll(String nama) throws SQLException;
}
