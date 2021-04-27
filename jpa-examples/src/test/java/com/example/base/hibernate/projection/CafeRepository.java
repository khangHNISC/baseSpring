package com.example.base.hibernate.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    /**
     * https://www.bytestree.com/spring/spring-data-jpa-projections-5-ways-return-custom-object/
     */
    @Query(value = "SELECT c FROM CAFE c")
    List<MyCafe> findEveryThingInterfaceProjection();

    @Query(value = "SELECT * FROM cafe", nativeQuery = true)
    List<MyCafe> findEveryThingNativeQueryInterfaceProjection();

    @Query(value = "SELECT new " +
            "com.example.base.hibernate.projection.MyCafeDTO(c.id, c.name)" +
            " FROM CAFE c")
    List<MyCafeDTO> findEveryThingClassProjection();

    /*
     * jpa will get this from @NameNativeQuery
     * remember to name it with class name prefix.
     */
    @Query(nativeQuery = true)
    List<MyCafeDTO> getCafes();
}
