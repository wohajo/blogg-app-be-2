package com.prawda.demoBlogBE.converters;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.user.User;
import com.prawda.demoBlogBE.helpers.CSVData;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CSVConverter {
    private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/OnePosts.csv";

    public CSVData parse() throws IOException {

        List<User> usersToAdd = new ArrayList<>();
        List<Post> postsToAdd = new ArrayList<>();
        CSVData csvData = new CSVData(new ArrayList<>(), new ArrayList<>());

        User admin = new User(
                null,
                "Admin",
                "Admin",
                DigestUtils.sha512Hex("Admin"),
                "Admin@admin.com",
                true);

        usersToAdd.add(admin);

        try (
                CSVReader csvReader = new CSVReaderBuilder(
                        new FileReader(SAMPLE_CSV_FILE_PATH))
                        .withSkipLines(1).build();
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {

                User newUser;
                Post newPost;

                System.out.println("id : " + nextRecord[0]);
                System.out.println("Author : " + nextRecord[1]);
                System.out.println("Contents : " + nextRecord[2].length());
                String tempAuthor = nextRecord[1];
                String tempAuthorNoSpace = nextRecord[1].replace(" ", "");

                User FoundUser = usersToAdd
                        .stream()
                        .filter(user -> user.getName()
                                .equals(tempAuthor))
                        .findFirst()
                        .orElse(null);

                if (FoundUser == null) {
                    newUser = new User(
                            null,
                            tempAuthor,
                            tempAuthorNoSpace,
                            DigestUtils.sha512Hex(tempAuthorNoSpace + "!"),
                            tempAuthorNoSpace + "@xd.pl",
                            false);


                    newPost = new Post(
                            Long.parseLong(nextRecord[0]),
                            nextRecord[2],
                            newUser);

                    usersToAdd.add(newUser);
                } else {
                    newPost = new Post(
                            Long.parseLong(nextRecord[0]),
                            nextRecord[2],
                            FoundUser);
                }

                postsToAdd.add(newPost);

                csvData.setPostList(postsToAdd);
                csvData.setUserList(usersToAdd);
                System.out.println("==========================");
            }

        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return csvData;
    }
}
