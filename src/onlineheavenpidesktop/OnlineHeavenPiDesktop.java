/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlineheavenpidesktop;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import models.Category;
import models.Serie;
import services.CategoryService;
import services.SerieService;

/**
 *
 * @author Lord Solari
 */
public class OnlineHeavenPiDesktop {
    
       public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
       
        
        SerieService serieService=new SerieService();
        CategoryService categoryService=new CategoryService();
           
      /*serieService.createSerie(serie1);
            */
           
        String[] options = {"1- Display Categories",
                            "2- Get Category by ID ",
                            "3- Add category",
                            "4- Update category",
                            "5- Delete category",
                            "6- Display Series",
                            "7- Get Serie by ID ",
                            "8- Add Serie (predefined)",
                            "9- Update Serie(predefined only add new to attributes)",
                            "10- Delete Serie",
                            "11- Add Category to Serie",
                            "12- Get Serie Categories",
                            "13- Get Category Series",
                            "14- Exit",
        };
        
       
        
        Scanner scanner = new Scanner(System.in);
        int option;
        while (true){
            printMenu(options);
            option = scanner.nextInt();
             switch (option){
                    case 1:displayCategories(categoryService);break;
                    case 2:getCategoryById(categoryService);break;
                    case 3:addCategory(categoryService);break;
                    case 4:updateCategory(categoryService);break;
                    case 5:deleteCategory(categoryService);break;
                    case 6:displaySeries(serieService);break;
                    case 7:getSerieById(serieService);break;
                    case 8:addSerie(serieService);break;
                    case 9:updateSerie(serieService);break;
                    case 10:deleteSerie(serieService);break;
                    case 11:addSerieCategory(serieService);break;
                    case 12:getSerieCategories(serieService);break;
                    case 13:getCategorySeries(categoryService);break;
                    case 14:System.exit(0);
                }
        }
}
    
      private static void getCategorySeries(CategoryService categoryService) {
            Scanner scanner = new Scanner(System.in);
          System.out.print("Enter category id  : ");
       int categoryId=scanner.nextInt();
       List<Serie> listSeries=categoryService.getCategorySeries(categoryId);
              
                        for(Serie serie : listSeries) {
                        System.out.println(serie.getId()+ " "+serie.getName()+" "+serie.getDescription()+" "+serie.getImage_local_url()+" "+serie.getTrailer()+" "+serie.getRelease_date()+" "+serie.getStudio_name()+" "+serie.getViews_count()+" "+serie.getCreated_at());
    }}
      
     private static void getSerieCategories(SerieService serieService) {
          Scanner scanner = new Scanner(System.in);
          System.out.print("Enter serie id  : ");
       int serieId=scanner.nextInt();
       List<Category> listCategories=serieService.getSerieCategories(serieId);
                        for(Category category : listCategories) {
                        System.out.println(category.getId()+ " "+category.getName());
    }}
     
     private static void addSerieCategory(SerieService serieService) {
         Scanner scanner = new Scanner(System.in);
          System.out.print("Enter serie id  : ");
       int serieId=scanner.nextInt();
       System.out.print("Enter category id  : ");
       int categoryId=scanner.nextInt();
      serieService.addSerieCategory(serieId,categoryId);
        
   
    }
     
     private static void getSerieById(SerieService serieService) {
         Scanner scanner = new Scanner(System.in);
          System.out.print("Enter Serie id to find : ");
       int serieId=scanner.nextInt();
       Serie serie=serieService.getSerieById(serieId);
         System.out.println(serie.getId()+ " "+serie.getName()+" "+serie.getDescription()+" "+serie.getImage_local_url()+" "+serie.getTrailer()+" "+serie.getRelease_date()+" "+serie.getStudio_name()+" "+serie.getViews_count()+" "+serie.getCreated_at());
   
    }
     
     private static void displaySeries(SerieService serieService) {
       List<Serie> listSeries=serieService.readSeries();
              
                        for(Serie serie : listSeries) {
                        System.out.println(serie.getId()+ " "+serie.getName()+" "+serie.getDescription()+" "+serie.getImage_local_url()+" "+serie.getTrailer()+" "+serie.getRelease_date()+" "+serie.getStudio_name()+" "+serie.getViews_count()+" "+serie.getCreated_at());
    }}
      private static void addSerie(SerieService serieService) {
      
               Serie serie1=new Serie("nanatsu Desktp", "Nanatsu Description from desktop", "https://adala-news.fr/wp-content/uploads/2020/08/Nanatsu-no-Taizai-Fundo-no-Shinpan-anime-image.jpeg", "https://www.youtube.com/watch?v=J3xvndsxcpQ","2014-10-01",8.2F,1500,"2014-10-01","Solari Desktop Studios ");
                serieService.createSerie(serie1);
      }
      
       private static void updateSerie(SerieService serieService) {
      
                 Scanner scanner = new Scanner(System.in);
       
       System.out.print("Enter Serie id to update : ");
       int serieId=scanner.nextInt();
       Serie serie1=new Serie("nanatsu Desktp new ", "Nanatsu Description desktop new ", "https://adala-news.fr/wp-content/uploads/2020/08/Nanatsu-no-Taizai-Fundo-no-Shinpan-anime-image.jpeg new ", "https://www.youtube.com/watch?v=J3xvndsxcpQ new ","2014-10-01",8.2F,1500,"2014-10-01","Solari Desktop Studios new");
          
       serieService.updateSerie(serie1,serieId);
      }
     
      
       private static void deleteSerie(SerieService serieService) {
      
                 Scanner scanner = new Scanner(System.in);
       
       System.out.print("Enter Serie id to delete : ");
       int serieId=scanner.nextInt();
          
       serieService.deleteSerie(serieId);
      }
     
      
    
    private static void displayCategories(CategoryService categoryService) {
       List<Category> listCategories=categoryService.readCategories();
                        for(Category category : listCategories) {
                        System.out.println(category.getId()+ " "+category.getName());
    }}
              
      private static void getCategoryById(CategoryService categoryService) {
         Scanner scanner = new Scanner(System.in);
          System.out.print("Enter Category id to find : ");
       int categoryId=scanner.nextInt();
       Category category=categoryService.getCategoryById(categoryId);
         System.out.println(category.getId()+ " "+category.getName());
   
    }
     
      private static void addCategory(CategoryService categoryService) {
           Scanner scanner = new Scanner(System.in);
       
       System.out.print("Enter Category name : ");
      Category category1=new Category(scanner.next());
       categoryService.createCategory(category1);  
    }
       private static void updateCategory(CategoryService categoryService) {
           Scanner scanner = new Scanner(System.in);
       
       System.out.print("Enter Category id : ");
       int categoryId=scanner.nextInt();
       System.out.print("Enter Category new name : ");
      categoryService.updateCategory(scanner.next(),categoryId);
    }
       
         private static void deleteCategory(CategoryService categoryService) {
           Scanner scanner = new Scanner(System.in);
       
       System.out.print("Enter Category id : ");
       int categoryId=scanner.nextInt();
       
       categoryService.deleteCategory(categoryId);
    }
                        
}
    

