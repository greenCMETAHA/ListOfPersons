package utils;

import org.apache.log4j.Logger;

import DAO.DAO;

public class Utils {
	private static Utils utils;
	
	private static Logger log = Logger.getLogger(DAO.class);   	
	
	public final static int PERSONS_ON_PAGE=10;
	public final static String EMPTY="";
	
	private void Unils(){
		
	}
	
	public static Utils start(){
		if(utils == null)
			utils = new Utils();
			
		if (utils==null) //ooops...
			log.error("Can not create the Utilits class");
		
		return utils;
	}
	
	
}
