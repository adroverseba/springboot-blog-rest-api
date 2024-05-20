package mapper;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryDto mapToDto(Category category);

    Category mapToEntity(CategoryDto categoryDto);
}
