package mapper;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);
    CommentDto mapToDTO(Comment comment);

    Comment mapToEntity(CommentDto commentDto);
}
