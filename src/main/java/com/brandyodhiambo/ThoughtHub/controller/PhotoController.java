package com.brandyodhiambo.ThoughtHub.controller;

import com.brandyodhiambo.ThoughtHub.payload.response.ApiResponse;
import com.brandyodhiambo.ThoughtHub.payload.response.PagedResponse;
import com.brandyodhiambo.ThoughtHub.payload.request.PhotoRequest;
import com.brandyodhiambo.ThoughtHub.payload.response.PhotoResponse;
import com.brandyodhiambo.ThoughtHub.security.CurrentUser;
import com.brandyodhiambo.ThoughtHub.service.impl.UserPrincipal;
import com.brandyodhiambo.ThoughtHub.service.PhotoService;
import com.brandyodhiambo.ThoughtHub.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
	@Autowired
	private PhotoService photoService;

	@GetMapping
	public PagedResponse<PhotoResponse> getAllPhotos(
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
		return photoService.getAllPhotos(page, size);
	}

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PhotoResponse> addPhoto(@Valid @RequestBody PhotoRequest photoRequest,
			@CurrentUser UserPrincipal currentUser) {
		PhotoResponse photoResponse = photoService.addPhoto(photoRequest, currentUser);

		return new ResponseEntity< >(photoResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PhotoResponse> getPhoto(@PathVariable(name = "id") Long id) {
		PhotoResponse photoResponse = photoService.getPhoto(id);

		return new ResponseEntity< >(photoResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<PhotoResponse> updatePhoto(@PathVariable(name = "id") Long id,
			@Valid @RequestBody PhotoRequest photoRequest, @CurrentUser UserPrincipal currentUser) {

		PhotoResponse photoResponse = photoService.updatePhoto(id, photoRequest, currentUser);

		return new ResponseEntity< >(photoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deletePhoto(@PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser) {
		ApiResponse apiResponse = photoService.deletePhoto(id, currentUser);

		return new ResponseEntity< >(apiResponse, HttpStatus.OK);
	}
}
