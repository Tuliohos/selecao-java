package com.indra.gasstationserver.service;

import java.util.List;

import com.indra.gasstationserver.model.user.User;

public interface IUserService {

	public void create(User user);

	public void update(User user);

	public void delete(User user);

	public List<User> loadList();

}