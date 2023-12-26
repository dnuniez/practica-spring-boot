package com.inditex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

	@Autowired
    private WebApplicationContext webApplicationContext;
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;
    
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testEnrichAndSaveAlbums() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/enrichAndSaveAlbums"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verifica que el método enrichAndSaveAlbums de albumService se haya llamado
        verify(albumService, times(1)).enrichAndSaveAlbums();
    }

    @Test
    void testEnrichAndGetAlbums() throws Exception {
    	List<Album> mockAlbums = new ArrayList<>();
        mockAlbums.add(new Album(1L, 1L, "quidem molestiae enim"));
        when(albumService.enrichAndGetAlbums()).thenReturn(mockAlbums);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/enrichAndGetAlbums"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verifica que el método enrichAndGetAlbums de albumService se haya llamado
        verify(albumService, times(1)).enrichAndGetAlbums();
    }

    @Test
    void testGetAlbums() throws Exception {
    	List<Album> mockAlbums = new ArrayList<>();
        mockAlbums.add(new Album(1L, 1L, "quidem molestiae enim"));
        when(albumService.getAlbums()).thenReturn(mockAlbums);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/getAlbums"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verifica que el método getAlbums de albumService se haya llamado
        verify(albumService, times(1)).getAlbums();
    }
}