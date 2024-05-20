package mapper;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
//import org.mapstruct.factory.Mappers;


@Mapper
public interface PostMapper {
    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "category.id", target = "categoryId")
    PostDto mapToDTO(Post post);


    Post mapToEntity(PostDto postDto);
}
