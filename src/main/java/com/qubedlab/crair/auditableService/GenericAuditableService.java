package com.qubedlab.crair.auditableService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericAuditableService<T, ID> {

    /**
     * Performs the Create, Update operations on the object
     *
     * @param obj the object of type T

     * @return an object of type T
     * @throws java.lang.Exception
     */

    /**
     * @param wrapper
     * @return
     * @throws java.lang.Exception
     */

    /**
     * @param pageable
     * @return
     * @throws java.lang.Exception

   */

    /**
     *Finds and returns an object for the specified ID.
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    T findById(String id) throws Exception;
}

