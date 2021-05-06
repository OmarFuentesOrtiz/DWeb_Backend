package com.neoadventura.repositories;

import com.neoadventura.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findRegionsByPais(Long id);

    //@Query("SELECT s FROM Region s WHERE s.pais=?1")
    //List<Region> findByPaisId(Long id);
    /*
    @Query(value="select * from regions r where r.pais_id= :id:", nativeQuery = true)
    List<Region> findByPaisId(
            @Param("id") Long id
    );*/
}
