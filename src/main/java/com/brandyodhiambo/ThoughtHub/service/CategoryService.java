package com.brandyodhiambo.ThoughtHub.service;

import com.brandyodhiambo.ThoughtHub.exception.UnauthorizedException;
import com.brandyodhiambo.ThoughtHub.model.Category;
import com.brandyodhiambo.ThoughtHub.payload.response.ApiResponse;
import com.brandyodhiambo.ThoughtHub.payload.response.PagedResponse;
import com.brandyodhiambo.ThoughtHub.service.impl.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

	PagedResponse<Category> getAllCategories(int page, int size);

	ResponseEntity<Category> getCategory(Long id);

	ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser);

	ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserPrincipal currentUser)
			throws UnauthorizedException;

	ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException;

}
