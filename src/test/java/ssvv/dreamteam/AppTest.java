package ssvv.dreamteam;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.XMLFileService.NotaXMLService;
import Service.XMLFileService.StudentXMLService;
import Service.XMLFileService.TemaLabXMLService;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudent(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);
        String[] params2={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(studentXMLService.findOne("1"));
    }

    @Test
    public void testAddStudentAddTema(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params2={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(studentXMLService.findOne("1"));

        String[] params={"1","descr","4","7"};
        try{
            temaLabXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(temaLabXMLService.findOne(1));
    }

    @Test
    public void testAddStudentAddTemaAddNota() throws IOException {
        NotaValidator vt=new NotaValidator();
        NotaXMLRepo repo=new NotaXMLRepo(vt,"NotaXML.xml");
        NotaXMLService notaXMLService = new NotaXMLService(repo);
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);
        TemaLabValidator vt2=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt2,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params2={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(studentXMLService.findOne("1"));

        String[] params={"1","descr","4","7"};
        try{
            temaLabXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(temaLabXMLService.findOne(1));

        String[] params3={"1","1","1","9","2017-01-13T17:09:42.411"};
        try{
            notaXMLService.add(params3);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        assertEquals(1, notaXMLService.getSize());
    }
}
