package de.hfu;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository{
    private List<Resident> repos = new ArrayList<Resident>();
    @Override
    public List<Resident> getResidents() {

        return this.repos;
    }
    public void addResident(Resident r) {
        repos.add(r);
    }
    public void removeResident(Resident r) {
        repos.remove(r);
    }

}