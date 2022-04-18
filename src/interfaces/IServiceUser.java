/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.User;

/**
 *
 * @author Lord Solari
 */
public interface IServiceUser {
     public void createUser(User u);
    public List<User> readUser();
    public boolean delete(int id);
    public boolean update(User u);
    public void clean();
    public List<User> diasplayAllN();
    public List<User> diasplayAllP();
    public List<User> diasplayAllA();
    public String getByMail(String name);
}
