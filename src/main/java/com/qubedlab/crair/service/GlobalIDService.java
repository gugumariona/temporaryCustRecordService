package com.qubedlab.crair.service;

import java.util.Map;

public interface GlobalIDService {

    public String lastNameReverse(String lastName);

    public String globalIDGenerator(Map<String, Object> data);

}
