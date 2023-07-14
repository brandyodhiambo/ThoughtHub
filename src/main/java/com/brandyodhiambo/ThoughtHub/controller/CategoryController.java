package com.brandyodhiambo.ThoughtHub.controller;

import com.brandyodhiambo.ThoughtHub.exception.UnauthorizedException;
import com.brandyodhiambo.ThoughtHub.model.Category;
import com.brandyodhiambo.ThoughtHub.payload.response.ApiResponse;
import com.brandyodhiambo.ThoughtHub.payload.response.PagedResponse;
import com.brandyodhiambo.ThoughtHub.security.CurrentUser;
import com.brandyodhiambo.ThoughtHub.service.impl.UserPrincipal;
import com.brandyodhiambo.ThoughtHub.service.CategoryService;
import com.brandyodhiambo.ThoughtHub.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public PagedResponse<Category> getAllCategories(
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
		return categoryService.getAllCategories(page, size);
	}

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category,
			@CurrentUser UserPrincipal currentUser) {

		return categoryService.addCategory(category, currentUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable(name = "id") Long id) {
		return categoryService.getCategory(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Category> updateCategory(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Category category, @CurrentUser UserPrincipal currentUser) throws UnauthorizedException {
		return categoryService.updateCategory(id, category, currentUser);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(name = "id") Long id,
			@CurrentUser UserPrincipal currentUser) throws UnauthorizedException {
		return categoryService.deleteCategory(id, currentUser);
	}

}
