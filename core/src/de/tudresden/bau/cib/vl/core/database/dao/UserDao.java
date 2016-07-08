package de.tudresden.bau.cib.vl.core.database.dao;

import java.util.List;

import de.tudresden.bau.cib.vl.core.database.domain.User;

public interface UserDao {

    public List<User> getUserList();
    public User getUser(Integer id);
    public Integer insertUser(User contact);
    public void updateUser(User contact);
    public void deleteUser(int userId);
	public User getUser(String login, String password);
	public boolean existUser(String login);
	public User getUserByUsername(String username);
}
