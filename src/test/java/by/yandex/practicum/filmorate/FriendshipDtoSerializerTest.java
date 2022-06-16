package by.yandex.practicum.filmorate;

import by.yandex.practicum.filmorate.models.Friendship;
import by.yandex.practicum.filmorate.models.User;
import by.yandex.practicum.filmorate.rest.converters.FriendshipToFriendshipDtoConverter;
import by.yandex.practicum.filmorate.rest.dto.FriendshipDto;
import by.yandex.practicum.filmorate.rest.serializers.FriendshipDtoSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FriendshipDtoSerializerTest {

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

        Friendship friendshipUser = new Friendship(3L, user, friend);
        Friendship friendshipFriend = new Friendship(4L, friend, user);

        List<Friendship> friendshipList = new ArrayList<>();
        friendshipList.add(friendshipUser);
        friendshipList.add(friendshipFriend);

        FriendshipDto friendshipDto = new FriendshipToFriendshipDtoConverter().convert(friendshipUser);

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        new FriendshipDtoSerializer().serialize(friendshipDto, jsonGenerator, serializerProvider);
        jsonGenerator.flush();
        System.out.println(jsonWriter);
    }
}