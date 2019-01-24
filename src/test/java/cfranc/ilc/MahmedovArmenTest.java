/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfranc.ilc;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author am155083
 */
public class MahmedovArmenTest {

    String[] when2SimpleWords = new String[]{"parapluie", "parachute"};

    public MahmedovArmenTest() {
    }

    @Test
    public void getSimilarity_2SimpleWords_33() {
        MarkovWord m = new MarkovWord();
        double expected = 0.33;
        double actual = m.getSimilarity(when2SimpleWords[0], when2SimpleWords[1], 2); // couple de deux caracteres "para", "para"
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void getSimilarity_SameWord_100() {
        MarkovWord m = new MarkovWord();
        double expected = 1.0;
        double actual = m.getSimilarity(when2SimpleWords[0], when2SimpleWords[0], 4);
        assertEquals(expected, actual, 0.000000001);
    }

    @Test
    public void Common_3SameWord_3() {
        MarkovWord m = new MarkovWord();
        MarkovData m1 = new MarkovData("Test", 4);
        MarkovData m2 = new MarkovData("Test", 4);
        MarkovData m3 = new MarkovData("Bonjour", 6);
        MarkovData m4 = new MarkovData("Bonjour", 6);
        MarkovData m5 = new MarkovData("Java", 4);
        MarkovData m6 = new MarkovData("Java", 4);
        MarkovData m7 = new MarkovData("Marko", 5);
        MarkovData m8 = new MarkovData("Junit", 5);

        List<MarkovData> One = new ArrayList<MarkovData>();
        List<MarkovData> Two = new ArrayList<MarkovData>();
        One.add(m1);
        One.add(m3);
        One.add(m5);
        One.add(m7);

        Two.add(m2);
        Two.add(m4);
        Two.add(m6);
        Two.add(m8);
        int expected = 3;
        int actual = m.common(One, Two);
        assertEquals(expected, actual);
    }

    @Test
    public void Common_0SameWords_0() {
        //Compare 2 Listes de mots, retourne le nombre de mots identiques dans chaque liste
        MarkovWord m = new MarkovWord();
        MarkovData m1 = new MarkovData("Bonjour", 6);
        MarkovData m2 = new MarkovData("Test", 4);
        MarkovData m5 = new MarkovData("Marko", 5);
        MarkovData m6 = new MarkovData("Junit", 5);
        List<MarkovData> One = new ArrayList<MarkovData>();
        List<MarkovData> Two = new ArrayList<MarkovData>();
        One.add(m1);
        One.add(m5);
        Two.add(m2);
        Two.add(m6);

        int expected = 0;
        int actual = m.common(One, Two);
        assertEquals(expected, actual);
    }

    @Test
    public void Common_SameWordWithDifferentCase_1() {
        MarkovWord m = new MarkovWord();
        MarkovData m1 = new MarkovData("Bonjour", 2);
        MarkovData m2 = new MarkovData("bonjour", 2);
        List<MarkovData> One = new ArrayList<MarkovData>();
        List<MarkovData> Two = new ArrayList<MarkovData>();
        One.add(m1);
        Two.add(m2);

        int expected = 1;
        int actual = m.common(One, Two);
        assertEquals(expected, actual);
    }

    @Test
    public void union_2SameWords_4() {
        MarkovWord m = new MarkovWord();
        MarkovData m1 = new MarkovData("Bonjour", 2);
        MarkovData m2 = new MarkovData("Bonjour", 2);
        MarkovData m3 = new MarkovData("Test", 2);
        MarkovData m4 = new MarkovData("Test", 2);
        MarkovData m5 = new MarkovData("Marko", 2);
        MarkovData m6 = new MarkovData("Markov", 2);
        List<MarkovData> One = new ArrayList<MarkovData>();
        List<MarkovData> Two = new ArrayList<MarkovData>();
        One.add(m1);
        One.add(m3);
        One.add(m5);
        
        Two.add(m2);
        Two.add(m4);        
        Two.add(m6);

        int expected = 4;
        int actual = m.union(One, Two);
        assertEquals(expected, actual);
    }

    
    @Test
    public void union_0SameWords_4() {
        MarkovWord m = new MarkovWord();
        MarkovData m1 = new MarkovData("Bonjour", 2);
        MarkovData m2 = new MarkovData("Test", 2);
        MarkovData m3 = new MarkovData("Marko", 2);
        MarkovData m4 = new MarkovData("Markov", 2);
        List<MarkovData> One = new ArrayList<MarkovData>();
        List<MarkovData> Two = new ArrayList<MarkovData>();
        One.add(m1);
        One.add(m2);
        
        Two.add(m3);
        Two.add(m4);        

        int expected = 4;
        int actual = m.union(One, Two);
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void union_2SameWords_1() {
        MarkovWord m = new MarkovWord();
        MarkovData m1 = new MarkovData("Bonjour", 2);
        MarkovData m2 = new MarkovData("Bonjour", 2);
        
        List<MarkovData> One = new ArrayList<MarkovData>();
        List<MarkovData> Two = new ArrayList<MarkovData>();
        One.add(m1);
        Two.add(m2);
        
        int expected = 1;
        int actual = m.union(One, Two);
        assertEquals(expected, actual);
    }
    
    @Test
    public void contains_containLetters_1() {
        MarkovWord m = new MarkovWord("parapluie", 2);
        int expected = 1;
        int actual = m.contains("pa");
        assertEquals(expected, actual);	
    }
    
    @Test
    public void contains_notContainLetters_0() {
        MarkovWord m = new MarkovWord("parapluie", 2);
        int expected = 0;
        int actual = m.contains("at");
        assertEquals(expected, actual);	
    }
    
    @Test
    public void processing_containWord_equals() {
        MarkovWord m = new MarkovWord();
        List<MarkovData> expected = new ArrayList<MarkovData>();
        expected.add(new MarkovData("%p",1));
        expected.add(new MarkovData("pb",1));
        expected.add(new MarkovData("b%",1));
        List<MarkovData> actual = m.processString("pb", 2);
        assertEquals(expected, actual);
    }
    
    @Test
    public void processing_NotContainWord_notequals() {
        MarkovWord m = new MarkovWord();
        List<MarkovData> expected = new ArrayList<MarkovData>();
        expected.add(new MarkovData("%p",1));
        expected.add(new MarkovData("pb",1));
        expected.add(new MarkovData("b%",1));
        List<MarkovData> actual = m.processString("te", 2);
        assertNotEquals(expected, actual);
    }
    
}
