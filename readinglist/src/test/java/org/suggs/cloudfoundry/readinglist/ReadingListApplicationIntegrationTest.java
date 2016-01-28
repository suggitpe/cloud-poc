package org.suggs.cloudfoundry.readinglist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReadingListApplication.class)
@WebAppConfiguration
public class ReadingListApplicationIntegrationTest {

    private static final String BOOK_AUTHOR = "BOOK_AUTHOR";
    private static final String BOOK_TITLE = "BOOK_TITLE";
    public static final String BOOK_ISBN = "1234567";
    public static final String BOOK_DESCRIPTION = "BOOK_DESCRIPTION";

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void onSetup() {
        mockMvc = webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void loadsApplicationWithNoFailures() {
    }

    @Test
    public void loadsTheCorrectHomePageAndAllKeyAttributesArePresent() throws Exception {
        mockMvc.perform(get("/readingList"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    public void addingABook() throws Exception {
        mockMvc.perform(post("/readingList")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", BOOK_TITLE)
                .param("author", BOOK_AUTHOR)
                .param("isbn", BOOK_ISBN)
                .param("description", BOOK_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/readingList"));

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("readingList");
        expectedBook.setTitle(BOOK_TITLE);
        expectedBook.setAuthor(BOOK_AUTHOR);
        expectedBook.setIsbn(BOOK_ISBN);
        expectedBook.setDescription(BOOK_DESCRIPTION);

        mockMvc.perform(get("/readingList"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", not(empty())))
                .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
    }

}