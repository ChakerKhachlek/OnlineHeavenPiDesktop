/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.xml.stream.events.Comment;

/**
 *
 * @author inesz
 */
public interface IServiceComment {
    
    //add
    void addComment(Comment c);
    void modifyComment(Comment c);
    void deleteComment(Comment c);
    
    //select all
    List<Comment> fetchAllComment();
    
}
