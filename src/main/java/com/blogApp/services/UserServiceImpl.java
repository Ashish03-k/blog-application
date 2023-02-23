package com.blogApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.entities.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payloads.UserDto;
import com.blogApp.repositiories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userrepo;

	@Override
	public UserDto createUser(UserDto userdto) {
		User user = this.userDtoToUser(userdto);
		User savedUser = this.userrepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user = this.userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());

		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(user.getPassword());
		user.setAbout(userdto.getAbout());

		User updatedUser = this.userrepo.save(user);
		UserDto userdto1 = this.userToUserDto(updatedUser);

		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userrepo.findAll();
		List<UserDto> userdto = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return userdto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		this.userrepo.delete(user);
	}

	public User userDtoToUser(UserDto userdto) {

		User user = new User();

		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());

		return user;
	}

	public UserDto userToUserDto(User user) {
		UserDto userdto = new UserDto();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setEmail(user.getEmail());
		userdto.setAbout(user.getAbout());
		userdto.setPassword(user.getPassword());
		return userdto;
	}
}
