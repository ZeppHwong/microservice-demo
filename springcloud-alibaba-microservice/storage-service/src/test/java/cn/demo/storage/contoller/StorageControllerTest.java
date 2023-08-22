package cn.demo.storage.contoller;

import cn.demo.storage.controller.StorageController;
import cn.demo.storage.infra.mapper.StorageMapper;
import cn.demo.storage.infra.valueobject.Storage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StorageController.class)
@AutoConfigureMybatis
public class StorageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StorageMapper storageMapper;
    @Test
    @DisplayName("测试Controller删减库存")
    public void testStorageController() throws Exception {
        Storage storage = new Storage();
        storage.setCommodityCode("product-1");
        storage.setCount(1);
        Mockito.when(storageMapper.reduceStorage(storage)).thenReturn(1);
        mockMvc.perform(
                        get("/reduce/storage")
                                .param("commodityCode", "product-1")
                                .param("count", "1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count",is(1)));
    }
}
