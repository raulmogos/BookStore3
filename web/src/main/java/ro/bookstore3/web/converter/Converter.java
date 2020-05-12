package ro.bookstore3.web.converter;

import ro.bookstore3.models.BaseEntity;
import ro.bookstore3.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

