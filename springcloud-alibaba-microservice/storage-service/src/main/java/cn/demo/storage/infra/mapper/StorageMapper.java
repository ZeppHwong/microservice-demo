package cn.demo.storage.infra.mapper;

import cn.demo.storage.infra.valueobject.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StorageMapper {
    int reduceStorage(Storage storage);
}
