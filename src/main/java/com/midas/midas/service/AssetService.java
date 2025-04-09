package com.midas.midas.service;

import com.midas.midas.model.Asset;
import com.midas.midas.model.User;
import com.midas.midas.repository.AssetRepository;
import com.midas.midas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssetRepository assetRepository;

    public Asset addAsset(Long userId, String name, BigDecimal value) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        Asset asset = new Asset();

        asset.setName(name);
        asset.setValue(value);
        asset.setUser(user);

        return assetRepository.save(asset);
    }
}
