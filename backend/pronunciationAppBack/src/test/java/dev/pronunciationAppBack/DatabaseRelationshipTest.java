package dev.pronunciationAppBack;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.model.Stage.Status;
import dev.pronunciationAppBack.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseRelationshipTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private WordRepository wordRepository;

    @BeforeEach
    public void setUp() {
        // Clear the database before each test
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        levelRepository.deleteAll();
        stageRepository.deleteAll();
        wordRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testRelationships() {
        // Create sample data
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@test.com");

        Category category = new Category();
        category.setCategoryName("Test Category");
        category.setDescription("Test Description");
        category.setSubCategoryName("Test Subcategory");

        Level level = new Level();
        level.setName("Test Level");
        level.setCategory(category);

        Stage stage = new Stage();
        stage.setName("Test Stage");
        stage.setLevel(level);
        stage.setAvatarUrl("Test Avatar URL");
        stage.setStatus(Status.COMPLETED);

        Word word = new Word();
        word.setText("Test Word");
        word.setStage(stage);
        word.setDescription("Test Description");
        word.setSentence("Test Sentence");

        // Save data
        userRepository.save(user);
        categoryRepository.save(category);
        levelRepository.save(level);
        stageRepository.save(stage);
        wordRepository.save(word);

        // Verify relationships
        List<Level> levels = levelRepository.findAll();
        assertEquals(1, levels.size());
        assertEquals("Test Category", levels.get(0).getCategory().getCategoryName());

        List<Stage> stages = stageRepository.findAll();
        assertEquals(1, stages.size());
        assertEquals("Test Level", stages.get(0).getLevel().getName());

        List<Word> words = wordRepository.findAll();
        assertEquals(1, words.size());
        assertEquals("Test Stage", words.get(0).getStage().getName());
    }

    @Test
    @Transactional
    public void testCascadeOperations() {
        // Create sample data
        Category category = new Category();
        category.setCategoryName("Cascade Category");
        category.setDescription("Test Description");
        category.setSubCategoryName("Test Subcategory");

        Level level = new Level();
        level.setName("Cascade Level");
        level.setCategory(category);
        category.setLevels(List.of(level));

        Stage stage = new Stage();
        stage.setName("Cascade Stage");
        stage.setLevel(level);
        stage.setAvatarUrl("Test Avatar URL");
        stage.setStatus(Status.COMPLETED);

        Word word = new Word();
        word.setText("Test Word");
        word.setStage(stage);
        word.setDescription("Test Description");
        word.setSentence("Test Sentence");

        // Save data
        categoryRepository.save(category);

        // Verify cascade operations
        List<Category> categories = categoryRepository.findAll();
        assertEquals(1, categories.size());
        assertEquals(1, categories.get(0).getLevels().size());
    }

    @Test
    @Transactional
    public void testFetchingStrategies() {
        // Create sample data
        Category category = new Category();
        category.setCategoryName("Fetch Category");
        category.setDescription("Test Description");
        category.setSubCategoryName("Test Subcategory");

        Level level = new Level();
        level.setName("Fetch Level");
        level.setCategory(category);

        Stage stage = new Stage();
        stage.setName("Fetch Stage");
        stage.setLevel(level);
        stage.setAvatarUrl("Test Avatar URL");
        stage.setStatus(Status.COMPLETED);

        Word word = new Word();
        word.setText("Test Word");
        word.setStage(stage);
        word.setDescription("Test Description");
        word.setSentence("Test Sentence");

        // Save data
        categoryRepository.save(category);

        // Verify fetching strategies
        Category fetchedCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertNotNull(fetchedCategory);
    }
}
