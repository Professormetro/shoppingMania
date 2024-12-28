package org.chernov.catalogservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketService {

    private static final String BUCKET_KEY = "bucket";
    private final RedisTemplate<String, List<Long>> redisTemplate;


    public void addProductToBucket(Long productId){

        List<Long> bucketItems = redisTemplate.opsForValue().get(BUCKET_KEY);
        if(bucketItems == null){
            bucketItems = new ArrayList<>();
        }
        bucketItems.add(productId);

        redisTemplate.opsForValue().set(BUCKET_KEY, bucketItems);
    }


    public List<Long> getBucketItems(){
        List<Long> bucketItems = redisTemplate.opsForValue().get(BUCKET_KEY);
        return bucketItems != null ? bucketItems : new ArrayList<>();
    }

    public void removeProductFromBucket(Long productId){
        List<Long> bucketItems = getBucketItems();
        if(bucketItems != null && bucketItems.contains(productId)){
            bucketItems.remove(productId);
            redisTemplate.opsForValue().set(BUCKET_KEY, bucketItems);
        }
    }

    public void clearBucket(){
        redisTemplate.delete(BUCKET_KEY);
    }

}
