package com.qubedlab.crair.repository;



import com.qubedlab.crair.models.TradeVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tradeInVehiclesRepository")
public interface TradeVehiclesRepository extends JpaRepository<TradeVehicles,String> {

    @Query("FROM TradeInVehicles WHERE trade_veh_vin = ?1")
    public TradeVehicles findByVin(String vin);

}
