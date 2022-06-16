package by.yandex.practicum.filmorate;

import by.yandex.practicum.filmorate.models.Friendship;
import by.yandex.practicum.filmorate.models.User;
import by.yandex.practicum.filmorate.rest.converters.UserToUserDtoConverter;
import by.yandex.practicum.filmorate.rest.dto.UserDto;
import by.yandex.practicum.filmorate.rest.serializers.UserDtoSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

public class UserDtoSerializerTest {

    @Test
    public void serialize() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setEmail("email@example.com");
        user.setLogin("loginName");
        user.setName("userName");
        user.setBirthday(LocalDate.now());

        User friend = new User();
        friend.setId(2L);
        friend.setEmail("friend@example.com");
        friend.setLogin("friendLogin");
        friend.setName("friendName");
        friend.setBirthday(LocalDate.now());

        User friend2 = new User();
        friend2.setId(4L);
        friend2.setEmail("friend2@example.com");
        friend2.setLogin("friendLogin2");
        friend2.setName("friendName2");
        friend2.setBirthday(LocalDate.now());

        Friendship friendship = new Friendship(3L, user, friend);
        Friendship friendship2 = new Friendship(5L, user, friend2);
        user.addFriendship(friendship);
        user.addFriendship(friendship2);

        UserDto userDto = new UserToUserDtoConverter().convert(user);

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        new UserDtoSerializer().serialize(userDto, jsonGenerator, serializerProvider);
        jsonGenerator.flush();
        System.out.println(jsonWriter);
    }
}