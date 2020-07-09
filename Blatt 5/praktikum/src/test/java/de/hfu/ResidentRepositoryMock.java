package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class ResidentRepositoryMock {

    private ResidentRepository residentRepositoryMock;
    private BaseResidentService baseResidentService = new BaseResidentService();

    private Resident r1;
    private Resident r2;
    private Resident r3;
    private Resident r4;


    public ResidentRepositoryMock() {
        this.r1 = new Resident("Jeff", "Jones", "Streetystreet", "somecity", new Date(1111-11-11));
        this.r2 = new Resident("John", "Jankins", "Coolerstreet", "anothercity", new Date(1211-11-11));
        this.r3 = new Resident("theolderJeff", "Jones", "Streetystreet", "somecity", new Date(1000-11-11));
        this.r4 = new Resident("Hmmmm", "Doesnt", "make", "sense", new Date(2000-3-7));
        List<Resident> residents = Arrays.asList(this.r1, this.r2, this.r3, this.r4);
        this.residentRepositoryMock = createMock(ResidentRepository.class);
        expect(residentRepositoryMock.getResidents()).andReturn(residents);
        replay(residentRepositoryMock);
        baseResidentService.setResidentRepository(residentRepositoryMock);
    }


    @Test(expected = ResidentServiceException.class)
    public void testGetUniqueResidentWithWildcard() throws Exception {
        Resident resident = new Resident("Te*", "", "", "", new Date());
        baseResidentService.getUniqueResident(resident);
        verify(residentRepositoryMock);
    }


    @Test(expected = ResidentServiceException.class)
    public void testGetUniqueResidentNoResult() throws Exception {
        Resident resident = new Resident("Te", "", "", "", new Date());
        baseResidentService.getUniqueResident(resident);
        verify(residentRepositoryMock);
    }



    @Test
    public void testGetUniqueResident() throws Exception {
        Resident resident = new Resident("theolderJeff", "Jones", "Streetystreet", "somecity", new Date(1000-11-11));
        Assert.assertThat(resident.getFamilyName(), equalTo(baseResidentService.getUniqueResident(resident).getFamilyName()));
        verify(residentRepositoryMock);
    }

    @Test
    public void testGetFilteredResidentsListWithWildcard() throws Exception {
        Resident resident = new Resident("J*", "", "", "", null);
        Assert.assertThat(Arrays.asList(r1, r2), equalTo(baseResidentService.getFilteredResidentsList(resident)));
        verify(residentRepositoryMock);
    }

    @Test
    public void testGetFilteredResidentsList() throws Exception {
        Resident resident = new Resident("", "Jankins", "", "", null);
        Assert.assertThat(Arrays.asList(r2), equalTo(baseResidentService.getFilteredResidentsList(resident)));
        verify(residentRepositoryMock);
    }


    @Test
    public void testGetFilteredResidentsListNoResult() throws Exception {
        Resident resident = new Resident("noname", "", "", "", null);
        Assert.assertThat(Arrays.asList(), equalTo(baseResidentService.getFilteredResidentsList(resident)));
        verify(residentRepositoryMock);
    }
}