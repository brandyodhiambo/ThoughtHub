package com.brandyodhiambo.ThoughtHub.service;

import com.brandyodhiambo.ThoughtHub.model.User;
import com.brandyodhiambo.ThoughtHub.payload.*;
import com.brandyodhiambo.ThoughtHub.payload.request.InfoRequest;
import com.brandyodhiambo.ThoughtHub.payload.response.ApiResponse;
import com.brandyodhiambo.ThoughtHub.service.impl.UserPrincipal;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);

	UserIdentityAvailability checkUsernameAvailability(String username);

	UserIdentityAvailability checkEmailAvailability(String email);

	UserProfile getUserProfile(String username);

	User addUser(User user);

	User updateUser(User newUser, String username, UserPrincipal currentUser);

	ApiResponse deleteUser(String username, UserPrincipal currentUser);

	ApiResponse giveAdmin(String username);

	ApiResponse removeAdmin(String username);

	UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);

}