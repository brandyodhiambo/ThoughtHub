package com.brandyodhiambo.ThoughtHub.service.impl;

import com.brandyodhiambo.ThoughtHub.exception.ResourceNotFoundException;
import com.brandyodhiambo.ThoughtHub.exception.UnauthorizedException;
import com.brandyodhiambo.ThoughtHub.model.Tag;
import com.brandyodhiambo.ThoughtHub.model.RoleName;
import com.brandyodhiambo.ThoughtHub.payload.response.ApiResponse;
import com.brandyodhiambo.ThoughtHub.payload.response.PagedResponse;
import com.brandyodhiambo.ThoughtHub.repository.TagRepository;
import com.brandyodhiambo.ThoughtHub.service.TagService;
import com.brandyodhiambo.ThoughtHub.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public PagedResponse<Tag> getAllTags(int page, int size) {
		AppUtils.validatePageNumberAndSize(page, size);

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

		Page<Tag> tags = tagRepository.findAll(pageable);

		List<Tag> content = tags.getNumberOfElements() == 0 ? Collections.emptyList() : tags.getContent();

		return new PagedResponse<>(content, tags.getNumber(), tags.getSize(), tags.getTotalElements(), tags.getTotalPages(), tags.isLast());
	}

	@Override
	public Tag getTag(Long id) {
		return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
	}

	@Override
	public Tag addTag(Tag tag, UserPrincipal currentUser) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag updateTag(Long id, Tag newTag, UserPrincipal currentUser) {
		Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
		if (tag.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			tag.setName(newTag.getName());
			return tagRepository.save(tag);
		}
		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to edit this tag");

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public ApiResponse deleteTag(Long id, UserPrincipal currentUser) {
		Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
		if (tag.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			tagRepository.deleteById(id);
			return new ApiResponse(Boolean.TRUE, "You successfully deleted tag");
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete this tag");

		throw new UnauthorizedException(apiResponse);
	}
}






















