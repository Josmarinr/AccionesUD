package com.AccionesUD.AccionesUD.application.profile;

import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.domain.service.profile.UserProfileService;
import com.AccionesUD.AccionesUD.dto.profile.UpdateUserProfileRequest;

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