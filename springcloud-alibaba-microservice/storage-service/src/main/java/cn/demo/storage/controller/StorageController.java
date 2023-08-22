package cn.demo.storage.controller;

import cn.demo.storage.infra.mapper.StorageMapper;
import cn.demo.storage.infra.valueobject.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    StorageMapper storageMapper;

    @RequestMapping("reduce/storage")
    public Storage reduceStorage(@RequestParam("commodityCode") String commodityCode,
                                @RequestParam("count") int count) {
        Storage storage = new Storage();
        storage.setCommodityCode(commodityCode);
        storage.setCount(count);
        storageMapper.reduceStorage(storage);
        return storage;
    }
}
