package com.springboot.classes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class SynchronisationRequest {
	private List<Synchronisation> synchronisations;

    public List<Synchronisation> getSynchronisations() {
        return synchronisations;
    }

    public void setSynchronisations(List<Synchronisation> synchronisations) {
        this.synchronisations = synchronisations;
    }
}
