package com.AccionesUD.AccionesUD.authentication.application;

import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.domain.service.UserProfileService;
import com.AccionesUD.AccionesUD.authentication.dto.UpdateUserProfileRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileApplicationService {
    private final UserProfileService userProfileService;

public void updateUserProfile(UpdateUserProfileRequest request) {
    userProfileService.updateUser(request);
}

public UpdateUserProfileRequest getProfile(String username) {
    return userProfileService.getProfile(username);
}
}