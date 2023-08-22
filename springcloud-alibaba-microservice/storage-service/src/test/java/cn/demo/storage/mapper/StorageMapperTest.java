package cn.demo.storage.mapper;

import cn.demo.storage.infra.mapper.StorageMapper;
import cn.demo.storage.infra.valueobject.Storage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StorageMapperTest {

    @Autowired
    StorageMapper storageMapper;

    @Test
    @DisplayName("删减仓库测试")
    public void testReduceStorage(){
        Storage storage = new Storage();
        storage.setCommodityCode("product-1");
        storage.setCount(1);

        int result = storageMapper.reduceStorage(storage);
        assertEquals(1, result);
        assertEquals(98, storage.getCount());
    }
}
