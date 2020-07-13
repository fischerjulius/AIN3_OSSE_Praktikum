package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResidentRepositoryTest {

    Resident r1 = new Resident("Jeff", "Jones", "Streetystreet", "somecity", new Date(1111-11-11));
    Resident r2 = new Resident("John", "Jankins", "Coolerstreet", "anothercity", new Date(1211-11-11));
    Resident r3 = new Resident("theolderJeff", "Jones", "Streetystreet", "somecity", new Date(1000-11-11));
    Resident r4 = new Resident("Hmmmm", "Doesnt", "make", "sense", new Date(2000-3-7));
    ResidentRepositoryStub repos = new ResidentRepositoryStub();
    BaseResidentService service = new BaseResidentService();

    @Test
    public void getFilteredResidentListTest1() {
        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        Resident search = new Resident("*", "*", "*", "somecity",null);
        List<Resident> s = service.getFilteredResidentsList(search);
        for(Resident resident : s) {
            if(resident.equals(r1)) {
                assertEquals(r1,resident);
            }
            else if(resident.equals(r3)) {
                assertEquals(r3,resident);
            }
            else {
                assertEquals(null, resident);
            }

        }
        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);
        //-> failed, kein city-Filter

    }
    @Test
    public void getFilteredResidentListTest2() {
        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        Resident search = new Resident("*J*", "*", "*", "*",null);
        List<Resident> s = service.getFilteredResidentsList(search);
        for(Resident resident : s) {
            if(resident.equals(r1)) {
                assertEquals(r1,resident);
            }
            else if(resident.equals(r2)) {
                assertEquals(r2,resident);
            }
            else if(resident.equals(r3)) {
                assertEquals(r3,resident);
            }
            else {
                assertEquals(null, 1);
            }

        }
        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);

    }
    @Test
    public void getFilteredResidentListTest3() {
        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        Resident search = new Resident("*Jeff*", "*", "Streetystreet", "*",null);
        List<Resident> s = service.getFilteredResidentsList(search);
        for(Resident resident : s) {
            if(resident.equals(r1)) {
                assertEquals(r1,resident);
            }
            else if(resident.equals(r3)) {
                assertEquals(r3,resident);
            }
            else {
                assertEquals(null,resident);
            }

        }
        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);

    }
    @Test
    public void getUniqueResidentTest1() throws ResidentServiceException {
        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        assertEquals(r1,  service.getUniqueResident(r1));


        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);
    }
    @Test
    public void getUniqueResidentTest2() throws ResidentServiceException {
        Resident test = new Resident();
        test.setStreet("Streetystreet");
        //test.setDateOfBirth(new Date(1000-11-11));
        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        assertEquals(r3, service.getUniqueResident(test));
        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);
        // -> failed, da  keine Birth of Date check
    }
    @Test
    public void getUniqueResidentTest3() throws ResidentServiceException {
        Resident test = new Resident();
        test.setGivenName("Jeff");
        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        assertEquals(r1, service.getUniqueResident(test));
        assertEquals(r3, service.getUniqueResident(r3));
        assertEquals(r4, service.getUniqueResident(r4));
        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);
    }
    @Test
    public void getFilteredResidentListTestX() {

        repos.addResident(r1);
        repos.addResident(r2);
        repos.addResident(r3);
        repos.addResident(r4);
        service.setResidentRepository(repos);
        Resident search = new Resident("AC", "*", "*", "*",null);
        List<Resident> s = service.getFilteredResidentsList(search);
        if(s.size()>0) {
            for(Resident resident : s) {
                if(resident.equals(r1)) {
                    assertEquals(r1,resident);
                }
                else if(resident.equals(r2)) {
                    assertEquals(r2,resident);
                }
                else if(resident.equals(r3)) {
                    assertEquals(r3,resident);
                }
                else {
                    assertEquals(null, 1);
                }
            }
        }
        else {
            assertEquals(null, 1);
        }
        repos.removeResident(r1);
        repos.removeResident(r2);
        repos.removeResident(r3);
        repos.removeResident(r4);

    }
}
