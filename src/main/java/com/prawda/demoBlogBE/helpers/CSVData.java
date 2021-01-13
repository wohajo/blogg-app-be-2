package com.prawda.demoBlogBE.helpers;

import com.prawda.demoBlogBE.domain.Post;
import com.prawda.demoBlogBE.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CSVData {
    private List<Post> postList;
    private List<User> userList;
}
