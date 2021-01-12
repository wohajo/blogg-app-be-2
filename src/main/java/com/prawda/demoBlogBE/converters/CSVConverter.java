package com.prawda.demoBlogBE.converters;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.prawda.demoBlogBE.domain.Post;
import com.prawda.demoBlogBE.domain.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVConverter {
    private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/OnePosts.csv";

    public static void main(String[] args) throws IOException {

        List<User> usersToAdd = new ArrayList<>();
        List<Post> postsToAdd = new ArrayList<>();

        User admin = new User(
                1L,
                "AdminF",
                "AdminS",
                "Admin",
                "Admin",
                "Admin@this.com",
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
                String[] splitName = nextRecord[1].split(" ");

                User FoundUser = usersToAdd
                        .stream()
                        .filter(user -> user.getFirstName().equals(splitName[0]) && user.getLastName().equals(splitName[1]))
                        .findFirst()
                        .orElse(null);

                if (FoundUser == null) {
                    newUser = new User(
                            (long) usersToAdd.size(),
                            splitName[0],
                            splitName[1],
                            splitName[0] + splitName[1],
                            splitName[0] + splitName[1],
                            splitName[0] + splitName[1] + "@this.com",
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

                System.out.println("==========================");
            }

        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
