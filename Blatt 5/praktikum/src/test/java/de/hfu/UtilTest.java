package de.hfu;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

    @Test
    public void istErstesHalbjahr() {
        final int[] eingabe = {1,2,3,4,5,6,7,8,9,10,11,12};
        final boolean[] sollWert = {true,true,true,true,true,true,false,false,false,false,false,false};
        for(int i = 0; i < eingabe.length; i++) {
            assertEquals(sollWert[i],Util.istErstesHalbjahr(eingabe[i]));
        }
    }

}
