package main.java.DAO;

public class HibernateToDoListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * New {@link HibernateToDoListException} Instance
	 */
	public HibernateToDoListException(){
		super();
	}
	/**
	 *New {@link HibernateToDoListException} @param message
	 */
	public HibernateToDoListException(String message){
		super(message);
	}
	public HibernateToDoListException(String message, Throwable arg){
		super(message,arg);
	}
	public HibernateToDoListException(Throwable arg){
		super(arg);
	}
}
