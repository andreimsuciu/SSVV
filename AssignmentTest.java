package ssvv.dreamteam;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.XMLFileService.StudentXMLService;
import Service.XMLFileService.TemaLabXMLService;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

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

        String[] params={"1","descr","7","4"};
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
        String[] params={"","descr","7","4"};
        try{
            temaLabXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params2={null,"descr","7","4"};
        try{
            temaLabXMLService.add(params2);
        }catch (ValidatorException | NullPointerException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);
    }
}

