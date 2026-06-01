package main;

import repository.*;
import service.*;

import view.LoginView;
import model.User;

public class Main {
	public static void main(String[] args) {
		User defaultUser = new User(1001, "Default", "User", "12345", "user@user.com", "123", true, "Administrator");
		UserRepository userRepository = UserRepository.getInstance();
		userRepository.addUser(defaultUser);

		LoginView loginView = new LoginView();
		loginView.showWindow();
	}
}
