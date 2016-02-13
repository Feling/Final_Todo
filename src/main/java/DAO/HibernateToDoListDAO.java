package main.java.DAO;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


import main.java.table.Item;
import main.java.table.User;
import main.java.util.HibernateUtil;

public class HibernateToDoListDAO implements IToDoListDAO{



	@Override
	public void addUser(User user) throws HibernateToDoListException {
		Session session = null;
		 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			throw new HibernateToDoListException("Error adding User");
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		//session.close();
	}

/*	@Override
/*	public boolean isUserExists(User user){
		 Session session = null;
		 boolean result = false;
		 try{
		 session = HibernateUtil.getSessionFactory().openSession();
			 session.beginTransaction();
			 Query query = session.createQuery("from User where ID ='"+user.getId()+"'");
			 User u = (User)query.uniqueResult();
			 session.getTransaction().commit();
			 if(u!=null) result = true;
		 }catch(Exception e){
			e.printStackTrace();
		 }finally{
			 session.close();
		 }
		return result;
	}*/
	
	@Override
	public void deleteUser(User user) throws HibernateToDoListException {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}catch(HibernateException e){
			throw new HibernateException("Cant delete User");
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		
		
	}

	@Override
	public boolean addItem(Item item) throws HibernateToDoListException {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		}catch(HibernateException e){
			throw new HibernateException("Cant add Item (Item)");
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		//session.close();
		return true;
		
	}

	@Override
	public void getUsers() throws HibernateToDoListException  {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List Users = session.createQuery("from User").list();
			Iterator i = Users.iterator();
			System.out.println("There are " + Users.size() + " Users");
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
		}catch(HibernateException e){
			throw new HibernateException("Problem with getUser()");
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
			
			
		
	}
	public void setUsername (User user) throws HibernateToDoListException {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(HibernateException e){
			throw new HibernateException("Cant setUsername");
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
}
	/*public void getItems(User user)  {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List Users = session.createQuery("from user").list();
			Iterator i = Users.iterator();
			System.out.println("There are " + Users.size() + " Users");
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
		}catch(Exception e){
		
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
	}*/

	@Override
	public boolean deleteItem(int id) throws HibernateToDoListException
	{
		boolean success = true;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Item tempInstance = (Item) session.load(Item.class, id);
			session.delete(tempInstance);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			throw new HibernateToDoListException("Could't delete item");
		}
		finally
		{
			session.close();
		}
		return success;
	}
	/*public boolean deletItem(Item item) {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(item);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		return true;
	}*/

	@Override
	public void getItems() throws HibernateToDoListException {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List Items = session.createQuery("from Item").list();
			Iterator i = Items.iterator();
			System.out.println("There are " + Items.size() + " Items");
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
		}catch(HibernateException e){
		throw new HibernateException("Cant get Items");
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		
	}

	public boolean authenticate(String username, String password) throws HibernateToDoListException {
		 User user = getUserByUsername(username);		
		 if(user!=null && user.getUsername().equals(username) && user.getPassword().equals(password)){
			 return true;
		 }else{
			 return false;
		 }
	}
		 public User getUserByUsername(String username) throws HibernateToDoListException {
			 Session session = null;
			 User user = null;
			 try{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();

			 	 Query query = session.createQuery("from User where username='"+username+"'");
				 user = (User)query.uniqueResult();
				 session.getTransaction().commit();
			 } catch (HibernateException e) {
				 if (session != null) {
					 session.beginTransaction().rollback();
				 }
				throw new HibernateException("Cant getUserByUserName");
			 } finally {
				 session.close();
			 }
			 return user;
		 }
		
		/* public Set<Item> getItems(User user) {
			 Session session = null;	

				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();

		 Query query=session.createQuery("from User");  
		    List<User> list=query.list();
		      
		    Iterator<User> itr=list.iterator();  
		    while(itr.hasNext()){  
		        User q=itr.next();  
		        System.out.println("Question Name: "+q.getUsername());  
		    }
			return null;
		 }
}*/
		 
		 @Override 
		 public List<Item> getItems(User user) throws HibernateToDoListException
			{
				String hql = new String("from Item where ID='" + user.getId() + "'");
				List<Item> itemsOfUser = null;
				Session session = null;
				try
				{
					if (isUserExists(user))
					{
						session = HibernateUtil.getSessionFactory().openSession();
						session.beginTransaction();
						Query query = session.createQuery(hql);
						itemsOfUser = query.list();
						session.getTransaction().commit();		
					}
					else
					{
						itemsOfUser = null;
					}
				}
				catch (HibernateException e)
				{
					session.getTransaction().rollback();	
					throw new HibernateException("Cannot get items");
				}
				finally
				{
					session.close();
				}
				return itemsOfUser;
			}

		public User getUser(int userID) throws HibernateToDoListException {
			
			 Session session = null;
			 User user = null;
			 try{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();

			 	 Query query = session.createQuery("from User where ID='"+userID+"'");
				 user = (User)query.uniqueResult();
				 session.getTransaction().commit();
			 } catch (HibernateException e) {
					 session.beginTransaction().rollback();
					 throw new HibernateException("Cant getUser with userID");
				 
			 } finally {
				 session.close();
			 }
			 return user;
		}

		public Item getItem(int itemID) throws HibernateToDoListException {
			 Session session = null;
			 Item item = null;
			 try{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();

			 	 Query query = session.createQuery("from Item where ITEM_ID='"+itemID+"'");
				 item = (Item)query.uniqueResult();
				 session.getTransaction().commit();
			 } catch (HibernateException e) {
					 session.beginTransaction().rollback();
				 throw new HibernateException("Cant getItem with itemID");
				
			 } finally {
				 session.close();
			 }
			 return item;
		}

	/*	public boolean updateItem(Item updateItem) {
			Session session = null;
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.update(updateItem);
				session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if ((session != null) && (session.isOpen()))session.close();
			}
			//session.close();
			return true;
		}*/
		@Override
		public boolean updateItem(Item item) throws HibernateToDoListException
		{
			boolean success = true;
			if (isUserExists(item.getItemid()))
			{
				Session session = null;
				try
				{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					Item tempInstanceOfItem = (Item) session.load(Item.class, item.getItemid());
					if (tempInstanceOfItem != null && tempInstanceOfItem.getItemid() == item.getItemid())
					{
						if (item.getDescription() != null)
						{
							tempInstanceOfItem.setDescription(item.getDescription());
						}
						if (item.getName() != null)
						{
							tempInstanceOfItem.setName(item.getName());
						}
						session.save(tempInstanceOfItem);
						session.getTransaction().commit();
					}
					else
					{
						throw new HibernateToDoListException("Update Error: You cannot change this item.");
					}
				}
				catch (HibernateException e)
				{
					success = false;
					if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new HibernateToDoListException("Error: Transaction is empty");
					}
				}
				finally
				{
					session.close();
				}
				return success;
			}
			else
			{
				System.out.println("no user connected");
				return !success;
			}
		}


		public boolean isUserExists(int userID) throws HibernateToDoListException {
			 Session session = null;
			 boolean result = false;
			 try{
			 session = HibernateUtil.getSessionFactory().openSession();
				 session.beginTransaction();
				 Query query = session.createQuery("from User where ID ='"+userID+"'");
				 session.getTransaction().commit();
				 if (query != null)
					 result = true;
			 }catch(HibernateException e){
				throw new HibernateToDoListException("CAnt check if user exist by userId");
			 }finally{
				 session.close();
			 }
			return result;
		}

		public boolean connect(User user) throws HibernateToDoListException 
		{
			return connect(user.getUsername(), user.getPassword());

		}

		/**
		 * an overloading of connect of the IToDoListUserDAO interface.
		 *
		 * @param i_User
		 *            the i_ user
		 * @return true, if successfull
		 * @throws HibernateToDoListException 
		 * @throws HibernateToDoListDAOException
		 *             the hibernate to do list dao exception
		 */
		public boolean connect(String name, String password) throws HibernateToDoListException 
		{
			return isUserExists(name, password);
		}
	
		private boolean isUserExists(String Name, String Password) throws HibernateToDoListException
		{
			return isUserExists(new User(0, Name, Password));
		}

		/**
		 * Checks if is user exists.
		 *
		 * @param user
		 *            the user
		 * @return true, if is user exists
		 * @throws HibernateToDoListException 
		 */
		 public boolean isUserExists(User user) throws HibernateToDoListException
		{
			return isUserExists(user.getId());
		}
		
}


