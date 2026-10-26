package com.example.roomwordssample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.roomwordssample.data.Word;
import com.example.roomwordssample.data.WordDao;
import com.example.roomwordssample.data.WordRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class WordDaoTest {

    private WordRoomDatabase database;
    private WordDao wordDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();

        database = Room.inMemoryDatabaseBuilder(
                context,
                WordRoomDatabase.class
        ).allowMainThreadQueries().build();

        wordDao = database.wordDao();
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void insertAndReadWord() {
        wordDao.insert(new Word("Bonjour"));

        List<Word> words = wordDao.getAllWordsForTest();

        assertEquals(1, words.size());
        assertEquals("Bonjour", words.get(0).getWord());
    }

    @Test
    public void deleteAllWords() {
        wordDao.insert(new Word("Bonjour"));
        wordDao.insert(new Word("Android"));

        wordDao.deleteAll();

        List<Word> words = wordDao.getAllWordsForTest();

        assertTrue(words.isEmpty());
    }

    @Test
    public void wordsAreAlphabetized() {
        wordDao.insert(new Word("Zebra"));
        wordDao.insert(new Word("Apple"));

        List<Word> words = wordDao.getAllWordsForTest();

        assertEquals(2, words.size());
        assertEquals("Apple", words.get(0).getWord());
        assertEquals("Zebra", words.get(1).getWord());
    }
}