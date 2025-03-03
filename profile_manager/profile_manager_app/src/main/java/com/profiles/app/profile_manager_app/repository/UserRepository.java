package com.profiles.app.profile_manager_app.repository;

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.models.DatosUsuario;

@Repository
public interface UserRepository extends JpaRepository<DatosUsuario, Long> {

    Optional<DatosUsuario> findByEmail(String email);

    @Query(value = "SELECT * FROM usuarios WHERE ST_DWithin(posicion, ST_GeomFromText(:posString, 4326), :distancia)", nativeQuery = true)
    Optional<List<DatosUsuario>> findByPosicion(@Param("posString") String posString,
            @Param("distancia") int distancia);

    Optional<DatosUsuario> findByIdUsuario(String userId);

    

}