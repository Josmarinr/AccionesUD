package com.AccionesUD.AccionesUD.gestionusuario.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AccionesUD.AccionesUD.gestionusuario.dto.UpdateUserProfileRequest;
import com.AccionesUD.AccionesUD.usuario.entity.User;
import com.AccionesUD.AccionesUD.authentication.repository.UserRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void updateProfile(UpdateUserProfileRequest request) {
        User user = userRepository.findById(request.getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con cédula: " + request.getId()));

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setAddress(request.getAddress());
        user.setOtpEnabled(request.isOtpEnabled());
        user.setDailyOrderLimit(request.getDailyOrderLimit());

        userRepository.save(user);
    }
}
