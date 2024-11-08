package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ImagenDTO;
import com.ntm.appseguridad.entities.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenMapper {
    ImagenMapper INSTANCE = Mappers.getMapper(ImagenMapper.class);

    ImagenDTO toDTO(Imagen imagen);

    Imagen toEntity(ImagenDTO imagenDTO);

    List<ImagenDTO> toDtoList(List<Imagen> entities);
}
