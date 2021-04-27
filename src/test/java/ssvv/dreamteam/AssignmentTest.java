package ssvv.dreamteam;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.XMLFileService.StudentXMLService;
import Service.XMLFileService.TemaLabXMLService;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

import java.text.NumberFormat;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class AssignmentTest {
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddAssignmentValid(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params={"1","descr","4","7"};
        try{
            temaLabXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(temaLabXMLService.findOne(1));
    }
    @Test
    public void testAddAssignmentIdInvalid(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        boolean fails=false;
        String[] params={"","descr","4","7"};
        try{
            temaLabXMLService.add(params);
        }catch (ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params2={null,"descr","4","7"};
        try{
            temaLabXMLService.add(params2);
        }catch (ValidatorException | NullPointerException | NumberFormatException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);
    }

    @Test
    public void testAddAssignmentDuplicateID(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params={"1","descr","4","7"};
        String[] params2={"1","descr2","3","8"};
        try{
            temaLabXMLService.add(params);
            temaLabXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertEquals(1, temaLabXMLService.getSize());
    }

    @Test
    public void testAddAssignmentDescInvalid(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params={"1","","4","7"};
        try{
            temaLabXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertEquals(0, temaLabXMLService.getSize());
    }

    @Test
    public void testAddAssignmentDeadlineInvalid(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params={"1","desc","-1","7"};
        try{
            temaLabXMLService.add(params);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        assertEquals(0, temaLabXMLService.getSize());
    }

    @Test
    public void testAddAssignmentPredareInvalid(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo trepo=new TemaLabXMLRepo(vt,"TemaLabXML.xml");
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(trepo);

        String[] params={"1","desc","1","17"};
        try{
            temaLabXMLService.add(params);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        assertEquals(0, temaLabXMLService.getSize());
    }
}

