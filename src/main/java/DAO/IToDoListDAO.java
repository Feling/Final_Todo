package main.java.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import main.java.table.*;

public interface IToDoListDAO {
	public void addUser(User user) throws SQLException, HibernateToDoListException;
	public void deleteUser(User user) throws SQLException, HibernateToDoListException;
	public boolean addItem(Item item) throws SQLException, HibernateToDoListException;
	public void getUsers() throws HibernateToDoListException;
	//public boolean deletItem(int i);
	public boolean deleteItem(int id) throws HibernateToDoListException;
	public void getItems() throws HibernateToDoListException;
	public void setUsername(User user) throws SQLException, HibernateToDoListException; //todo add function
	boolean isUserExists(User user) throws HibernateToDoListException;
	public boolean authenticate(String username, String password) throws SQLException, HibernateToDoListException;
	public User getUserByUsername(String username) throws SQLException, HibernateToDoListException; 
	public List<Item> getItems(User user) throws HibernateToDoListException;
	public User getUser(int userID) throws HibernateToDoListException;
	public boolean updateItem(Item updateItem) throws HibernateToDoListException;
	public Item getItem(int itemID) throws HibernateToDoListException;
	public boolean connect(String name, String password) throws HibernateToDoListException;
	public boolean connect(User user) throws HibernateToDoListException;
	public boolean isUserExists(int userID) throws HibernateToDoListException;
	
}