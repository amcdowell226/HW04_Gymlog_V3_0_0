package com.example.hw04_gymlog_v300;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.hw04_gymlog_v300.database.GymLogDatabase;
import com.example.hw04_gymlog_v300.database.UserDAO;
import com.example.hw04_gymlog_v300.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DBUnitTest {
    private UserDAO userDao;
    private GymLogDatabase db;
    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GymLogDatabase.class).build();
        userDao = db.userDAO();
    }
    @After
    public void closeDb() throws IOException {
        db.close();
    }
    @Test
    public void writeUserAndReadInList() {
        String username = "testuser1";
        String password = "password";
        User user = new User(username, password);
        userDao.insert(user);
        List<User> users = userDao.getAllUsersList();
        assertNotNull(users.get(0));
        assertEquals(username, users.get(0).getUsername());
    }
}
