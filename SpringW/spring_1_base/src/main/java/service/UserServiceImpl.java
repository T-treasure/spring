package service;

import dao.UserDAO;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAO();

    @Override
    public void addUSer() {
        userDAO.addUser();
    }
}
