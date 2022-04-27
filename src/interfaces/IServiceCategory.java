/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Category;
import models.Serie;

/**
 *
 * @author Lord Solari
 */
public interface IServiceCategory {
     public int createCategory(Category c);
     public List<Category> readCategories();
     public Category getCategoryById(int id);
     public void updateCategory(String name, int id);
     
     public void deleteCategory(int id);
     public List<Serie> getCategorySeries(int categoryId);
}
